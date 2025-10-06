package su.terrafirmagreg.core.common.data.particles;

import net.dries007.tfc.client.ClimateRenderCache;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.world.phys.Vec2;

public class CoolingSteam extends TextureSheetParticle {
    private float xWind;
    private float zWind;
    private final float xMod;
    private final float zMod;
    private final float verticalSpeed = 0.45f;
    private final float speed = 0.6f;

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public CoolingSteam(ClientLevel level, double x, double y, double z) {
        super(level, x, y, z);
        Vec2 wind = ClimateRenderCache.INSTANCE.getWind();
        this.lifetime = 180;
        this.gravity = 0;
        this.age = this.random.nextInt(40);
        this.hasPhysics = false;

        xMod = 1 + this.random.nextFloat();
        zMod = 1 + this.random.nextFloat();

        xWind = wind.x * xMod;
        zWind = wind.y * zMod;
        this.setAlpha(.5f);

        this.scale(this.random.nextFloat() * 8F + 3F);
    }

    private int ticker = 0;

    public void tick() {
        super.tick();
        if (ticker == 100) {
            Vec2 wind = ClimateRenderCache.INSTANCE.getWind();
            xWind = wind.x * xMod;
            zWind = wind.y * zMod;

            // this.setAlpha(1 - (float) age / lifetime);

            ticker = 0;
        } else {
            ticker++;
        }

        this.yd = (age * -0.005f + 1.5) * verticalSpeed;

        this.xd = xWind * speed;
        this.zd = zWind * speed;
    }

}
