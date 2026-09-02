package su.terrafirmagreg.core.common.data.rockets;

import net.minecraft.world.item.Item;

public interface RocketMaterialRecord {

    default Item getItem() {
        return null;
    }
}
