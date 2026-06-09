package su.terrafirmagreg.core.common.data.rockets;

import org.jetbrains.annotations.NotNull;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public record RocketInsulation(int stateID, Item insulationItem, ResourceLocation name, int count) {
    @Override
    public @NotNull String toString() {
        return name.toString();
    }
}
