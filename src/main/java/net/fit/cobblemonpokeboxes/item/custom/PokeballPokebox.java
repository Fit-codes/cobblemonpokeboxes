package net.fit.cobblemonpokeboxes.item.custom;

import net.fit.cobblemonpokeboxes.component.ModComponentTypes;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
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
        PokeboxLootpool lootpool = null;
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

        //Get what item the Pokebox rolls.
        if (!level.isClientSide) {
            //ConfigManager.lootgoxdebug();
            //System.out.println("PokeballPokebox InteractionResultHolder is being run on the server!");

            //Assigns defualt lootpool if there is none.
            if(itemstack.get(ModComponentTypes.POKEBOXLOOTPOOL) == null)
                itemstack.set(ModComponentTypes.POKEBOXLOOTPOOL, 1);
            //System.out.println("Interacted lootbox number: " + itemstack.get(ModComponentTypes.POKEBOXLOOTPOOL.get()));
            //Constructs the lootpool for this pokebox
            //TODO NOT NEEDED
            lootpool = new PokeboxLootpool(itemstack.get(ModComponentTypes.POKEBOXLOOTPOOL.get()));
            lootpool.rollReward(itemstack);
        }

        //finish animation
        if(level.isClientSide){

            //try catch to make sure the itemstack data matches server
        }

        //give player rolled item
        if (!level.isClientSide) {
            itemstack.consume(1, player);
//            ((ServerLevel) level).sendParticles(ParticleTypes.DUST_COLOR_TRANSITION,
//                player.getX(), player.getY(), player.getZ(), 5, 0 , 0 ,0, 0);
            int tier = itemstack.get(ModComponentTypes.POKEBOXREWARDTIER);
            int reward = itemstack.get(ModComponentTypes.POKEBOXREWARD);
            Item rewardItem = lootpool.getReward(tier, reward);
            int amount = lootpool.getAmount(tier, reward);
            String tierColor = lootpool.getTierColor(tier);

            // Send chat message with tier color
            net.minecraft.network.chat.MutableComponent message = Component.literal("Opened ")
                .append(Component.literal(lootpool.name).withStyle(net.minecraft.ChatFormatting.GOLD))
                .append(Component.literal(" and received "))
                .append(Component.translatable(rewardItem.getDescriptionId()).withStyle(getTierChatColor(tierColor)))
                .append(Component.literal(" x" + amount).withStyle(getTierChatColor(tierColor)));

            player.sendSystemMessage(message);

            ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(rewardItem, amount));
        }


        if (!level.isClientSide) {

        }

        //testing


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

    private net.minecraft.ChatFormatting getTierChatColor(String tierColor) {
        return switch (tierColor.toLowerCase()) {
            case "green" -> net.minecraft.ChatFormatting.GREEN;
            case "blue" -> net.minecraft.ChatFormatting.BLUE;
            case "purple" -> net.minecraft.ChatFormatting.LIGHT_PURPLE;
            case "red" -> net.minecraft.ChatFormatting.RED;
            case "yellow" -> net.minecraft.ChatFormatting.YELLOW;
            case "gold" -> net.minecraft.ChatFormatting.GOLD;
            default -> net.minecraft.ChatFormatting.GRAY; // "none" or unknown
        };
    }
}
