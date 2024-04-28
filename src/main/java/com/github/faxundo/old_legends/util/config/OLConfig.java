package com.github.faxundo.old_legends.util.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@me.shedaniel.autoconfig.annotation.Config(name = "old_legends")
@me.shedaniel.autoconfig.annotation.Config.Gui.Background("minecraft:textures/block/stone.png")
public class OLConfig implements ConfigData {



    @ConfigEntry.Gui.Tooltip
    public boolean enableAwakening = true;
    public int paleGemDesertWellWeight = 1;
    public int paleGemDesertPyramidWeight = 2;
    public int paleGemOceanRuinColdWeight = 3;
    public int paleGemOceanRuinWarmWeight = 3;
    public int paleGemTrailsRuinsCommonWeight = 4;
    public int paleGemTrailsRuinsRareWeight = 5;
    public int deathRuneSnifferDigging = 1;
    public int skyRuneSnifferDigging = 1;

    @ConfigEntry.Gui.CollapsibleObject
    public final Reliquary reliquary = new Reliquary();

    public static class Reliquary {
        @ConfigEntry.Gui.Tooltip
        public int grinningHoarderTime = 18000;
        public float ancientCityWeight = 0.5f;
        public float strongholdWeight = 0.5f;
        public int desertPyramidWeight = 2;
        public int ruinsCommonWeight = 3;
        public int ruinsRareWeight = 4;
    }

    @ConfigEntry.Gui.CollapsibleObject
    public final EmeraldMourning emeraldMourning = new EmeraldMourning();

    public static class EmeraldMourning {
        public boolean enable = true;
        public float weight = 0.6f;
        public int percentageIllager = 30;

        public int percentageIllagerAwake = 60;
        @ConfigEntry.Gui.Tooltip
        public int cooldown = 140;
        public int consumeDurability = 5;
        @ConfigEntry.Gui.Tooltip
        public int mobTime = 600;
        public float weightPage = 0.6f;
    }

    @ConfigEntry.Gui.CollapsibleObject
    public final SwallowsStorm swallowsStorm = new SwallowsStorm();

    public static class SwallowsStorm {
        public boolean enable = true;
        public float weight = 0.5f;
        public int maxCharges = 30;
        public int stormHeal = 6;
        @ConfigEntry.Gui.Tooltip
        public int stormHealLost = 10;
        public int stormChance = 5;
        @ConfigEntry.Gui.Tooltip
        public int stormCooldown = 200;
        @ConfigEntry.Gui.Tooltip
        public int consumeDurability = 1;
        @ConfigEntry.Gui.Tooltip
        public int maxChargesAwake = 60;
        public int electrifiedDamage = 10;
        public double electrifiedStrength = 0.5d;
        public int stormHealAwake = 10;
        public double electrifiedStrengthAwake = 1d;
        public int explosiveCooldown = 100;
        public int explosiveDamage = 15;
        public int explosiveRange = 10;
        public int explosiveKnockback = 2;
        public float weightPage = 0.6f;
    }

    @ConfigEntry.Gui.CollapsibleObject
    public final FlutterEcho flutterEcho = new FlutterEcho();

    public static class FlutterEcho {
        public boolean enable = true;
        public float weight = 0.3f;
        public int mineChance = 30;
        public int attackChance = 30;
        public int mineChanceAwake = 50;
        public int attackChanceAwake = 50;
        @ConfigEntry.Gui.Tooltip
        public int cooldown = 200;
        @ConfigEntry.Gui.Tooltip
        public int consumeDurability = 4;
        @ConfigEntry.Gui.Tooltip
        public int countLimit = 3;
        public float weightPage = 0.6f;
    }
}
