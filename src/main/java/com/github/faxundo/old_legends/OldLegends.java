package com.github.faxundo.old_legends;

import com.github.crimsondawn45.fabricshieldlib.lib.event.ShieldBlockCallback;
import com.github.faxundo.old_legends.entity.MourningMob;
import com.github.faxundo.old_legends.event.AttackEntityHandler;
import com.github.faxundo.old_legends.event.ServerTickHandler;
import com.github.faxundo.old_legends.event.ShieldBlockHandler;
import com.github.faxundo.old_legends.item.OLItem;
import com.github.faxundo.old_legends.item.OLItemGroup;
import com.github.faxundo.old_legends.item.OLPredicateProvider;
import com.github.faxundo.old_legends.networking.OLPackets;
import com.github.faxundo.old_legends.sound.OLSound;
import com.github.faxundo.old_legends.util.LootTableModifiers;
import com.github.faxundo.old_legends.util.config.OLConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OldLegends implements ModInitializer {

    public static final String MOD_ID = "old_legends";
    public static final String MOD_NAME = "Old Legends";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static OLConfig CONFIG = new OLConfig();

    @Override
    public void onInitialize() {
        LOGGER.info(MOD_NAME + "is here");

        OLPredicateProvider.registerPredicateProvider();
        OLItem.registerOldLegendsItems();
        OLItemGroup.registerItemGroup();
        OLPackets.registerC2SPackets();

        LootTableModifiers.modifyLootTables();

        OLSound.registerSounds();
        MourningMob.createMourningMobAttributes();

        ShieldBlockCallback.EVENT.register(new ShieldBlockHandler());
        ServerTickHandler.EVENT.register(new ServerTickHandler());
        AttackEntityHandler.EVENT.register(new AttackEntityHandler());

        AutoConfig.register(OLConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(OLConfig.class).getConfig();
    }
}
