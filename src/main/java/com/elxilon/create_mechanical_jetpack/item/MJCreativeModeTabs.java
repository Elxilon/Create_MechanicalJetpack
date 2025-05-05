package com.elxilon.create_mechanical_jetpack.item;

import com.tterrag.registrate.util.entry.ItemProviderEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.elxilon.create_mechanical_jetpack.CreateMechanicalJetpack.ID;

public class MJCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ID);

    public static final List<ItemProviderEntry<?, ?>> ITEMS = List.of(
            MJItems.COPPER_JETPACK,
            MJItems.NETHERITE_JETPACK
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DEFAULT_TAB = CREATIVE_MODE_TABS.register("mechanical_jetpack_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + ID + ".base"))
            .icon(() -> new ItemStack(MJItems.COPPER_JETPACK.get()))
            .displayItems(new DisplayItemsGenerator(ITEMS))
            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    private record DisplayItemsGenerator(
            List<ItemProviderEntry<?, ?>> items) implements CreativeModeTab.DisplayItemsGenerator {
        @Override
        public void accept(@NotNull CreativeModeTab.ItemDisplayParameters params, @NotNull CreativeModeTab.Output output) {
            for (ItemProviderEntry<?, ?> item : items) {
                output.accept(item);
            }
        }
    }
}
