package net.fit.cobblemonpokeboxes.item;

import net.fit.cobblemonpokeboxes.CobblemonPokeboxes;
import net.fit.cobblemonpokeboxes.item.custom.PokeballPokebox;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.fit.cobblemonpokeboxes.component.ModComponentTypes.POKEBOXLOOTPOOL;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CobblemonPokeboxes.MODID);

    public static final DeferredItem<Item> POKEBALL_POKEBOX = ITEMS.register("pokeball_pokebox",
            () -> new PokeballPokebox(new Item.Properties().component(POKEBOXLOOTPOOL, 1)));
    public static final DeferredItem<Item> ULTRABALL_POKEBOX = ITEMS.register("ultraball_pokebox",
            () -> new PokeballPokebox(new Item.Properties().component(POKEBOXLOOTPOOL, 2)));
    public static final DeferredItem<Item> MASTERBALL_POKEBOX = ITEMS.register("masterball_pokebox",
            () -> new PokeballPokebox(new Item.Properties().component(POKEBOXLOOTPOOL, 3)));
    public static final DeferredItem<Item> MINERS_AND_CRAFTERS_BOX = ITEMS.register("miners_and_crafters_box",
            () -> new PokeballPokebox(new Item.Properties().component(POKEBOXLOOTPOOL, 4)));
    public static final DeferredItem<Item> COSMETIC_BOX = ITEMS.register("cosmetic_box",
            () -> new PokeballPokebox(new Item.Properties().component(POKEBOXLOOTPOOL, 5)));
    public static final DeferredItem<Item> SIMPLE_HATS_BOX = ITEMS.register("simple_hats_box",
            () -> new PokeballPokebox(new Item.Properties().component(POKEBOXLOOTPOOL, 6)));
    public static final DeferredItem<Item> POKEMON_COSMETIC_POKEBOX = ITEMS.register("pokemon_cosmetic_pokebox",
            () -> new PokeballPokebox(new Item.Properties().component(POKEBOXLOOTPOOL, 7)));
    public static final DeferredItem<Item> POKEDOLL_POKEBOX = ITEMS.register("pokedoll_pokebox",
            () -> new PokeballPokebox(new Item.Properties().component(POKEBOXLOOTPOOL, 8)));
    public static final DeferredItem<Item> PLUSHIE_BOX = ITEMS.register("plushie_box",
            () -> new PokeballPokebox(new Item.Properties().component(POKEBOXLOOTPOOL, 9)));


    //Pokegem registeries
    public static final DeferredItem<Item> ORIGIN_STONE = ITEMS.register("origin_stone",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> POKE_ESSENCE = ITEMS.register("poke_essence",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> POKE_GEM = ITEMS.register("poke_gem",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> POKE_FRAGMENT = ITEMS.register("poke_fragment",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> POKE_GEM_DUST = ITEMS.register("poke_gem_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> POKE_JEWEL = ITEMS.register("poke_jewel",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> REGISTONE = ITEMS.register("registone",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BACKPACK_UPGRADER = ITEMS.register("backpack_upgrader",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

//.stacksTo(1)