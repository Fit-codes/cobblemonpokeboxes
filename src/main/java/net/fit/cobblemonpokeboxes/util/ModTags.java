package net.fit.cobblemonpokeboxes.util;

import net.fit.cobblemonpokeboxes.CobblemonPokeboxes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags{
    public static class Blocks{
        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(CobblemonPokeboxes.MODID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> POKEBOX = createTag("pokebox");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(CobblemonPokeboxes.MODID, name));
        }
    }
}