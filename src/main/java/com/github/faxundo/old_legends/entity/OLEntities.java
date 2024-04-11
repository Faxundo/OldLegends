package com.github.faxundo.old_legends.entity;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.entity.custom.EchoPickAxeProjectileEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class OLEntities {

    public static final EntityType<EchoPickAxeProjectileEntity> ECHO_PICKAXE_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE, OldLegends.identifier("echo_pickaxe_projectile"),
            FabricEntityTypeBuilder.<EchoPickAxeProjectileEntity>create(SpawnGroup.MISC, EchoPickAxeProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f,0.25f)).build());
}
