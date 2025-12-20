package net.fit.cobblemonpokeboxes.item;

import net.fit.cobblemonpokeboxes.CobblemonPokeboxes;
import net.fit.cobblemonpokeboxes.item.custom.PokeballPokebox;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CobblemonPokeboxes.MODID);

    public static final DeferredItem<Item> POKEBALL_POKEBOX = ITEMS.register("pokeball_pokebox",
            () -> new PokeballPokebox(new Item.Properties()));

    //Pokegem registeries
    public static final DeferredItem<Item> ORIGIN_STONE = ITEMS.register("origin_stone",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> POKE_ESSENCE = ITEMS.register("poke_essence",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> POKE_GEM = ITEMS.register("poke_gem",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> POKE_GEM_DUST = ITEMS.register("poke_gem_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> POKE_JEWEL = ITEMS.register("poke_jewel",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> REGISTONE = ITEMS.register("registone",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

//.stacksTo(1)