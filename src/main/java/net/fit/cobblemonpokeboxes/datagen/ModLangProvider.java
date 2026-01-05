package net.fit.cobblemonpokeboxes.datagen;

import net.fit.cobblemonpokeboxes.CobblemonPokeboxes;
import net.fit.cobblemonpokeboxes.block.ModBlocks;
import net.fit.cobblemonpokeboxes.item.ModItems;
import net.minecraft.data.PackOutput;
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
        this.add(ModItems.GREATBALL_POKEBOX.get(), "Greatball Pokebox");
        this.add(ModItems.ULTRABALL_POKEBOX.get(), "Ultraball Pokebox");
        this.add(ModItems.MINERS_AND_CRAFTERS_BOX.get(), "Miners & Crafters Box");
        this.add(ModItems.COSMETIC_BOX.get(), "Cosmetic Box");
        this.add(ModItems.SIMPLE_HATS_BOX.get(), "Simple Hats Box");
        this.add(ModItems.POKEMON_COSMETIC_POKEBOX.get(), "Pokemon Cosmetic Pokebox");
        this.add(ModItems.POKEDOLL_POKEBOX.get(), "Pokedoll Pokebox");
        this.add(ModItems.PLUSHIE_BOX.get(), "Plushie Box");
        this.add(ModItems.POKEROCK_POKEBOX.get(), "Pokerock Pokebox");
        this.add(ModItems.BATTLE_ITEM_POKEBOX.get(), "Battle Item Pokebox");
        this.add(ModItems.DIVEBALL_POKEBOX.get(), "Diveball Pokebox");
        this.add(ModItems.NETHER_BOX.get(), "Nether Box");
        this.add(ModItems.CLOTHING_SCRAP.get(), "Clothing Scrap");

        this.add(ModItems.ORIGIN_STONE.get(), "Origin Stone");
        this.add(ModItems.POKE_ESSENCE.get(), "Poke Essence");
        this.add(ModItems.POKE_GEM.get(), "Poke Gem");
        this.add(ModItems.POKE_GEM_FRAGMENT.get(), "Poke Gem Fragment");
        this.add(ModItems.POKE_GEM_DUST.get(), "Poke Gem Dust");
        this.add(ModItems.POKE_JEWEL.get(), "Poke Jewel");
        this.add(ModItems.REGISTONE.get(), "Registone");
        this.add(ModItems.BACKPACK_UPGRADER.get(), "Backpack Upgrader");
        this.add(ModBlocks.POKE_GEM_ORE.get(), "Poke Gem Ore");
        this.add(ModBlocks.DEEPSLATE_POKE_GEM_ORE.get(), "Deepslate Poke Gem Ore");


        //Debugging tooltip translations
        this.add("tooltip.cobblemonpokeboxes,pokeballpokebox.tooltip", "Pokebox lootpool data: ");
    }
}
