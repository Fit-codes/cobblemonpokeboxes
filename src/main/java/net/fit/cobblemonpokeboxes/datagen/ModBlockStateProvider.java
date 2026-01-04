package net.fit.cobblemonpokeboxes.datagen;

import net.fit.cobblemonpokeboxes.CobblemonPokeboxes;
import net.fit.cobblemonpokeboxes.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CobblemonPokeboxes.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
//        blockWithItem(ModBlocks.BISMUTH_BLOCK);
//
        blockWithItem(ModBlocks.POKE_GEM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_POKE_GEM_ORE);

    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
