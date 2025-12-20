package net.fit.cobblemonpokeboxes;

import java.io.File;
import java.util.List;

import com.electronwill.nightconfig.core.file.FileConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();


static {
    BUILDER.push(List.of("lootbox"));

                BUILDER.comment("Is this box enabled?")
                        .define("enabled", false);
                BUILDER.comment("In game display name of the lootbox.")
                        .define("name", "Test Lootbox");
                BUILDER.comment("ItemID name. Keep it all lowercase, one word, no spaces.")
                        .define("idname", "test_lootbox");
                BUILDER.comment("Sprite/model of the lootbox. Look above for options!")
                        .define("model", "pokeball");

                BUILDER.push(List.of("lootpool"));
                    //for(int x = 1; x <= 5; x++){
                        BUILDER.push("tier");
                        BUILDER.comment("Weight of the tier.")
                                .define("weight", 5);
                        BUILDER.comment("Color of the tier.")
                                .define("color", "none");
                        BUILDER.pop();
                BUILDER.pop();

            BUILDER.pop();
}


//    static {
//
//
//            BUILDER.push(List.of("lootbox"));
//
//                BUILDER.comment("Is this box enabled?")
//                        .define("enabled", false);
//                BUILDER.comment("In game display name of the lootbox.")
//                        .define("name", "Test Lootbox");
//                BUILDER.comment("ItemID name. Keep it all lowercase, one word, no spaces.")
//                        .define("idname", "test_lootbox");
//                BUILDER.comment("Sprite/model of the lootbox. Look above for options!")
//                        .define("model", "pokeball");
//
//                BUILDER.push(List.of("lootpool"));
//                    //for(int x = 1; x <= 5; x++){
//                        BUILDER.push("tier");
//                        BUILDER.comment("Weight of the tier.")
//                                .define("weight", 5);
//                        BUILDER.comment("Color of the tier.")
//                                .define("color", "none");
//                        //BUILDER.push("asfaf");
//
//                        //maybe it will pull the entire array if its empty? test this! could be our ticket to not having to make 9000000 different lootboxes
//                        //BUILDER.pop();
//
//                        BUILDER.pop();
//                    //}
//                BUILDER.pop();
//
//
//            BUILDER.pop();
//        }
//    }

    static final ModConfigSpec SPEC = BUILDER.build();


    //private static final ModConfigSpec.IntValue NUMBER_OF_LOOTBOXES;





    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }
}


//        static {
//            BUILDER.push("lootbox");
//
//            BUILDER
//                     .comment("In game display name of the lootbox.")
//                     .define("name", "Pokeball Pokebox");
//            BUILDER
//                    .comment("ItemID name. Keep it all lowercase, one word, no spaces.")
//                    .define("idname", "pokeball_pokebox");
//            BUILDER
//                    .comment("Sprite/model of the lootbox. Look above for what each number represents!")
//                    .define("model", 0);
//            BUILDER
//            .comment("A list of items to log on common setup.")
//            .defineListAllowEmpty("lootpool", List.of("minecraft:iron_ingot"), () -> "", Config::validateItemName);
//
//
//            BUILDER.pop();
//
//            BUILDER.push("lootbox");
//
//            BUILDER
//                    .comment("Whether to log the dirt block on common setup")
//                    .define("logDirtBlock", true);
//
//
//            BUILDER.pop();
//        }

//    public static final ModConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
//            .comment("Whether to log the dirt block on common setup")
//            .define("logDirtBlock", true);
//
//    public static final ModConfigSpec.IntValue MAGIC_NUMBER = BUILDER
//            .comment("A magic number")
//            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);
//
//    public static final ModConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
//            .comment("What you want the introduction message to be for the magic number")
//            .define("magicNumberIntroduction", "The magic number is... ");
//
//    // a list of strings that are treated as resource locations for items
//    public static final ModConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
//            .comment("A list of items to log on common setup.")
//            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), () -> "", Config::validateItemName);