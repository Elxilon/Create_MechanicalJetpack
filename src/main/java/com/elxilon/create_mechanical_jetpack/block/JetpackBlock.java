package com.elxilon.create_mechanical_jetpack.block;

import com.elxilon.create_mechanical_jetpack.CreateMechanicalJetpack;
import com.simibubi.create.content.equipment.armor.BacktankBlock;
import com.simibubi.create.content.equipment.armor.BacktankBlockEntity;
import com.simibubi.create.content.equipment.armor.BacktankRenderer;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.builders.BlockEntityBuilder;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class JetpackBlock extends BacktankBlock {
    private static final CreateRegistrate REGISTRATE = CreateMechanicalJetpack.registrate();

    public final static BlockEntityBuilder<BacktankBlockEntity, ?> JETPACK_BLOCK_ENTITY_BUILDER = REGISTRATE
            .blockEntity("jetpack", BacktankBlockEntity::new)
            .visual(() -> SingleAxisRotatingVisual::backtank)
            .renderer(() -> BacktankRenderer::new);

    private static BlockEntityType<BacktankBlockEntity> jetpackBlockEntityType;

    public JetpackBlock(Properties properties) {
        super(properties);
    }

    public static void registerBlockEntityType(BlockEntityType<BacktankBlockEntity> type) {
        jetpackBlockEntityType = type;
    }

    @Override
    public BlockEntityType<? extends BacktankBlockEntity> getBlockEntityType() {
        return jetpackBlockEntityType;
    }
}
