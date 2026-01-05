package net.fit.cobblemonpokeboxes.item.custom;

import net.fit.cobblemonpokeboxes.component.ModComponentTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemHandlerHelper;

import java.util.List;

public class PokeballPokebox extends Item {
    public PokeballPokebox(Properties properties) {
        super(properties);
    }


    //Builds the lootpool from the config JSON, changes the pokebox reward
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        //Play sound and start animation.
        if(level.isClientSide){
            //plays a chest opening sound around the player
            level.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.CHEST_OPEN,
                    SoundSource.NEUTRAL,
                    0.5F,
                    1.2F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
            );
        }

        //Roll reward and give player the item (server-side only)
        if (!level.isClientSide) {
            //Assigns default lootpool if there is none.
            if(itemstack.get(ModComponentTypes.POKEBOXLOOTPOOL) == null)
                itemstack.set(ModComponentTypes.POKEBOXLOOTPOOL, 1);

            //Constructs the lootpool for this pokebox
            PokeboxLootpool lootpool = new PokeboxLootpool(itemstack.get(ModComponentTypes.POKEBOXLOOTPOOL.get()));

            //Roll the reward - this now returns the reward data directly
            PokeboxLootpool.RewardRoll reward = lootpool.rollReward();

            //Consume the pokebox item AFTER we have the reward data
            itemstack.consume(1, player);

            ChatFormatting tierColor = getTierChatColor(reward.tierColor);

            if (reward.isLuckyDrop) {
                // Lucky drop - broadcast to all players on the server
                MutableComponent luckyMessage = Component.literal("LUCKY DROP! ")
                    .withStyle(tierColor, ChatFormatting.BOLD)
                    .append(Component.literal(player.getName().getString())
                        .withStyle(ChatFormatting.WHITE, ChatFormatting.BOLD))
                    .append(Component.literal(" pulled a ")
                        .withStyle(ChatFormatting.WHITE))
                    .append(Component.translatable(reward.item.getDescriptionId())
                        .withStyle(tierColor, ChatFormatting.BOLD))
                    .append(Component.literal(" x" + reward.amount)
                        .withStyle(tierColor, ChatFormatting.BOLD))
                    .append(Component.literal(" from a ")
                        .withStyle(ChatFormatting.WHITE))
                    .append(Component.literal(lootpool.name)
                        .withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD))
                    .append(Component.literal("!")
                        .withStyle(ChatFormatting.WHITE));

                // Broadcast to all players on the server
                if (level.getServer() != null) {
                    for (ServerPlayer serverPlayer : level.getServer().getPlayerList().getPlayers()) {
                        serverPlayer.sendSystemMessage(luckyMessage);
                    }
                }
            } else {
                // Regular drop - send only to the player who opened the box
                MutableComponent message = Component.literal("Opened ")
                    .append(Component.literal(lootpool.name).withStyle(ChatFormatting.YELLOW))
                    .append(Component.literal(" and received "))
                    .append(Component.translatable(reward.item.getDescriptionId()).withStyle(tierColor))
                    .append(Component.literal(" x" + reward.amount).withStyle(tierColor));

                player.sendSystemMessage(message);
            }

            //Give the player the reward item
            ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(reward.item, reward.amount));
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack itemstack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()){
            tooltipComponents.add(Component.translatable("tooltip.cobblemonpokeboxes,pokeballpokebox.tooltip"));
            if(itemstack.get(ModComponentTypes.POKEBOXLOOTPOOL) != null)
                tooltipComponents.add(Component.literal(""+ itemstack.get(ModComponentTypes.POKEBOXLOOTPOOL)));
//            tooltipComponents.add(Component.translatable("tooltip.cobblemonpokeboxes,pokeballpokebox2.tooltip"));
//            if(itemstack.get(ModComponentTypes.POKEBOXREWARDTIER) != null)
//                tooltipComponents.add(Component.literal(""+ itemstack.get(ModComponentTypes.POKEBOXREWARDTIER)));
//            tooltipComponents.add(Component.translatable("tooltip.cobblemonpokeboxes,pokeballpokebox3.tooltip"));
//            if(itemstack.get(ModComponentTypes.POKEBOXREWARD) != null)
//                tooltipComponents.add(Component.literal(""+ itemstack.get(ModComponentTypes.POKEBOXREWARD)));
        }
        super.appendHoverText(itemstack, context, tooltipComponents, tooltipFlag);
    }

    private ChatFormatting getTierChatColor(String tierColor) {
        return switch (tierColor.toLowerCase()) {
            case "green" -> ChatFormatting.GREEN;
            case "blue" -> ChatFormatting.BLUE; // Darker blue
            case "purple" -> ChatFormatting.LIGHT_PURPLE;
            case "red" -> ChatFormatting.RED;
            case "yellow" -> ChatFormatting.GOLD; // Changed from YELLOW to GOLD for a richer color
            case "gold" -> ChatFormatting.GOLD;
            default -> ChatFormatting.GRAY; // "none" or unknown
        };
    }
}
