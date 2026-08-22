package su.terrafirmagreg.core.common.blockentity;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import net.dries007.tfc.common.blockentities.InventoryBlockEntity;
import net.dries007.tfc.common.capabilities.InventoryItemHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandler;

import su.terrafirmagreg.core.TFGCore;
import su.terrafirmagreg.core.common.container.RocketWorkbenchContainer;
import su.terrafirmagreg.core.common.data.TFGTags;

public class RocketWorkbenchBlockEntity extends InventoryBlockEntity<InventoryItemHandler> {
    public static final int SLOT_TOT = 1;
    public static final int MAIN_SLOT = 0;

    private static final Component NAME = Component.translatable(TFGCore.MOD_ID + ".block_entity.rocket_workbench");

    /**
     * Constructs a new RocketWorkbenchBlockEntity.
     * @param pos   The block position.
     * @param state The block state.
     */
    public RocketWorkbenchBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, RocketWorkbenchBlockEntity::createInventory, NAME);
    }

    /**
     * Creates the inventory handler.
     * @param entity The block entity.
     * @return The inventory item handler.
     */
    private static InventoryItemHandler createInventory(InventoryBlockEntity<?> entity) {
        return new InventoryItemHandler(entity, SLOT_TOT);
    }

    /**
     * @return The inventory handler.
     */
    public IItemHandler getInventory() {
        return inventory;
    }

    /**
     * Creates the ui for the rocket workbench.
     * @param windowId The window ID.
     * @param inv      The player inventory.
     * @param player   The player.
     * @return The container menu.
     */
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowId, @NotNull Inventory inv, @NotNull Player player) {
        return RocketWorkbenchContainer.create(this, inv, windowId);
    }

    /**
            * Gets the stack limit for a given slot.
            * @param slot The slot index.
            * @return The stack limit.
     */
    @Override
    public int getSlotStackLimit(int slot) {
        return switch (slot) {
            case MAIN_SLOT -> 1;
            default -> 64;
        };
    }

    /**
     * Checks if an item is valid for a given slot.
     * @param slot  The slot index.
     * @param stack The item stack.
     * @return True if the item is valid for the slot.
     */
    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return switch (slot) {
            case MAIN_SLOT -> stack.is(TFGTags.Items.ROCKET_BLUEPRINT);
            default -> false;
        };
    }

}
