package com.github.faxundo.old_legends;

import com.github.faxundo.old_legends.config.OldLegendsConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OldLegends implements ModInitializer {

    public static final String MOD_ID = "oldlegends";
    public static final String MOD_NAME = "Old Legends";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static OldLegendsConfig CONFIG = new OldLegendsConfig();

    @Override
    public void onInitialize() {
        LOGGER.info(MOD_NAME + "is here");

        AutoConfig.register(OldLegendsConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(OldLegendsConfig.class).getConfig();
    }
}
