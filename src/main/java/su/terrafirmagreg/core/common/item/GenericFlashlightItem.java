package su.terrafirmagreg.core.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;

import su.terrafirmagreg.core.client.dynamic_lights.ConeLightSource;

public class GenericFlashlightItem extends Item {
    public GenericFlashlightItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack heldStack = player.getItemInHand(usedHand);
        CompoundTag heldStackTag = heldStack.getOrCreateTag();

        if (!heldStackTag.contains(tagKey) || heldStackTag.getInt(tagKey) == 0) {
            heldStackTag.putInt(tagKey, 1);
            heldStack.setTag(heldStackTag);

            if (level.isClientSide) {
                var targetPos = calculateLookingPos(player.getRotationVector(), player.blockPosition(), player.getEyeY());
                new ConeLightSource().testing(level, targetPos);
                System.out.println("made light");
            }

            return InteractionResultHolder.consume(heldStack);
        }

        if (heldStackTag.getInt(tagKey) == 1) {
            heldStackTag.putInt(tagKey, 0);
            heldStack.setTag(heldStackTag);
            return InteractionResultHolder.consume(heldStack);
        }

        return InteractionResultHolder.pass(heldStack);
    }

    public static final String tagKey = "flashlight_state";

    public static boolean testFlashlightStatus(ItemStack itemStack) {
        if (!(itemStack.getItem() instanceof GenericFlashlightItem)) {
            return false;
        }

        CompoundTag itemTag = itemStack.getOrCreateTag();

        int status = itemTag.getInt(tagKey);

        return status == 1;
    }

    //0 = off, 1 = on
    public enum FlashlightStatus {
        OFF, ON
    }

    private BlockPos calculateLookingPos(Vec2 viewVec, BlockPos pos, double eyePos) {
        int magnitude = 5;
        float xRot = -viewVec.y;
        float yRot = -viewVec.x;

        var xChange = magnitude * Math.sin(xRot);
        var zChange = magnitude * Math.cos(xRot);
        var yChange = magnitude * Math.sin(yRot);

        System.out.println("xRot " + xRot);
        System.out.println("yRot " + yRot);
        System.out.println(new BlockPos((int) (pos.getX() + xChange), (int) (eyePos + yChange), (int) (pos.getZ() + zChange)));

        return new BlockPos((int) (pos.getX() + xChange), (int) (eyePos + yChange), (int) (pos.getZ() + zChange));

    }
}
