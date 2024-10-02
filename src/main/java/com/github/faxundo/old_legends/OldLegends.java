package com.github.faxundo.old_legends;

import com.github.faxundo.old_legends.block.OLBlock;
import com.github.faxundo.old_legends.block.OLBlockEntity;
import com.github.faxundo.old_legends.effect.OLEffect;
import com.github.faxundo.old_legends.entity.OLEntities;
import com.github.faxundo.old_legends.entity.custom.Vengeful;
import com.github.faxundo.old_legends.event.*;
import com.github.faxundo.old_legends.event.callback.ShieldBlockCallback;
import com.github.faxundo.old_legends.item.OLItem;
import com.github.faxundo.old_legends.item.OLItemGroup;
import com.github.faxundo.old_legends.networking.OLPacket;
import com.github.faxundo.old_legends.screen.OLScreenHandler;
import com.github.faxundo.old_legends.sound.OLSound;
import com.github.faxundo.old_legends.util.LootTableModifier;
import com.github.faxundo.old_legends.util.OLDataComponent;
import com.github.faxundo.old_legends.util.OLPredicateProvider;
import com.github.faxundo.old_legends.util.config.OLConfig;
import com.github.faxundo.old_legends.villager.OLTrades;
import com.github.faxundo.old_legends.villager.OLVillager;
import com.github.faxundo.old_legends.world.village.OLStructures;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OldLegends implements ModInitializer {

    public static final String MOD_ID = "old_legends";
    public static final String MOD_NAME = "Old Legends";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static OLConfig CONFIG = new OLConfig();

    @Override
    public void onInitialize() {
        LOGGER.info("[ " + MOD_NAME + " ]" + " The old secrets will be revealed");

        OLPredicateProvider.registerPredicateProvider();
        OLItem.registerOLItems();
        OLItemGroup.registerItemGroup();

        OLPacket.registerPayloads();
        OLPacket.registerC2SPackets();

        OLBlock.registerOLBlocks();
        OLScreenHandler.registerScreenHandler();
        OLBlockEntity.registerOLBlockEntities();

        OLEffect.registerEffects();
        OLDataComponent.registerDataComponents();

        LootTableModifier.modifyLootTables();

        OLSound.registerSounds();
        Vengeful.createMourningMobAttributes();
        OLEntities.registerEntities();

        OLVillager.registerVillagers();
        OLTrades.registerOLTrades();
        OLStructures.registerVillageStructure();

        ServerTickEvents.START_SERVER_TICK.register(new ServerTickHandler());
        PlayerBlockBreakEvents.AFTER.register(new PlayerBlockBreakAfterHandler());
        PlayerBlockBreakEvents.BEFORE.register(new PlayerBlockBreakBeforeHandler());
        ShieldBlockCallback.EVENT.register(new ShieldBlockHandler());
        UseBlockCallback.EVENT.register(new UseBlockHandler());

        AutoConfig.register(OLConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(OLConfig.class).getConfig();

//        DeeperAndDarker.modExists();
    }

    public static Identifier identifier(String path) {
        return Identifier.of(MOD_ID, path);
    }

}
