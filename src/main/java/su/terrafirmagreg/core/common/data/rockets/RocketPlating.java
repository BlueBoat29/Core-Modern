package su.terrafirmagreg.core.common.data.rockets;

import org.jetbrains.annotations.NotNull;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public record RocketPlating(int stateID, Item plateItem, ResourceLocation name, int count, TagKey<Item> offhandTool) {
    @Override
    public @NotNull String toString() {
        return name.toString();
    }

}
