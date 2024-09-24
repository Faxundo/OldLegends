package com.github.faxundo.old_legends;

import com.github.faxundo.old_legends.block.OLBlock;
import com.github.faxundo.old_legends.entity.OLEntities;
import com.github.faxundo.old_legends.event.KeyInputHandler;
import com.github.faxundo.old_legends.networking.OLPacket;
import com.github.faxundo.old_legends.particle.OLParticle;
import com.github.faxundo.old_legends.particle.custom.EchoPickAxeParticle;
import com.github.faxundo.old_legends.particle.custom.LockParticle;
import com.github.faxundo.old_legends.screen.OLScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class OldLegendsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		KeyInputHandler.register();

		OLScreen.registerScreen();

		OLPacket.registerS2CPackets();

		OLParticle.registerParticles();
		ParticleFactoryRegistry.getInstance().register(OLParticle.LOCK, LockParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(OLParticle.ECHO_PICKAXE, EchoPickAxeParticle.Factory::new);
		BlockRenderLayerMap.INSTANCE.putBlock(OLBlock.ECHO_ORE, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(OLBlock.ECHO_BLOCK, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(OLBlock.RUNE_TABLE, RenderLayer.getCutoutMipped());

		EntityRendererRegistry.register(OLEntities.ECHO_PICKAXE_PROJECTILE, FlyingItemEntityRenderer::new);
	}
}