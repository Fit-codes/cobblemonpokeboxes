package net.fit.cobblemonpokeboxes.item;

import net.fit.cobblemonpokeboxes.CobblemonPokeboxes;
import net.fit.cobblemonpokeboxes.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CobblemonPokeboxes.MODID);

    public static final Supplier<CreativeModeTab> POKEBOXES_TAB = CREATIVE_MODE_TAB.register("pokeboxes_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.POKEBALL_POKEBOX.get()))
//                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(CobblemonPokeBoxes.MODID, "tutorial_items_tab"))
                    .title(Component.translatable("creativetab.pokeboxes.pokeboxes_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.POKEBALL_POKEBOX);
                        output.accept(ModItems.ORIGIN_STONE);
                        output.accept(ModItems.POKE_ESSENCE);
                        output.accept(ModItems.POKE_GEM);
                        output.accept(ModItems.POKE_GEM_DUST);
                        output.accept(ModItems.POKE_JEWEL);
                        output.accept(ModItems.REGISTONE);
                        output.accept(ModBlocks.POKE_GEM_ORE);
                        output.accept(ModBlocks.POKE_GEM_DEEPSLATE_ORE);
                    }).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
