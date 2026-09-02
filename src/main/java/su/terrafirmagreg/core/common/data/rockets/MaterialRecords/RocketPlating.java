package su.terrafirmagreg.core.common.data.rockets.MaterialRecords;

import org.jetbrains.annotations.NotNull;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import su.terrafirmagreg.core.common.data.rockets.RocketMaterialRecord;

public record RocketPlating(int stateID, Item plateItem, ResourceLocation name, int count, TagKey<Item> offhandTool, int strength) implements RocketMaterialRecord {
    @Override
    public @NotNull String toString() {
        return name.toString();
    }

    @Override
    public Item getItem() {
        return plateItem;
    }
}
