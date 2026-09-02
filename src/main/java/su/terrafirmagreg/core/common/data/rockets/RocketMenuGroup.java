package su.terrafirmagreg.core.common.data.rockets;

import java.util.List;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public record RocketMenuGroup(ItemStack iconItem, ResourceLocation tooltipText, List<?> subList) {
}
