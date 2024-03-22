package com.github.faxundo.old_legends.util;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Style;
import net.minecraft.text.TextColor;
import net.minecraft.world.World;

public class OLHelpers {

    public static Style getStyle(String styleType) {
        int common = 0xFFFFFF;
        int name = 0xFF00FF;
        int name_awake = 0x8033B1;
        int shift = 0xEDE65A;
        int ability_name = 0x2DFEF8;
        int ability_name_awake = 0xBA5BFF;
        int ability = 0xFECB2D;
        int ability_awake = 0xF2A125;

        Style COMMON = Style.EMPTY.withColor(TextColor.fromRgb(common));
        Style NAME = Style.EMPTY.withColor(TextColor.fromRgb(name));
        Style NAME_AWAKE = Style.EMPTY.withColor(TextColor.fromRgb(name_awake));
        Style SHIFT = Style.EMPTY.withColor(TextColor.fromRgb(shift)).withBold(true);
        Style ABILITY_NAME = Style.EMPTY.withColor(TextColor.fromRgb(ability_name)).withItalic(true);
        Style ABILITY_NAME_AWAKE = Style.EMPTY.withColor(TextColor.fromRgb(ability_name_awake)).withItalic(true);
        Style ABILITY = Style.EMPTY.withColor(TextColor.fromRgb(ability));
        Style ABILITY_AWAKE = Style.EMPTY.withColor(TextColor.fromRgb(ability_awake));

        return switch (styleType) {
            case "name" -> NAME;
            case "name_awake" -> NAME_AWAKE;
            case "shift" -> SHIFT;
            case "ability_name" -> ABILITY_NAME;
            case "ability_name_awake" -> ABILITY_NAME_AWAKE;
            case "ability" -> ABILITY;
            case "ability_awake" -> ABILITY_AWAKE;
            default -> COMMON;
        };
    }

    public static double getRandomNumber (int min, int max) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    }

    public static void spawnParticle(World world, ParticleEffect particle, double xpos, double ypos, double zpos,
                                     double xvelocity, double yvelocity, double zvelocity) {
        if (world.isClient) {
            world.addParticle(particle, xpos, ypos, zpos, xvelocity, yvelocity, zvelocity);
        } else if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(particle, xpos, ypos, zpos, 1, xvelocity, yvelocity, zvelocity, 0.1);
        }
    }
}
