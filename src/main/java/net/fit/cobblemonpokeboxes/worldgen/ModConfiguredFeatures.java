package net.fit.cobblemonpokeboxes.worldgen;

import net.fit.cobblemonpokeboxes.CobblemonPokeboxes;
import net.fit.cobblemonpokeboxes.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_POKE_GEM_ORE_KEY = registerKey("poke_gem_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_POKE_GEM_ORE_SMALL_KEY = registerKey("poke_gem_small_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_POKE_GEM_ORE_SURFACE_KEY = registerKey("poke_gem_surface_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldPokeGemOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.POKE_GEM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_POKE_GEM_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_POKE_GEM_ORE_KEY, Feature.ORE, new OreConfiguration(overworldPokeGemOres, 16, 0.75F ));
        register(context, OVERWORLD_POKE_GEM_ORE_SMALL_KEY, Feature.ORE, new OreConfiguration(overworldPokeGemOres, 7, 0.3F ));
        register(context, OVERWORLD_POKE_GEM_ORE_SURFACE_KEY, Feature.ORE, new OreConfiguration(overworldPokeGemOres, 7 ));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(CobblemonPokeboxes.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
