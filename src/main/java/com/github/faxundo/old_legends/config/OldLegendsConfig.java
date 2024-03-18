package com.github.faxundo.old_legends.config;

import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "oldlegends")
@Config.Gui.Background("minecraft:textures/block/stone.png")
public class OldLegendsConfig implements ConfigData {

    @Comment("Text")
    public boolean test = true;

}
