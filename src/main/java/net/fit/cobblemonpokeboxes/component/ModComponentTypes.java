package net.fit.cobblemonpokeboxes.component;

import net.fit.cobblemonpokeboxes.CobblemonPokeboxes;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.entity.npc.VillagerData;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

import static com.mojang.serialization.Codec.*;

public class ModComponentTypes {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(CobblemonPokeboxes.MODID);

    //Creates the pokeboxloot data
    public static final DeferredHolder<DataComponentType<?>,DataComponentType<Integer>> POKEBOXLOOTPOOL = register("pokeboxloot",
            builder -> builder.persistent(INT));
    //VillagerTradesEvent


    //    public record PokeBoxReward(int arg1){}
//    public static final StreamCodec<RegistryFriendlyByteBuf, PokeBoxReward> POKEBOX_REWARD_STREAM_CODEC =
//            StreamCodec.composite(
//                    ByteBufCodecs.VAR_INT, PokeBoxReward::arg1,
//                    PokeBoxReward::new
//            );
    public static final DeferredHolder<DataComponentType<?>,DataComponentType<Integer>> POKEBOXREWARDTIER = register("pokeboxrewardtier",
            builder -> builder.persistent(INT));
    public static final DeferredHolder<DataComponentType<?>,DataComponentType<Integer>> POKEBOXREWARD = register("pokeboxrewarditemnum",
            builder -> builder.persistent(INT));




    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name,
                                                                                          UnaryOperator<DataComponentType.Builder<T>> builderOperator){
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void  register(IEventBus eventBus){
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
