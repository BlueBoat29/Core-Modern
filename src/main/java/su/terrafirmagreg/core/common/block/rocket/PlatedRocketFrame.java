package su.terrafirmagreg.core.common.block.rocket;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import su.terrafirmagreg.core.common.data.blocks.TFGBlocks;
import su.terrafirmagreg.core.common.data.rockets.RocketMaterials;

public class PlatedRocketFrame extends Block {

    public static final VoxelShape SHAPE = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F);
    public static final IntegerProperty INSULATION_ID = InsulatedRocketFrame.INSULATION_ID;
    public static final IntegerProperty PLATING_ID = IntegerProperty.create("plating_id", 1, 2);

    public PlatedRocketFrame(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any()
                .setValue(INSULATION_ID, 1)
                .setValue(PLATING_ID, 1));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(INSULATION_ID).add(PLATING_ID));
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        super.destroy(level, pos, state);
        if (level instanceof ServerLevel serverLevel) {
            Block.popResource(serverLevel, pos, RocketMaterials.getInsulationStack(state));
            Block.popResource(serverLevel, pos, RocketMaterials.getPlatingStack(state));
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        final ItemStack handStack = player.getMainHandItem();
        final ItemStack offhandStack = player.getOffhandItem();

        System.out.println("use");
        if (level.isClientSide)
            return InteractionResult.PASS;

        if (handStack.is(RocketMaterials.REMOVAL_TOOL)) {
            return useCrowbar(handStack, offhandStack, state, level, pos, player, hand, hit);
        } else {
            return InteractionResult.PASS;
        }

    }

    private InteractionResult useCrowbar(ItemStack handStack, ItemStack offhandStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        Block.popResource(level, pos, RocketMaterials.getPlatingStack(state));

        level.setBlockAndUpdate(pos, TFGBlocks.INSULATED_ROCKET_FRAME.get().withPropertiesOf(state));

        level.playLocalSound(pos, SoundType.ANVIL.getBreakSound(), SoundSource.BLOCKS, 1F, 1F, false);
        return InteractionResult.SUCCESS;
    }

}
