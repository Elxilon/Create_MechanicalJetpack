package com.elxilon.create_mechanical_jetpack.block;

import com.elxilon.create_mechanical_jetpack.CreateMechanicalJetpack;
import com.elxilon.create_mechanical_jetpack.item.MJItems;

import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import com.simibubi.create.AllDataComponents;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.api.stress.BlockStressValues;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.LootTable.Builder;

import java.util.function.Supplier;

@SuppressWarnings("removal")
public class MJBlocks {
    private static final CreateRegistrate REGISTRATE = CreateMechanicalJetpack.registrate();

    public static <B extends Block, P> NonNullUnaryOperator<BlockBuilder<B, P>> jetpack(Supplier<ItemLike> drop) {
        return b -> b.blockstate((c, p) -> p.horizontalBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
                .transform(pickaxeOnly())
                .addLayer(() -> RenderType::cutoutMipped)
                .onRegister(block -> BlockStressValues.IMPACTS.register(block, () -> 4.0))
                .loot((lt, block) -> {
                    Builder builder = LootTable.lootTable();
                    LootItemCondition.Builder survivesExplosion = ExplosionCondition.survivesExplosion();
                    lt.add(block, builder.withPool(LootPool.lootPool()
                            .when(survivesExplosion)
                            .setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(drop.get())
                                    .apply(CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)
                                            .include(AllDataComponents.BACKTANK_AIR)))));
                });
    }

    public static final BlockEntry<JetpackBlock> COPPER_JETPACK_BLOCK =
            REGISTRATE.block("copper_jetpack", JetpackBlock::new)
                    .initialProperties(SharedProperties::copperMetal)
                    .transform(jetpack(MJItems.COPPER_JETPACK::get))
                    .register();

    public static final BlockEntry<JetpackBlock> NETHERITE_JETPACK_BLOCK =
            REGISTRATE.block("netherite_jetpack", JetpackBlock::new)
                    .initialProperties(SharedProperties::netheriteMetal)
                    .transform(jetpack(MJItems.NETHERITE_JETPACK::get))
                    .register();

    public static void register() {}
}
