package com.elxilon.create_mechanical_jetpack.item;

import com.elxilon.create_mechanical_jetpack.CreateMechanicalJetpack;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateMechanicalJetpack.ID);

    public static final DeferredItem<Item> COPPER_JETPACK = ITEMS.register("copper_jetpack", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NETHERITE_JETPACK = ITEMS.register("netherite_jetpack", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
