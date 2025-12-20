package net.fit.cobblemonpokeboxes.datagen;

import net.fit.cobblemonpokeboxes.CobblemonPokeboxes;
import net.fit.cobblemonpokeboxes.block.ModBlocks;
import net.fit.cobblemonpokeboxes.item.ModCreativeModeTabs;
import net.fit.cobblemonpokeboxes.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.common.CreativeModeTabRegistry;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(PackOutput output, String locale) {
        super(output, CobblemonPokeboxes.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        //Adds creative mode tab translation.
        this.add("creativetab.pokeboxes.pokeboxes_items", "Pokeboxes");
        //Item translations.
        this.add(ModItems.POKEBALL_POKEBOX.get(), "Pokeball Pokebox");
        this.add(ModItems.ORIGIN_STONE.get(), "Origin Stone");
        this.add(ModItems.POKE_ESSENCE.get(), "Poke Essence");
        this.add(ModItems.POKE_GEM.get(), "Poke Gem");
        this.add(ModItems.POKE_GEM_DUST.get(), "Poke Gem Dust");
        this.add(ModItems.POKE_JEWEL.get(), "Poke Jewel");
        this.add(ModItems.REGISTONE.get(), "Registone");
        this.add(ModBlocks.POKE_GEM_ORE.get(), "Poke Gem Ore");
        this.add(ModBlocks.POKE_GEM_DEEPSLATE_ORE.get(), "Deepslate Poke Gem Ore");


        //Debugging tooltip translations
        this.add("tooltip.cobblemonpokeboxes,pokeballpokebox.tooltip", "Pokebox lootpool data: ");
    }
}
