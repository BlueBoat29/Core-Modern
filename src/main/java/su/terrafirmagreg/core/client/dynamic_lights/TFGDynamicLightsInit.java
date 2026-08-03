package su.terrafirmagreg.core.client.dynamic_lights;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import dev.lambdaurora.lambdynlights.api.DynamicLightsInitializer;

@OnlyIn(Dist.CLIENT)
public class TFGDynamicLightsInit implements DynamicLightsInitializer {
    @Override
    public void onInitializeDynamicLights() {
        System.out.println("this worked");
    }
}
