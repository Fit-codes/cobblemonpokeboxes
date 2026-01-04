package net.fit.cobblemonpokeboxes.loot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Manager for loading pokebox loot pools from datapacks.
 * Supports /reload command for server-side modifications.
 */
public class PokeboxLootpoolManager extends SimpleJsonResourceReloadListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(PokeboxLootpoolManager.class);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String DIRECTORY = "pokebox_lootpools";

    private static final Map<Integer, PokeboxLootpoolData> lootpools = new HashMap<>();

    public PokeboxLootpoolManager() {
        super(GSON, DIRECTORY);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> resources, ResourceManager resourceManager, ProfilerFiller profiler) {
        lootpools.clear();
        LOGGER.info("Loading pokebox loot pools from datapacks...");

        for (Map.Entry<ResourceLocation, JsonElement> entry : resources.entrySet()) {
            ResourceLocation id = entry.getKey();
            JsonElement json = entry.getValue();

            try {
                // Parse the JSON using the codec
                PokeboxLootpoolData data = PokeboxLootpoolData.CODEC
                        .parse(JsonOps.INSTANCE, json)
                        .getOrThrow(error -> {
                            LOGGER.error("Failed to parse loot pool {}: {}", id, error);
                            return new RuntimeException(error);
                        });

                // Extract the lootpool number from the filename (e.g., "lootbox1" -> 1)
                String path = id.getPath();
                int lootpoolNumber = extractLootpoolNumber(path);

                if (lootpoolNumber > 0) {
                    lootpools.put(lootpoolNumber, data);
                    LOGGER.info("Loaded loot pool {} ({}): {}", lootpoolNumber, data.itemId(), data.name());
                } else {
                    LOGGER.warn("Could not extract lootpool number from path: {}", path);
                }

            } catch (Exception e) {
                LOGGER.error("Error loading loot pool {}: {}", id, e.getMessage(), e);
            }
        }

        LOGGER.info("Loaded {} pokebox loot pools", lootpools.size());
    }

    /**
     * Extract the lootpool number from the filename.
     * Examples: "lootbox1" -> 1, "pokebox_3" -> 3
     */
    private int extractLootpoolNumber(String path) {
        try {
            // Try to extract number from the end of the filename
            String[] parts = path.replaceAll("[^0-9]+", " ").trim().split(" ");
            if (parts.length > 0 && !parts[parts.length - 1].isEmpty()) {
                return Integer.parseInt(parts[parts.length - 1]);
            }
        } catch (NumberFormatException e) {
            LOGGER.warn("Could not parse lootpool number from: {}", path);
        }
        return -1;
    }

    /**
     * Get a loot pool by its number (1-indexed).
     */
    public static PokeboxLootpoolData getLootpool(int lootpoolNumber) {
        return lootpools.get(lootpoolNumber);
    }

    /**
     * Get all loaded loot pools.
     */
    public static Map<Integer, PokeboxLootpoolData> getAllLootpools() {
        return new HashMap<>(lootpools);
    }

    /**
     * Check if a loot pool exists.
     */
    public static boolean hasLootpool(int lootpoolNumber) {
        return lootpools.containsKey(lootpoolNumber);
    }
}
