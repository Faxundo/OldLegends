package com.github.faxundo.old_legends.mixin;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.block.OLBlock;
import com.github.faxundo.old_legends.item.OLItem;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Shadow
    private ItemModels models;

    BakedModel getTexture(String texture) {
        return models.getModelManager().getModel(new ModelIdentifier(OldLegends.MOD_ID, texture, "inventory"));
    }

    @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel useSwallowsStormModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (!(renderMode == ModelTransformationMode.GUI)) {
            if (stack.isOf(OLItem.SWALLOWS_STORM)) {
                return getTexture("swallows_storm_3d");
            } else if (stack.isOf(OLItem.SWALLOWS_STORM_AWAKE)) {
                return getTexture("swallows_storm_awake_3d");
            }
        }
        if (stack.isOf(Item.fromBlock(OLBlock.RELIQUARY_BLOCK))) {
            return getTexture("reliquary_item");
        }

        return value;
    }
}