package su.terrafirmagreg.core.common.data.rockets.MaterialRecords;

import org.jetbrains.annotations.NotNull;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import su.terrafirmagreg.core.common.data.rockets.RocketMaterialRecord;

public record RocketEngine(Item engineItem, ResourceLocation name, int thrust, float fuelUseFactor) implements RocketMaterialRecord {
    @Override
    public @NotNull String toString() {
        return name.toString();
    }

    @Override
    public Item getItem() {
        return engineItem;
    }
}
