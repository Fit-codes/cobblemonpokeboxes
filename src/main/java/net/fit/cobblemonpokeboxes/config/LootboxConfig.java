package net.fit.cobblemonpokeboxes.config;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class LootboxConfig {
    private final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    private final ModConfigSpec SPEC;

    private final int LOOTBOXNUMBER;

    private final ModConfigSpec.ConfigValue<String> NAME;
    private final ModConfigSpec.ConfigValue<String> MODEL;
    private final List<ModConfigSpec.ConfigValue<Integer>> TIER_WEIGHT = new ArrayList<>();
    private final List<ModConfigSpec.ConfigValue<String>> TIER_COLOR = new ArrayList<>();
    private final List<ModConfigSpec.ConfigValue<List<? extends String>>> TIER_LOOTPOOL = new ArrayList<>();

    public LootboxConfig(int lootboxnumber){
        System.out.println("Constructing lootbox " + lootboxnumber);
        LOOTBOXNUMBER = lootboxnumber;

        BUILDER.push("lootbox");
            NAME = BUILDER.comment("In game display name of the lootbox.")
                    .define("Name", "Example Lootbox");
            MODEL = BUILDER.comment("Item to put the lootbox informtation into. View the readme for options!")
                    .define("ItemID", "pokeball_lootbox");
        BUILDER.pop();

        //TODO make tiers nested like this class, keep everything flexible. allows us to have as little or as many tiers as we want!

        BUILDER.push("lootpool");
            for(int x = 1; x <= 6; x++) {
                BUILDER.comment("---------------------------")
                        .push("Loot Tier: " + x);
                TIER_WEIGHT.add(BUILDER.comment("Weight of the tier. Set to 0 to disable.")
                        .define("Weight", 5));
                TIER_COLOR.add(BUILDER.comment("Color of the tier.")
                        .define("Color", "none"));
                TIER_LOOTPOOL.add((BUILDER.comment("Lootpool for the tier.")
                        .defineListAllowEmpty("Items", List.of("minecraft:coal", "4", "minecraft:apple", "1"), () -> "", LootboxConfig::validateInteger)));
                BUILDER.pop();
            }
        BUILDER.pop();
            SPEC = BUILDER.build();
    }

    public ModConfigSpec getSpec(){
        return SPEC;
    }
    public String getName(){return NAME.get();}
    public String getID(){return MODEL.get();}
    public int getTierWeight(int tier){return TIER_WEIGHT.get(tier-1).get();}
    public String getTierColor(int tier){return TIER_COLOR.get(tier-1).get();}
    public List<? extends String> getTierLootpool(int tier){return TIER_LOOTPOOL.get(tier-1).get();}

    //Used for debugging purposes.
    public int getLootboxNumber(){
        return LOOTBOXNUMBER;
    }
//    public int getTierNumber(int tier){
//        return TIER_NUMBER.get(tier-1).get();
//    }

    //TODO actually make this validate integers lmao :3
    private static boolean validateInteger(final Object obj){
            return true;
    }
    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }

}
