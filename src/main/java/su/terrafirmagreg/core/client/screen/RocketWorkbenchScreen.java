package su.terrafirmagreg.core.client.screen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.dries007.tfc.client.screen.TFCContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import su.terrafirmagreg.core.TFGCore;
import su.terrafirmagreg.core.client.screen.widget.IconToggleButton;
import su.terrafirmagreg.core.common.container.RocketWorkbenchContainer;
import su.terrafirmagreg.core.common.data.rockets.RocketMaterialRecord;
import su.terrafirmagreg.core.common.data.rockets.RocketMaterials;
import su.terrafirmagreg.core.common.data.rockets.RocketMenuGroup;

public class RocketWorkbenchScreen extends TFCContainerScreen<RocketWorkbenchContainer> {
    private final String texturePrefix = "textures/gui/rocket_menu/";

    public final Map<Integer, RocketMenuGroup> mainMenuButtons = new HashMap<>();

    /// Map linking main menu buttonID to all child buttonIDs
    public final Map<Integer, List<Integer>> mainChildMap = new HashMap<>();

    public final Map<Integer, Item> subMenuButtons = new HashMap<>();

    /// Map of all buttons keyed by buttonID
    // may not be needed idk
    public final Map<Integer, IconToggleButton> allButtons = new HashMap<>();

    ///  Map of all sub buttons keyed by buttonID
    public final Map<Integer, IconToggleButton> allSubButtons = new HashMap<>();

    public RocketWorkbenchScreen(RocketWorkbenchContainer container, Inventory playerInventory, Component name) {
        super(container, playerInventory, name, INVENTORY_2x2);
    }

    /**
     * Initializes the screen, adding widgets and updating buttons.
     */
    @Override
    protected void init() {
        super.init();
        makeMainMenu();
        System.out.println(mainMenuButtons);
        System.out.println(mainChildMap);
        System.out.println(allButtons);
        System.out.println(allSubButtons);

    }

    boolean isButtonOn() {
        return true;
    }

    private void makeMainMenu() {
        int menuButtonOffsetX = leftPos;
        int menuButtonOffsetY = topPos;

        int buttonID = 0;

        for (RocketMenuGroup menuGroup : RocketMaterials.MENU_GROUPS) {
            ItemStack icon = menuGroup.iconItem();
            ResourceLocation tooltip = menuGroup.tooltipText();
            List<?> matList = menuGroup.subList();

            int finalButtonID = buttonID;
            IconToggleButton newButton = new IconToggleButton(
                    menuButtonOffsetX, menuButtonOffsetY, 16, 16,
                    TFGCore.id(texturePrefix + "blue_slot.png"), icon,
                    16, 16, this::isButtonOn,
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.gameMode != null) {
                            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, finalButtonID);
                        }
                    }, font);
            menuButtonOffsetY += 20;
            mainMenuButtons.put(buttonID, menuGroup);
            allButtons.put(buttonID, newButton);

            this.addRenderableWidget(newButton);
            buttonID = makeSubButtons(matList, buttonID, menuButtonOffsetX);
            buttonID++;
        }

    }

    private int makeSubButtons(List<?> matList, int originalButtonID, int offsetX) {
        if (matList.isEmpty()) {
            return originalButtonID;
        }

        int buttonID = originalButtonID;

        offsetX += 16;
        int offsetY = topPos;

        List<Integer> childIDList = new ArrayList<>();

        for (Object material : matList) {
            if (material instanceof RocketMaterialRecord rocketMaterial) {
                buttonID++;
                Item materialItem = rocketMaterial.getItem();

                int finalButtonID = buttonID;
                IconToggleButton newButton = new IconToggleButton(
                        offsetX, offsetY, 16, 16,
                        TFGCore.id(texturePrefix + "blue_slot.png"), materialItem.getDefaultInstance(),
                        16, 16, this::isButtonOn,
                        (button) -> {
                            if (this.minecraft != null && this.minecraft.gameMode != null) {
                                this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, finalButtonID);
                            }
                        }, font);
                newButton.active = false;
                offsetY += 10;
                subMenuButtons.put(buttonID, materialItem);
                allButtons.put(buttonID, newButton);
                allSubButtons.put(buttonID, newButton);
                childIDList.add(buttonID);

                this.addRenderableWidget(newButton);
            }
        }

        mainChildMap.put(originalButtonID, childIDList);
        return buttonID;
    }

    /**
     * Called when a mouse button is clicked within the GUI element.
     * @return {@code true} if the event is consumed, {@code false} otherwise.
     *
     * @param mouseX the X coordinate of the mouse.
     * @param mouseY the Y coordinate of the mouse.
     * @param button the button that was clicked.
     */
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (mainMenuButtons.containsKey(button)) {
            setSubMenu(button);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public void setSubMenu(int menuButtonID) {
        System.out.println(menuButtonID);
        allSubButtons.values().forEach(button -> button.active = false);
        System.out.println("disabled buttons");

        mainChildMap.get(menuButtonID).forEach(activeButtonID -> allSubButtons.get(activeButtonID).active = true);
    }

}
