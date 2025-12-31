package net.fit.cobblemonpokeboxes.datagen;


import net.fit.cobblemonpokeboxes.CobblemonPokeboxes;
import net.fit.cobblemonpokeboxes.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CobblemonPokeboxes.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //basicItem(ModItems.POKEBALL_POKEBOX.get());
        basicItem(ModItems.ULTRABALL_POKEBOX.get());
        basicItem(ModItems.MASTERBALL_POKEBOX.get());
        basicItem(ModItems.MINERS_AND_CRAFTERS_BOX.get());
        basicItem(ModItems.COSMETIC_BOX.get());
        basicItem(ModItems.SIMPLE_HATS_BOX.get());
        basicItem(ModItems.POKEMON_COSMETIC_POKEBOX.get());
        basicItem(ModItems.POKEDOLL_POKEBOX.get());
        basicItem(ModItems.PLUSHIE_BOX.get());

        basicItem(ModItems.ORIGIN_STONE.get());
        basicItem(ModItems.POKE_ESSENCE.get());
        basicItem(ModItems.POKE_GEM.get());
        basicItem(ModItems.POKE_GEM_FRAGMENT.get());
        basicItem(ModItems.POKE_GEM_DUST.get());
        basicItem(ModItems.POKE_JEWEL.get());
        basicItem(ModItems.REGISTONE.get());
        basicItem(ModItems.BACKPACK_UPGRADER.get());
    }
}
