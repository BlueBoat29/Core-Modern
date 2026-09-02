package su.terrafirmagreg.core.client.screen.widget;

import java.util.function.BooleanSupplier;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

/**
 * A custom toggle button widget for GUI's.
 * Uses either an item or another texture as an overlay
 */
public final class IconToggleButton extends Button {
    private final ResourceLocation backgroundTexture;
    @Nullable
    private final ItemStack iconItem;
    @Nullable
    private final ResourceLocation iconTexture;
    private final int texWidth;
    private final int texHeight;
    private final BooleanSupplier isOnSupplier;
    private Font font;

    /**
     * Constructs a new ToggleButton instance using a texture overlay.
     *
     * @param x           The x-coordinate of the button.
     * @param y           The y-coordinate of the button.
     * @param width       The width of the button.
     * @param height      The height of the button.
     * @param backgroundTexture     The texture resource for the background.
     * @param iconTexture     The texture resource for the icon overlay.
     * @param texWidth    The width of the texture.
     * @param texHeight   The height of the texture.
     * @param isOn        A supplier to determine the current "on" state of the button.
     * @param onPress     The action to perform when the button is pressed.
     * @param font     font from the client screen.
     */
    public IconToggleButton(
            int x, int y, int width, int height, ResourceLocation backgroundTexture,
            @NotNull ResourceLocation iconTexture, int texWidth, int texHeight,
            BooleanSupplier isOn,
            OnPress onPress, Font font) {
        super(x, y, width, height, Component.empty(), onPress, DEFAULT_NARRATION);
        this.backgroundTexture = backgroundTexture;
        this.iconTexture = iconTexture;
        this.iconItem = null;
        this.texWidth = texWidth;
        this.texHeight = texHeight;
        this.isOnSupplier = isOn;
        this.font = font;
    }

    /**
     * Constructs a new ToggleButton instance using an item overlay.
     *
     * @param x           The x-coordinate of the button.
     * @param y           The y-coordinate of the button.
     * @param width       The width of the button.
     * @param height      The height of the button.
     * @param backgroundTexture     The texture resource for the background.
     * @param iconItem     The item for the icon overlay.
     * @param texWidth    The width of the texture.
     * @param texHeight   The height of the texture.
     * @param isOn        A supplier to determine the current "on" state of the button.
     * @param onPress     The action to perform when the button is pressed.
     * @param font     font from the client screen.
     */
    public IconToggleButton(
            int x, int y, int width, int height, ResourceLocation backgroundTexture,
            @NotNull ItemStack iconItem, int texWidth, int texHeight,
            BooleanSupplier isOn,
            OnPress onPress, Font font) {
        super(x, y, width, height, Component.empty(), onPress, DEFAULT_NARRATION);
        this.backgroundTexture = backgroundTexture;
        this.iconItem = iconItem;
        this.iconTexture = null;
        this.texWidth = texWidth;
        this.texHeight = texHeight;
        this.isOnSupplier = isOn;
        this.font = font;
    }

    /**
     * Renders the toggle button widget, including its texture and hover/disabled effects.
     *
     * @param gg     The graphics context for rendering.
     * @param mouseX The x-coordinate of the mouse.
     * @param mouseY The y-coordinate of the mouse.
     * @param delta  The partial tick time.
     */
    @Override
    protected void renderWidget(GuiGraphics gg, int mouseX, int mouseY, float delta) {
        final boolean on = this.isOnSupplier != null && this.isOnSupplier.getAsBoolean();

        final int u = 0;
        final int v = 0;

        if (!this.active) {
            return;
        }

        // Draw the button texture.
        gg.blit(this.backgroundTexture, this.getX(), this.getY(), u, v, this.texWidth, this.texHeight, this.texWidth, this.texHeight);

        if (this.iconTexture != null) {
            gg.blit(this.iconTexture, this.getX(), this.getY(), u, v, this.texWidth, this.texHeight, this.texWidth, this.texHeight);
        } else if (this.iconItem != null) {
            gg.renderItem(iconItem, this.getX(), this.getY());
            if (this.isMouseOver(mouseX, mouseY)) {
                gg.renderTooltip(font, iconItem, mouseX, mouseY);
            }
        }

        // Draw a hover effect if the button is active and the mouse is over it.
        if (this.active && this.isMouseOver(mouseX, mouseY)) {
            gg.fill(this.getX(), this.getY(), this.getX() + this.width, this.getY() + this.height, 0x40FFFFFF);
        }

        // Draw a dark overlay if the button is off.
        if (!on) {
            gg.fill(this.getX(), this.getY(), this.getX() + this.width, this.getY() + this.height, 0x80000000);
        }
    }
}
