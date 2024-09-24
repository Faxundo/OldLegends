package com.github.faxundo.old_legends.util;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.util.component.SwordDataComponent;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.UUID;

import static com.github.faxundo.old_legends.OldLegends.LOGGER;
import static com.github.faxundo.old_legends.OldLegends.MOD_NAME;

public class OLDataComponent {

    public static final ComponentType<Integer> CHARGES = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(OldLegends.MOD_ID, "charges"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );
    public static final ComponentType<String> OWNER = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(OldLegends.MOD_ID, "owner"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );
    public static final ComponentType<String> PAGES = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(OldLegends.MOD_ID, "pages"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );

    public static void registerDataComponents() {
        LOGGER.info(">>> Registering " + MOD_NAME + " data components.");
    }

}
