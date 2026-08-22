package su.terrafirmagreg.core.common.data.rockets;

import org.jetbrains.annotations.NotNull;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public record RocketFrame(Item frameBlockItem, ResourceLocation name, int strength) {
    @Override
    public @NotNull String toString() {
        return name.toString();
    }
}
