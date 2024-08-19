package com.github.faxundo.old_legends.particle.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class EchoPickAxeParticle extends SpriteBillboardParticle {

    public EchoPickAxeParticle(ClientWorld level, double xCoord, double yCoord, double zCoord,
                               SpriteProvider spriteSet, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord, xd, yd, zd);
        this.velocityMultiplier = 0.6F;
        this.x = xCoord;
        this.y = yCoord;
        this.z = zCoord;
        this.scale *= 2.5F;
        this.maxAge = 35;
        this.setSpriteForAge(spriteSet);
        this.red = 1f;
        this.green = 1f;
        this.blue = 1f;
        this.angle = 0.0f;
        this.prevAngle = 0.0f;
    }

    @Override
    public void tick() {
        super.tick();
        prevAngle = this.angle;
        this.angle += 0.03f;
        fadeOut();
    }

    private void fadeOut() {
        this.alpha = (-(1 / (float) maxAge) * age + 1);
    }

    @Override
    public Particle move(float speed) {
        return super.move(speed);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public float getSize(float tickDelta) {
        return this.scale;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider sprites;

        public Factory(SpriteProvider spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientWorld level, double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new EchoPickAxeParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}
