package su.terrafirmagreg.core.client.dynamic_lights;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import toni.sodiumdynamiclights.DynamicLightSource;
import toni.sodiumdynamiclights.SodiumDynamicLights;

public class ConeLightSource {

    private List<DynamicLightSource> lightSourceList = new ArrayList<>();
    private static SodiumDynamicLights dynamicLightsInstance = null;

    private void checkInstance() {
        if (dynamicLightsInstance == null) {
            dynamicLightsInstance = SodiumDynamicLights.get();
        }
    }

    public void addSources() {
        checkInstance();

        lightSourceList.forEach(dynamicLightSource -> dynamicLightsInstance.addLightSource(dynamicLightSource));
    }

    public void testing(Level level, BlockPos playerPos) {
        if (!level.isClientSide)
            return;

        lightSourceList.add(new PointLightSource(playerPos.getX(), playerPos.getY(), playerPos.getZ() + 5, 15, level));
        addSources();
    }
}
