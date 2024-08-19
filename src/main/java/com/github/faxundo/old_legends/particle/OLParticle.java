package com.github.faxundo.old_legends.particle;

import com.github.faxundo.old_legends.OldLegends;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class OLParticle {
    public static final SimpleParticleType LOCK = FabricParticleTypes.simple();
    public static final SimpleParticleType ECHO_PICKAXE = FabricParticleTypes.simple();

    public static void registerParticles() {
        Registry.register(Registries.PARTICLE_TYPE, OldLegends.identifier("lock"), LOCK);
        Registry.register(Registries.PARTICLE_TYPE, OldLegends.identifier("echo_pickaxe"), ECHO_PICKAXE);
    }
}
