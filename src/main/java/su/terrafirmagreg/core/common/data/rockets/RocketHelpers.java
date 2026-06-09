package su.terrafirmagreg.core.common.data.rockets;

import net.minecraft.client.color.block.BlockColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RocketHelpers {
    @OnlyIn(Dist.CLIENT)
    public static BlockColor tintedColor() {
        return (state, reader, pos, tintIndex) -> 0x333e47;
    }
}
