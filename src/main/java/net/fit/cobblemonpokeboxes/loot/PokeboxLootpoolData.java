package net.fit.cobblemonpokeboxes.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

/**
 * Data class representing a pokebox loot pool loaded from datapacks.
 * This replaces the config-based system with a datapack-compatible approach.
 */
public record PokeboxLootpoolData(
        String name,
        String itemId,
        List<LootTier> tiers
) {
    public static final Codec<PokeboxLootpoolData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("name").forGetter(PokeboxLootpoolData::name),
                    Codec.STRING.fieldOf("item_id").forGetter(PokeboxLootpoolData::itemId),
                    LootTier.CODEC.listOf().fieldOf("tiers").forGetter(PokeboxLootpoolData::tiers)
            ).apply(instance, PokeboxLootpoolData::new)
    );

    public record LootTier(
            int weight,
            String color,
            List<LootEntry> items
    ) {
        public static final Codec<LootTier> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.INT.fieldOf("weight").forGetter(LootTier::weight),
                        Codec.STRING.fieldOf("color").forGetter(LootTier::color),
                        LootEntry.CODEC.listOf().fieldOf("items").forGetter(LootTier::items)
                ).apply(instance, LootTier::new)
        );
    }

    public record LootEntry(
            String item,
            int count
    ) {
        public static final Codec<LootEntry> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.STRING.fieldOf("item").forGetter(LootEntry::item),
                        Codec.INT.fieldOf("count").forGetter(LootEntry::count)
                ).apply(instance, LootEntry::new)
        );
    }
}
