package net.fit.cobblemonpokeboxes.item.custom;

import net.fit.cobblemonpokeboxes.component.ModComponentTypes;
import net.fit.cobblemonpokeboxes.loot.PokeboxLootpoolData;
import net.fit.cobblemonpokeboxes.loot.PokeboxLootpoolManager;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Datapack-compatible version of PokeboxLootpool.
 * Loads loot pool data from JSON files instead of config files.
 */
public class PokeboxLootpool {
    public String name;
    String itemID;
    List<Pair<Integer, Integer>> weightrange;
    List<String> color;
    List<ArrayList<Pair<Item, Integer>>> lootpool;
    List<String> luckyTierColors;

    public PokeboxLootpool(int lootpoolnumber) {
        // Load from datapack instead of config
        PokeboxLootpoolData data = PokeboxLootpoolManager.getLootpool(lootpoolnumber);

        if (data == null) {
            System.err.println("ERROR: Loot pool " + lootpoolnumber + " not found in datapacks! Using fallback.");
            // Create empty fallback
            name = "Missing Lootpool " + lootpoolnumber;
            itemID = "pokeball_lootbox";
            weightrange = new ArrayList<>();
            color = new ArrayList<>();
            lootpool = new ArrayList<>();
            luckyTierColors = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                weightrange.add(new Pair<>(0, 0));
                color.add("none");
                lootpool.add(new ArrayList<>());
            }
            return;
        }

        name = data.name();
        itemID = data.itemId();
        weightrange = new ArrayList<>();
        color = new ArrayList<>();
        lootpool = new ArrayList<>();
        luckyTierColors = data.luckyTiers();

        int cumulativeWeight = 0;

        // Process each tier from the datapack
        for (int tierIndex = 0; tierIndex < data.tiers().size() && tierIndex < 6; tierIndex++) {
            PokeboxLootpoolData.LootTier tier = data.tiers().get(tierIndex);

            // Set up weight ranges
            if (tier.weight() <= 0) {
                weightrange.add(new Pair<>(0, 0));
            } else {
                int startWeight = cumulativeWeight + 1;
                cumulativeWeight += tier.weight();
                weightrange.add(new Pair<>(startWeight, cumulativeWeight));
            }

            // Set tier color
            color.add(tier.color());

            // Build loot pool for this tier
            ArrayList<Pair<Item, Integer>> tierLootpool = new ArrayList<>();
            for (PokeboxLootpoolData.LootEntry entry : tier.items()) {
                try {
                    Item item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(entry.item()));
                    tierLootpool.add(new Pair<>(item, entry.count()));
                } catch (Exception e) {
                    System.err.println("ERROR: Invalid item in loot pool: " + entry.item());
                }
            }
            lootpool.add(tierLootpool);
        }

        // Fill remaining tiers if less than 6 were defined
        while (weightrange.size() < 6) {
            weightrange.add(new Pair<>(0, 0));
            color.add("none");
            lootpool.add(new ArrayList<>());
        }

        System.out.println("+---------------------------------------------+");
        System.out.println("Loaded loot pool from datapack: " + name + " (ID: " + itemID + ")");
        System.out.println("Tier weights:");
        for (int i = 0; i < weightrange.size(); i++) {
            System.out.println("  Tier " + (i + 1) + " (" + color.get(i) + "): " + weightrange.get(i).getA() + "-" + weightrange.get(i).getB());
        }
        System.out.println("+---------------------------------------------+");
    }

    /**
     * Represents the result of rolling a reward from the loot pool.
     */
    public static class RewardRoll {
        public final int tier;
        public final int itemIndex;
        public final Item item;
        public final int amount;
        public final String tierColor;
        public final boolean isLuckyDrop;

        public RewardRoll(int tier, int itemIndex, Item item, int amount, String tierColor, boolean isLuckyDrop) {
            this.tier = tier;
            this.itemIndex = itemIndex;
            this.item = item;
            this.amount = amount;
            this.tierColor = tierColor;
            this.isLuckyDrop = isLuckyDrop;
        }
    }

    public RewardRoll rollReward() {
        int rolleditemindex = 0;
        int rolledtier = 0;

        for (int i = weightrange.size(); !(i == 0); i--) {
            if (!(weightrange.get(i - 1).getB() == 0)) {
                int rollednum = ((int) ((Math.random() * (weightrange.get(i - 1).getB()))) + 1);
                System.out.println("[Pokebox Roll] Random number: " + rollednum + " (out of " + weightrange.get(i - 1).getB() + ")");
                for (int x = 0; x < weightrange.size(); x++) {
                    if (rollednum <= weightrange.get(x).getB()) {
                        rolledtier = x + 1;
                        break;
                    }
                }
                break;
            }
        }

        System.out.println("[Pokebox Roll] Selected Tier " + rolledtier + " (" + color.get(rolledtier - 1) + ")");
        rolleditemindex = (int) (Math.random() * (lootpool.get(rolledtier - 1).size()));

        // Return the reward information directly instead of storing in itemstack
        Item rewardItem = getReward(rolledtier, rolleditemindex);
        int amount = getAmount(rolledtier, rolleditemindex);
        String tierColor = getTierColor(rolledtier);
        // Check if this tier's color is in the lucky tiers list (case-insensitive)
        boolean isLucky = luckyTierColors.stream()
            .anyMatch(luckyColor -> luckyColor.equalsIgnoreCase(tierColor));

        System.out.println("[Pokebox Roll] Rolled: " + rewardItem.getDescriptionId() + " x" + amount + " (Tier " + rolledtier + ", Color: " + tierColor + ")" + (isLucky ? " - LUCKY DROP!" : ""));

        return new RewardRoll(rolledtier, rolleditemindex, rewardItem, amount, tierColor, isLucky);
    }

    public Item getReward(int tier, int itemindex) {
        return lootpool.get(tier - 1).get(itemindex).getA();
    }

    public int getAmount(int tier, int itemindex) {
        return lootpool.get(tier - 1).get(itemindex).getB();
    }

    public String getTierColor(int tier) {
        return color.get(tier - 1);
    }
}
