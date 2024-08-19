package com.github.faxundo.old_legends;

import com.github.crimsondawn45.fabricshieldlib.lib.event.ShieldBlockCallback;
import com.github.faxundo.old_legends.block.OLBlock;
import com.github.faxundo.old_legends.block.OLBlockEntity;
import com.github.faxundo.old_legends.effect.OLEffect;
import com.github.faxundo.old_legends.entity.custom.Vengeful;
import com.github.faxundo.old_legends.event.PlayerBlockBreakAfterHandler;
import com.github.faxundo.old_legends.event.ServerTickHandler;
import com.github.faxundo.old_legends.event.ShieldBlockHandler;
import com.github.faxundo.old_legends.item.OLItem;
import com.github.faxundo.old_legends.item.OLItemGroup;
import com.github.faxundo.old_legends.networking.OLPacket;
import com.github.faxundo.old_legends.particle.OLParticle;
import com.github.faxundo.old_legends.screen.OLScreenHandler;
import com.github.faxundo.old_legends.sound.OLSound;
import com.github.faxundo.old_legends.util.LootTableModifier;
import com.github.faxundo.old_legends.util.OLDataComponent;
import com.github.faxundo.old_legends.util.OLPredicateProvider;
import com.github.faxundo.old_legends.util.config.OLConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
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
        LOGGER.info(MOD_NAME + " is here!");

        OLPredicateProvider.registerPredicateProvider();
        OLItem.registerOLItems();
        OLItemGroup.registerItemGroup();
        OLPacket.registerC2SPackets();

        OLBlock.registerOLBlocks();
        OLScreenHandler.registerScreenHandler();
        OLBlockEntity.registerOLBlockEntities();

        OLParticle.registerParticles();
        OLEffect.registerEffects();
        OLDataComponent.registerDataComponents();

        LootTableModifier.modifyLootTables();

        OLSound.registerSounds();
        Vengeful.createMourningMobAttributes();

        ShieldBlockCallback.EVENT.register(new ShieldBlockHandler());
        ServerTickEvents.START_SERVER_TICK.register(new ServerTickHandler());

        PlayerBlockBreakEvents.AFTER.register(new PlayerBlockBreakAfterHandler());

//        OLRecipe.registerRecipes();

        AutoConfig.register(OLConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(OLConfig.class).getConfig();

    }

    public static Identifier identifier(String path) {
        return Identifier.of(OldLegends.MOD_ID, path);
    }

}
