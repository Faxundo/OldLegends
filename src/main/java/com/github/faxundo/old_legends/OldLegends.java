package com.github.faxundo.old_legends;

import com.github.faxundo.old_legends.entity.MourningMob;
import com.github.faxundo.old_legends.util.config.OLConfig;
import com.github.faxundo.old_legends.item.OLItem;
import com.github.faxundo.old_legends.item.OLItemGroup;
import com.github.faxundo.old_legends.util.LootTableModifiers;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OldLegends implements ModInitializer {

    public static final String MOD_ID = "oldlegends";
    public static final String MOD_NAME = "Old Legends";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static OLConfig CONFIG = new OLConfig();

    @Override
    public void onInitialize() {
        LOGGER.info(MOD_NAME + "is here");

        OLItemGroup.registerItemGroup();

        OLItem.registerOldLegendsItems();
        LootTableModifiers.modifyLootTables();

        MourningMob.createMourningMobAttributes();

        AutoConfig.register(OLConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(OLConfig.class).getConfig();
    }
}
