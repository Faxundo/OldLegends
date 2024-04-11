package com.github.faxundo.old_legends.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class OLHelperParticle {


    public static void spawnParticle(World world, ParticleEffect particle, double xpos, double ypos, double zpos,
                                     double xvelocity, double yvelocity, double zvelocity) {
        if (world.isClient) {
            world.addParticle(particle, xpos, ypos, zpos, xvelocity, yvelocity, zvelocity);
        } else if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(particle, xpos, ypos, zpos, 1, xvelocity, yvelocity, zvelocity, 0.1);
        }
    }

    public static void spawnCircularParticlesAroundPlayer(PlayerEntity player, World world, ParticleEffect particle, int numParticles, double radius) {
        // Central Circle
        spawnParticlesInCircle(player, world, particle, numParticles, radius);

        // Generate particles in three smaller inner circles
        double innerRadius = radius * 0.5; // Radius of smallest inner circle
        double distanceBetweenCircles = radius * 0.25; // Distance between circles
        spawnParticlesInCircle(player, world, particle, numParticles, innerRadius);
        spawnParticlesInCircle(player, world, particle, numParticles, innerRadius - distanceBetweenCircles);
        spawnParticlesInCircle(player, world, particle, numParticles, innerRadius - 2 * distanceBetweenCircles);
    }

    // Generates particles in a circular radius
    public static void spawnParticlesInCircle(PlayerEntity player, World world, ParticleEffect particle, int numParticles, double radius) {
        for (int i = 0; i < numParticles; i++) {
            double angle = Math.random() * Math.PI * 2;
            double xOffset = Math.cos(angle) * radius;
            double zOffset = Math.sin(angle) * radius;

            double posX = player.getX() + xOffset;
            double posY = player.getY() + 1;
            double posZ = player.getZ() + zOffset;

            spawnParticle(world, particle, posX, posY, posZ, 1, 0, 0);
        }
    }
}
