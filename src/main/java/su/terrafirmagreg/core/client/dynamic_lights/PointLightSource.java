package su.terrafirmagreg.core.client.dynamic_lights;

import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.NotNull;

import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.world.level.Level;

import toni.sodiumdynamiclights.DynamicLightSource;
import toni.sodiumdynamiclights.SodiumDynamicLights;

public class PointLightSource implements DynamicLightSource {

    private double x = 0;
    private double y = 0;
    private double z = 0;
    private int luminance = 0;
    private final Level level;

    public PointLightSource(double x, double y, double z, int luminance, Level level) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.luminance = luminance;
        this.level = level;
    }

    @Override
    public double sdl$getDynamicLightX() {
        return x;
    }

    @Override
    public double sdl$getDynamicLightY() {
        return y;
    }

    @Override
    public double sdl$getDynamicLightZ() {
        return z;
    }

    @Override
    public Level sdl$getDynamicLightLevel() {
        return level;
    }

    @Override
    public int sdl$getLuminance() {
        return luminance;
    }

    @Override
    public void sdl$resetDynamicLight() {

    }

    @Override
    public void sdl$dynamicLightTick() {

    }

    @Override
    public boolean sdl$shouldUpdateDynamicLight() {
        return false;
    }

    @Override
    public boolean sodiumdynamiclights$updateDynamicLight(@NotNull LevelRenderer levelRenderer) {

        if (luminance > 0) {
            var chunkPos = new BlockPos.MutableBlockPos(Math.floorDiv((int)x, 16),
                    Math.floorDiv((int)y, 16),
                    Math.floorDiv((int)z, 16));
            SodiumDynamicLights.scheduleChunkRebuild(levelRenderer, chunkPos);
            return true;
        }

        return false;
    }

    @Override
    public void sodiumdynamiclights$scheduleTrackedChunksRebuild(@NotNull LevelRenderer levelRenderer) {

    }
}
