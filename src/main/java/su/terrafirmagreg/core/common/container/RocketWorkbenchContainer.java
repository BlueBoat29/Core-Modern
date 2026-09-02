package su.terrafirmagreg.core.common.container;

import org.jetbrains.annotations.NotNull;

import net.dries007.tfc.client.TFCSounds;
import net.dries007.tfc.common.container.BlockEntityContainer;
import net.dries007.tfc.common.container.CallbackSlot;
import net.dries007.tfc.common.recipes.inventory.EmptyInventory;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import su.terrafirmagreg.core.common.blockentity.RocketWorkbenchBlockEntity;
import su.terrafirmagreg.core.common.data.TFGContainers;

/**
 * Container for the Rocket Workbench block entity.
 */
public class RocketWorkbenchContainer extends BlockEntityContainer<RocketWorkbenchBlockEntity> {
    public static final int SLOT_TOT = RocketWorkbenchBlockEntity.SLOT_TOT;
    public static final int MAIN_SLOT = RocketWorkbenchBlockEntity.MAIN_SLOT;

    // Sets the gap between vertical sections of the GUI.
    public static final int SCREEN_SPACING = 5;

    /**
     * Initializes a new RocketWorkbenchContainer.
     * @param blockEntity The block entity.
     * @param playerInventory The player's inventory.
     * @param windowId The window ID.
     * @return The initialized RocketWorkbenchContainer.
     */
    public static RocketWorkbenchContainer create(RocketWorkbenchBlockEntity blockEntity, Inventory playerInventory, int windowId) {
        System.out.println("create rocket workbench");
        return new RocketWorkbenchContainer(blockEntity, playerInventory, windowId).init(playerInventory, 19 + SCREEN_SPACING + SCREEN_SPACING);
    }

    /**
     * Constructs a new RocketWorkbenchContainer.
     * @param blockEntity The block entity.
     * @param playerInventory The player's inventory.
     * @param windowId The window ID.
     */
    public RocketWorkbenchContainer(RocketWorkbenchBlockEntity blockEntity, Inventory playerInventory, int windowId) {
        super(TFGContainers.ROCKET_WORKBENCH.get(), windowId, blockEntity);

    }

    /**
     * Called when the container is closed.
     * @param player The player closing the container.
     */
    @Override
    public void removed(@NotNull Player player) {
        super.removed(player);
    }

    /**
     * Handles moving item stacks between slots.
     * @param stack The item stack to move.
     * @param slotIndex The index of the slot.
     * @return True if the move was successful.
     */
    @Override
    protected boolean moveStack(@NotNull ItemStack stack, int slotIndex) {
        return switch (typeOf(slotIndex)) {
            case MAIN_INVENTORY, HOTBAR -> !moveItemStackTo(stack, MAIN_SLOT, SLOT_TOT, false);
            case CONTAINER -> !moveItemStackTo(stack, containerSlots, slots.size(), false);
        };
    }

    /**
     * Adds the container's custom slots (inputs, tools, output).
     */
    @Override
    protected void addContainerSlots() {
        super.addContainerSlots();

        addSlot(new BlueprintSlot(blockEntity, MAIN_SLOT, 134, 72 + SCREEN_SPACING));
    }

    /**
     * Slot blueprint
     */
    public static class BlueprintSlot extends CallbackSlot {
        private final RocketWorkbenchBlockEntity blockEntity;

        /**
         * Constructs a ResultSlot.
         * @param blockEntity The block entity.
         * @param index The slot index.
         * @param x The x position.
         * @param y The y position.
         */
        public BlueprintSlot(RocketWorkbenchBlockEntity blockEntity, int index, int x, int y) {
            super(blockEntity, blockEntity.getInventory(), index, x, y);
            this.blockEntity = blockEntity;
        }

        /**
         * Determines if the player can pick up the output item.
         * @param player The player.
         * @return True if the item can be picked up.
         */
        @Override
        public boolean mayPickup(Player player) {
            return true;
        }

        /**
         * Stops items from entering the output slot.
         * @param stack The item stack.
         * @return False.
         */
        @Override
        public boolean mayPlace(@NotNull ItemStack stack) {
            return false;
        }

        /**
         * Handles logic when the player takes the output item.
         * @param player The player.
         * @param stack The item stack taken.
         */
        @Override
        public void onTake(@NotNull Player player, @NotNull ItemStack stack) {
            super.onTake(player, stack);

            player.level().playSound(null, blockEntity.getBlockPos(), TFCSounds.BELLOWS_BLOW.get(), player.getSoundSource(), 1, 2);

            if (player.level() instanceof ServerLevel serverLevel) {
                var pos = blockEntity.getBlockPos();
                double x = pos.getX() + 0.5;
                double y = pos.getY() + 1.0;
                double z = pos.getZ() + 0.5;
                serverLevel.sendParticles(ParticleTypes.SCRAPE, x, y, z, 10, 0.5, 0.3, 0.5, 0.3);
            }

        }
    }

    /**
     * Handler for recipe matching and crafting logic.
     * @param container The rocket workbench container.
     */
    public record RecipeHandler(RocketWorkbenchContainer container) implements EmptyInventory {
    }
}
