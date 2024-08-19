package com.github.faxundo.old_legends.entity;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.entity.custom.EchoPickAxeProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class OLEntities {

    public static final EntityType<EchoPickAxeProjectileEntity> ECHO_PICKAXE_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE, OldLegends.identifier("echo_pickaxe_projectile"),
            EntityType.Builder.<EchoPickAxeProjectileEntity>create(EchoPickAxeProjectileEntity::new, SpawnGroup.MISC)
                    .dimensions(0.25f, 0.25f).build());
}
