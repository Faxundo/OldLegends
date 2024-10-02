package com.github.faxundo.old_legends.integration;

import net.minecraft.util.Identifier;

public class MythicMetals {

    public static final String MOD_ID = "mythicmetals";

    public static final Identifier MIDAS_GOLD_INGOT = of("midas_gold_ingot");
    public static final Identifier MIDAS_GOLD_BLOCK = of("midas_gold_block");
    public static final Identifier ORES = of("ores");

    public static Identifier of(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
