package su.terrafirmagreg.core.common.block.rocket;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import net.dries007.tfc.util.Helpers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

import su.terrafirmagreg.core.common.data.TFGBlockEntities;

public class RocketWorkbenchBlock extends Block implements EntityBlock {
    public static final BooleanProperty HAS_BLUEPRINT = BooleanProperty.create("has_blueprint");

    public RocketWorkbenchBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any()
                .setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .setValue(HAS_BLUEPRINT, false));
    }

    /**
     * Handles player interaction
     * @param state The current block state.
     * @param level The world level.
     * @param pos The block position.
     * @param player The player interacting.
     * @param hand The hand used.
     * @param hit The hit result.
     * @return The interaction result.
     */
    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            if (player instanceof ServerPlayer serverPlayer) {
                level.getBlockEntity(pos, TFGBlockEntities.ROCKET_WORKBENCH.get())
                        .ifPresent(blockEntity -> Helpers.openScreen(serverPlayer, blockEntity, pos));
            }
            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return TFGBlockEntities.ROCKET_WORKBENCH.get().create(pos, state);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, ctx.getHorizontalDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder
                .add(BlockStateProperties.HORIZONTAL_FACING)
                .add(HAS_BLUEPRINT));
    }
}
