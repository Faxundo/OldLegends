package com.github.faxundo.old_legends.compability;

import net.minecraft.util.Identifier;

public class MythicMetals {

    //Identifiers
    public static final Identifier MIDAS_GOLD_INGOT = of("midas_gold_ingot");
    public static final Identifier MIDAS_GOLD_BLOCK = of("midas_gold_block");
    public static final Identifier ORES = of("ores");

    public static Identifier of(String path) {
        return Identifier.of("mythicmetals", path);
    }
}
