package net.fit.cobblemonpokeboxes.config;

import net.minecraft.world.entity.npc.VillagerTrades;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.VillagerTradingManager;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static net.fit.cobblemonpokeboxes.CobblemonPokeboxes.MODID;

public class ConfigManager {
    public static List<LootboxConfig> lootboxConfigList = new ArrayList<>();

    public ConfigManager(ModContainer modContainer){


        //TODO create a base server config that specifies the number of lootboxes. help with joining server with more files than you.
        // Maybe make lootboxes in datapacks?

        System.out.println("+---------------------------------------------+");
        boolean initialized = false;
        //If there is no config, make one!
        if(!Files.exists(Paths.get( "config/"+ MODID + "/lootbox"+ 1 + "-server.toml") )) {
            lootboxConfigList.add(new LootboxConfig(1));
            modContainer.registerConfig(ModConfig.Type.SERVER, lootboxConfigList.getFirst().getSpec(),MODID + "/lootbox1-server.toml");
            initialized = true;
        }
        //Looks through the config folder and registers all lootbox config files that are there.
        for(int i = 1; Files.exists(Paths.get( "config/"+ MODID + "/lootbox"+ i + "-server.toml")); i++ ) {
            if(!(initialized && i == 1)) {
                //System.out.println("Reading config " + i);
                lootboxConfigList.add(new LootboxConfig(i));
                modContainer.registerConfig(ModConfig.Type.SERVER, lootboxConfigList.get(i-1).getSpec(),MODID + "/lootbox" + i + "-server.toml");
            }
        }
        System.out.println("+---------------------------------------------+");
    }


    public static LootboxConfig getLootboxConfig(int lootboxnumber){
        return lootboxConfigList.get(lootboxnumber - 1);
    }
    //public static void lootgoxdebug(){
        //System.out.println("+---------------Lootbox debug-----------------+");
        //System.out.println("Number of registed lootboxes: " + lootboxConfigList.size());
        //for(int i = 0; i < lootboxConfigList.size(); i++){
            //System.out.println("Lootboxasdasdsdfsd");
            //System.out.println("Lootbox name: " + lootboxConfigList.get(i).getName());
            //System.out.println("Lootbox number: " + lootboxConfigList.get(i).getLootboxNumber());
        //}
        //System.out.println("+---------------------------------------------+");
    //}


//        LootboxConfig config = new Config();
//        if(!Files.exists(Paths.get( "config/"+ MODID + "/lootbox"+ 1 + ".toml") ))
//            modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC, MODID + "/lootbox1.toml");
//        for(int i = 1; Files.exists(Paths.get( "config/"+ MODID + "/lootbox"+ i + ".toml")); i++ ) {
//            // Register our mod's ModConfigSpec so that FML can create and load the config file for us
//            modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC, MODID + "/lootbox" + i + ".toml");
//        }

}

