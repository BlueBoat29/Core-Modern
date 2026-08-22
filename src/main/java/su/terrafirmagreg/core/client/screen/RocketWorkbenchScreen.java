package su.terrafirmagreg.core.client.screen;

import java.util.List;
import java.util.Map;

import net.dries007.tfc.client.screen.TFCContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import su.terrafirmagreg.core.client.screen.widget.DropDownMenuButton;
import su.terrafirmagreg.core.common.container.RocketWorkbenchContainer;
import su.terrafirmagreg.core.common.data.rockets.RocketMaterials;

public class RocketWorkbenchScreen extends TFCContainerScreen<RocketWorkbenchContainer> {
    private int menuButtonOffsetX = 20;
    private int menuButtonStartY = 20;

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

    }

    private void makeMainMenu() {
        int menuButtonY = menuButtonStartY;

        for (Map.Entry<ResourceLocation, List<?>> entryMap : RocketMaterials.MENU_GROUPS.entrySet()) {
            ResourceLocation name = entryMap.getKey();
            List<?> matList = entryMap.getValue();

            DropDownMenuButton newButton = new DropDownMenuButton(menuButtonOffsetX, menuButtonY, 100, 20, Component.literal("Frame"));
            menuButtonY += 25;
            this.addRenderableWidget(newButton);
        }

    }
}
