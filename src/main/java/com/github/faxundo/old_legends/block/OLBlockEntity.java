package com.github.faxundo.old_legends.block;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.block.entity.EchoBlockEntity;
import com.github.faxundo.old_legends.block.entity.EchoOreEntity;
import com.github.faxundo.old_legends.block.entity.ReliquaryBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static com.github.faxundo.old_legends.OldLegends.LOGGER;
import static com.github.faxundo.old_legends.OldLegends.MOD_NAME;

public class OLBlockEntity {

    public static final BlockEntityType<ReliquaryBlockEntity> RELIQUARY_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, OldLegends.identifier("reliquary"),
                    FabricBlockEntityTypeBuilder.create(ReliquaryBlockEntity::new,
                            OLBlock.RELIQUARY_BLOCK).build());
    public static final BlockEntityType<EchoOreEntity> ECHO_ORE_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, OldLegends.identifier("echo_ore"),
                    FabricBlockEntityTypeBuilder.create(EchoOreEntity::new,
                            OLBlock.ECHO_ORE).build());
    public static final BlockEntityType<EchoBlockEntity> ECHO_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, OldLegends.identifier("echo_block"),
                    FabricBlockEntityTypeBuilder.create(EchoBlockEntity::new,
                            OLBlock.ECHO_BLOCK).build());

    public static void registerOLBlockEntities() {
        LOGGER.info(">>> Registering " + MOD_NAME + " blocks entities.");
    }
}
