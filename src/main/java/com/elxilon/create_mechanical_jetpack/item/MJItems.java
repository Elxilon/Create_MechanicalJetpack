package com.elxilon.create_mechanical_jetpack.item;

import com.elxilon.create_mechanical_jetpack.CreateMechanicalJetpack;
import com.elxilon.create_mechanical_jetpack.block.MJBlocks;
import com.simibubi.create.AllCreativeModeTabs;
import com.simibubi.create.AllDataComponents;
import com.simibubi.create.AllTags;
import com.simibubi.create.Create;
import com.simibubi.create.content.equipment.armor.AllArmorMaterials;
import com.simibubi.create.content.equipment.armor.BacktankItem;
import com.simibubi.create.content.equipment.armor.BacktankUtil;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class MJItems {
    private static final CreateRegistrate REGISTRATE = CreateMechanicalJetpack.registrate();

    public static final ItemEntry<BacktankItem.BacktankBlockItem> COPPER_JETPACK_PLACEABLE = REGISTRATE
            .item("copper_jetpack_placeable",
                    p -> new BacktankItem.BacktankBlockItem(MJBlocks.COPPER_JETPACK_BLOCK.get(), MJItems.COPPER_JETPACK::get, p))
            .register();

    public static final ItemEntry<BacktankItem.BacktankBlockItem> NETHERITE_JETPACK_PLACEABLE = REGISTRATE
            .item("netherite_jetpack_placeable",
                    p -> new BacktankItem.BacktankBlockItem(MJBlocks.NETHERITE_JETPACK_BLOCK.get(), MJItems.NETHERITE_JETPACK::get, p))
            .register();

    public static final ItemEntry<? extends BacktankItem> COPPER_JETPACK = REGISTRATE
            .item("copper_jetpack",
                    p -> new JetpackItem(AllArmorMaterials.COPPER, p, Create.asResource("copper_diving"), COPPER_JETPACK_PLACEABLE))
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .transform(itemBuilder -> {
                itemBuilder.tab(AllCreativeModeTabs.BASE_CREATIVE_TAB.getKey(), (tab, output) -> {
                    ItemStack stack = new ItemStack(itemBuilder.getEntry());
                    stack.set(AllDataComponents.BACKTANK_AIR, BacktankUtil.maxAirWithoutEnchants());
                    output.accept(stack);
                });
                return itemBuilder;
            })
            .tag(ItemTags.CHEST_ARMOR)
            .tag(AllTags.AllItemTags.PRESSURIZED_AIR_SOURCES.tag)
            .register();

    public static final ItemEntry<? extends BacktankItem> NETHERITE_JETPACK = REGISTRATE
            .item("netherite_jetpack",
                    p -> new JetpackItem.Layered(ArmorMaterials.NETHERITE, p, Create.asResource("netherite_diving"), NETHERITE_JETPACK_PLACEABLE))
            .properties(Item.Properties::fireResistant)
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .transform(itemBuilder -> {
                itemBuilder.tab(AllCreativeModeTabs.BASE_CREATIVE_TAB.getKey(), (tab, output) -> {
                    ItemStack stack = new ItemStack(itemBuilder.getEntry());
                    stack.set(AllDataComponents.BACKTANK_AIR, BacktankUtil.maxAirWithoutEnchants());
                    output.accept(stack);
                });
                return itemBuilder;
            })
            .tag(ItemTags.CHEST_ARMOR)
            .tag(AllTags.AllItemTags.PRESSURIZED_AIR_SOURCES.tag)
            .register();

    public static void register() {}
}
