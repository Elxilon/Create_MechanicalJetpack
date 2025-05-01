package com.elxilon.create_mechanical_jetpack.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.elxilon.create_mechanical_jetpack.CreateMechanicalJetpack.ID;

public class AllCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ID);

    public static final Supplier<CreativeModeTab> DEFAULT_TAB = CREATIVE_MODE_TABS.register("mechanical_jetpack_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + ID + ".base"))
            .icon(() -> new ItemStack(AllItems.COPPER_JETPACK.get()))
            .displayItems((params, output) -> {
                output.accept(AllItems.COPPER_JETPACK);
                output.accept(AllItems.NETHERITE_JETPACK);
            })
            .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
