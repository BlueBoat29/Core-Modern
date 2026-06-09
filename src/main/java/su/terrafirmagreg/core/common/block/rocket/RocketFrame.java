package su.terrafirmagreg.core.common.block.rocket;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import su.terrafirmagreg.core.common.data.blocks.TFGBlocks;
import su.terrafirmagreg.core.common.data.rockets.RocketInsulation;
import su.terrafirmagreg.core.common.data.rockets.RocketMaterials;

public class RocketFrame extends Block {
    public static final VoxelShape SHAPE = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F);

    public RocketFrame(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    @SuppressWarnings("deprecation")
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        final ItemStack handStack = player.getMainHandItem();

        if (level.isClientSide) {
            return InteractionResult.PASS;
        }

        if (!RocketMaterials.ROCKET_INSULATION.containsKey(handStack.getItem())) {
            return InteractionResult.PASS;
        }

        RocketInsulation insulation = RocketMaterials.ROCKET_INSULATION.get(handStack.getItem());

        if (handStack.getCount() < insulation.count()) {
            return InteractionResult.PASS;
        }

        BlockState newBlock = TFGBlocks.INSULATED_ROCKET_FRAME.getDefaultState().setValue(InsulatedRocketFrame.INSULATION_ID, insulation.stateID());

        level.setBlockAndUpdate(pos, newBlock);

        if (!player.getAbilities().instabuild) {
            handStack.shrink(insulation.count());
        }

        level.playLocalSound(pos, SoundType.WOOL.getBreakSound(), SoundSource.BLOCKS, 1F, 1F, false);

        return InteractionResult.SUCCESS;
    }
}
