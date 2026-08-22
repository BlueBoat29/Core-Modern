package su.terrafirmagreg.core.client.screen.widget;

import net.dries007.tfc.client.RenderHelpers;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class DropDownMenuButton extends Button {
    public DropDownMenuButton(int x, int y, int width, int height, Component message) {
        super(x, y, width, height, message, (button) -> {
        }, RenderHelpers.NARRATION);
    }
}
