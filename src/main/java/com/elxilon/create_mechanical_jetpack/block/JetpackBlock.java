package com.elxilon.create_mechanical_jetpack.block;

import com.elxilon.create_mechanical_jetpack.CreateMechanicalJetpack;
import com.simibubi.create.content.equipment.armor.BacktankBlock;
import com.simibubi.create.content.equipment.armor.BacktankBlockEntity;
import com.simibubi.create.content.equipment.armor.BacktankRenderer;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

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

    @Override
    public InteractionResult onSneakWrenched(BlockState state, UseOnContext context) {
        onWrenched(state, context);
        return super.onSneakWrenched(state, context);
    }

    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        Level level = context.getLevel();
        if (level.isClientSide)
            return InteractionResult.SUCCESS;

        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();

        if (player != null) {
            BacktankBlockEntity be = getBlockEntity(level, pos);
            if (be != null) {
                ItemStack stack = getCloneItemStack(level, pos, state);

                if (!player.getInventory().add(stack))
                    player.drop(stack, false);

                level.destroyBlock(pos, false);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }
}
