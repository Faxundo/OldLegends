package com.github.faxundo.old_legends.particle.custom;

import com.github.faxundo.old_legends.OldLegends;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class LockParticle extends SpriteBillboardParticle {

    private int moveUp = 0;
    private int moveDown = 0;
    private int rest = 0;

    public LockParticle(ClientWorld level, double xCoord, double yCoord, double zCoord,
                        SpriteProvider spriteSet, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord, xd, yd, zd);
        this.velocityMultiplier = 0.6F;
        this.x = xCoord;
        this.y = yCoord;
        this.z = zCoord;
        this.scale *= 1.5F;
        this.maxAge = 3000;
        this.setSpriteForAge(spriteSet);
        this.red = 1f;
        this.green = 1f;
        this.blue = 1f;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        super.tick();
        if (world.getTimeOfDay() == OldLegends.CONFIG.reliquary.grinningHoarderTime) age = 3000;
        if (rest != 10) {
            rest++;
            if (moveUp <= 0 && moveDown != 0) {
                moveUp = 0;
                moveDown = 0;
            } else if (moveUp <= 30 && moveDown == 0) {
                moveUp++;
                move(0, 0.01, 0);
            } else {
                moveUp--;
                moveDown++;
                move(0, -0.01, 0);
            }
        } else {
            rest--;
        }
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
            return new LockParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}
