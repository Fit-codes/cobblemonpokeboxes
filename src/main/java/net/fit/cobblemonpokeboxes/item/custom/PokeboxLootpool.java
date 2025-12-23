package net.fit.cobblemonpokeboxes.item.custom;

import net.fit.cobblemonpokeboxes.component.ModComponentTypes;
import net.fit.cobblemonpokeboxes.config.ConfigManager;
import net.fit.cobblemonpokeboxes.config.LootboxConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class PokeboxLootpool {
    String name;
    String itemID;
    List<Pair<Integer, Integer>> weightrange;
    List<String> color;
    List<ArrayList<Pair<Item, Integer>>> lootpool;

    public PokeboxLootpool(int lootpoolnumber){
        //System.out.println("+---------------------------------------------+");
        LootboxConfig lootbox = ConfigManager.getLootboxConfig(lootpoolnumber);
        //System.out.println("loopoolnumber: "+ lootpoolnumber);
        //System.out.println("Creating lootpool for lootbox: "+ lootbox.getLootboxNumber());
        name = lootbox.getName();
        itemID = lootbox.getID();
        weightrange = new ArrayList<>();
        color = new ArrayList<>();
        List<? extends String> lootpoolHolder = new ArrayList<>();
        lootpool = new ArrayList<>();
        ArrayList<Pair<Item, Integer>> lootpairlist = new ArrayList<>();

        //System.out.print("Setting up tier: 1 |||");
        weightrange.add(new Pair<>(1, lootbox.getTierWeight(1)));
        //System.out.print(" weight: " + weightrange.getFirst().getA() + " " +weightrange.getFirst().getB());

        color.add(lootbox.getTierColor(1));
        //System.out.print(" | color: " + color.get(0));


        //System.out.println(" | Lootpool for tier 1: ");
        //lootpoolHolder = lootbox.getTierLootpool(1);
        for(int x = 0; x < lootbox.getTierLootpool(1).size(); x = x + 2){
            lootpairlist.add(new Pair<>(BuiltInRegistries.ITEM.get(ResourceLocation.parse(lootbox.getTierLootpool(1).get(x))), Integer.parseInt(lootbox.getTierLootpool(1).get(x+1))));
        }
        lootpool.add(new ArrayList<>(lootpairlist));
//        for(int x = 0; x <lootpool.get(0).size(); x++) {
//            System.out.println(lootpairlist.get(x).getA() + " " + lootpairlist.get(x).getB());
//        }


        for(int index = 1; index <= 5; index++){
            //System.out.print("Setting up tier: " + (index + 1) + " |||");


            //If the weight is 0, prevents the tier from being rolled.
            if (lootbox.getTierWeight(index+1) <= 0) {
                weightrange.add(new Pair<>(0, 0));
            }
            else {
                weightrange.add(new Pair<>(weightrange.get(index-1).getB() + 1, weightrange.get(index-1).getB() + lootbox.getTierWeight(index+1)));
            }
            //System.out.print( " weight: " + weightrange.get(index).getA() + " " +weightrange.get(index).getB());

            color.add(lootbox.getTierColor(index+1));
            //System.out.print(" | color: " + color.get(index));

            //System.out.println(" | Lootpool for tier " + (index + 1 )+ " : ");
            //lootpoolHolder = lootbox.getTierLootpool(index+1);
            lootpairlist.clear();
            for(int x = 0; x < lootbox.getTierLootpool(index + 1).size(); x = x + 2){
                lootpairlist.add(new Pair<>(BuiltInRegistries.ITEM.get(ResourceLocation.parse(lootbox.getTierLootpool(index + 1).get(x))), Integer.parseInt(lootbox.getTierLootpool(index + 1).get(x + 1))));
            }
            lootpool.add(new ArrayList<>(lootpairlist));
//            for(int x = 0; x <lootpool.get(index).size(); x++) {
//                System.out.println(lootpool.get(index).get(x).getA() + " " + lootpool.get(index).get(x).getB());
//            }
        }
        System.out.println("+---------------------------------------------+");
        for(int index = 0; index <= 5; index++) {
            for (int x = 0; x < lootpool.get(index).size(); x++) {

                //System.out.println(lootpool.get(index).get(x).getA() + " " + lootpool.get(index).get(x).getB());
            }
        }
    }

    public void rollReward(ItemStack itemstack){
        int rolleditemindex = 0;
        int rolledtier = 0;
        BuiltInRegistries.ITEM.get(ResourceLocation.parse("minecraft:stick"));
        for(int i = weightrange.size(); !(i == 0); i--){
            if(!(weightrange.get(i-1).getB() == 0)) {
                int rollednum = ((int) ((Math.random() * (weightrange.get(i - 1).getB()))) + 1);
                //System.out.println("rollednum: " + rollednum);
                for(int x = 0; x < weightrange.size(); x++){
                    if(rollednum <= weightrange.get(x).getB()){
                        rolledtier = x + 1;
                        break;
                    }
                }
                break;
            }
        }
        //System.out.println("rolledtier: " + rolledtier);
        //System.out.println("rolledtier size: " + lootpool.get(rolledtier - 1).size());
        rolleditemindex =(int) (Math.random() * (lootpool.get(rolledtier - 1).size()));
        //System.out.println("rolleditem: " + rolleditemindex);

        //sets the box properties to what reward it rolled.
        itemstack.set(ModComponentTypes.POKEBOXREWARDTIER.get(), rolledtier);
        itemstack.set(ModComponentTypes.POKEBOXREWARD.get(), rolleditemindex);
    }

    //TODO have list of lootpool items have recource keys instead of strings!!!!

    public Item getReward(int tier, int itemindex) {
        //System.out.println("Givinng player reward: " + lootpool.get(tier-1).get(itemindex).getA());
        return lootpool.get(tier-1).get(itemindex).getA();
    }
    public int getAmount(int tier, int itemindex) {
        //System.out.println("Of amount: " + lootpool.get(tier-1).get(itemindex).getB());
        return lootpool.get(tier-1).get(itemindex).getB();
    }
    public String getTierColor(int tier) {
        //System.out.println("Of amount: " + color.get(tier-1));
        return color.get(tier-1);
    }

}
