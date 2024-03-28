package com.github.faxundo.old_legends.util.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@me.shedaniel.autoconfig.annotation.Config(name = "old_legends")
@me.shedaniel.autoconfig.annotation.Config.Gui.Background("minecraft:textures/block/stone.png")
public class OLConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip
    public boolean enableAwakening = true;
    public int paleGemDesertWellWeight = 1;
    public int paleGemDesertPyramidWeight = 3;
    public int paleGemOceanRuinColdWeight = 5;
    public int paleGemOceanRuinWarmWeight = 5;
    public int paleGemTrailsRuinsCommonWeight = 7;
    public int paleGemTrailsRuinsRareWeight = 9;

    @ConfigEntry.Gui.CollapsibleObject
    public final EmeraldMourning emeraldMourning = new EmeraldMourning();

    public static class EmeraldMourning {
        public boolean enableEmeraldMourning = true;
        public float emeraldMourningWeight = 0.6f;
        public int emeraldMourningPercentageIllager = 30;

        public int emeraldMourningAwakePercentageIllager = 60;
        @ConfigEntry.Gui.Tooltip
        public int emeraldMourningAwakeCooldown = 140;
        public int emeraldMourningAwakePercentageConsumeDurability = 5;
        @ConfigEntry.Gui.Tooltip
        public int emeraldMourningAwakeMobTime = 600;
    }

    @ConfigEntry.Gui.CollapsibleObject
    public final SwallowsStorm swallowsStorm = new SwallowsStorm();

    public static class SwallowsStorm {
        public boolean enableSwallowsStorm = true;
        public float swallowsStormWeight = 0.5f;
    }
}
