package com.github.faxundo.old_legends.util;

import net.minecraft.text.Style;
import net.minecraft.text.TextColor;

public class OLHelpers {

    public static Style getStyle(String styleType) {
        int common = 0xFFFFFF;
        int name = 0xFF00FF;
        int name_awake = 0x6A2795;
        int shift = 0x239F30;
        int ability = 0xE6BA24;

        Style COMMON = Style.EMPTY.withColor(TextColor.fromRgb(common));
        Style NAME = Style.EMPTY.withColor(TextColor.fromRgb(name));
        Style NAME_AWAKE = Style.EMPTY.withColor(TextColor.fromRgb(name_awake));
        Style SHIFT = Style.EMPTY.withColor(TextColor.fromRgb(shift));
        Style ABILITY = Style.EMPTY.withColor(TextColor.fromRgb(ability));

        return switch (styleType) {
            case "name" -> NAME;
            case "name_awake" -> NAME_AWAKE;
            case "shift" -> SHIFT;
            case "ability" -> ABILITY;
            default -> COMMON;
        };
    }
}
