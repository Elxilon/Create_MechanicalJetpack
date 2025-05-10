package com.elxilon.create_mechanical_jetpack.block;

import com.elxilon.create_mechanical_jetpack.CreateMechanicalJetpack;
import com.simibubi.create.content.equipment.armor.BacktankBlock;
import com.simibubi.create.content.equipment.armor.BacktankBlockEntity;
import com.simibubi.create.content.equipment.armor.BacktankRenderer;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class JetpackBlock extends BacktankBlock implements IBE<BacktankBlockEntity> {
    private static final CreateRegistrate REGISTRATE = CreateMechanicalJetpack.registrate();

    public final static BlockEntityEntry<BacktankBlockEntity> JETPACK_BLOCK_ENTITY = REGISTRATE
            .blockEntity("jetpack", BacktankBlockEntity::new)
            .visual(() -> SingleAxisRotatingVisual::backtank)
            .validBlocks(MJBlocks.COPPER_JETPACK_BLOCK, MJBlocks.NETHERITE_JETPACK_BLOCK)
            .renderer(() -> BacktankRenderer::new)
            .register();

    public JetpackBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends BacktankBlockEntity> getBlockEntityType() {
        return JETPACK_BLOCK_ENTITY.get();
    }
}
