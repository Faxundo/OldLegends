package com.github.faxundo.old_legends.config;

import me.shedaniel.autoconfig.ConfigData;

@me.shedaniel.autoconfig.annotation.Config(name = "oldlegends")
@me.shedaniel.autoconfig.annotation.Config.Gui.Background("minecraft:textures/block/stone.png")
public class OLConfig implements ConfigData {

    public boolean enableAwakening = true;
    public int paleGemDesertWellWeight = 1;
    public int paleGemDesertPyramidWeight = 3;
    public int paleGemOceanRuinColdWeight = 5;
    public int paleGemOceanRuinWarmWeight = 5;
    public int paleGemTrailsRuinsCommonWeight = 7;
    public int paleGemTrailsRuinsRareWeight = 9;

    public boolean enableEmeraldMourning = true;
    public float emeraldMourningWeight = 0.6f;
    public int emeraldMourningPercentage = 30;
    public int emeraldMourningAwakePercentage = 60;
}
