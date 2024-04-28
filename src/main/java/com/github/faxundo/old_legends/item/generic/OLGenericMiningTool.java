package com.github.faxundo.old_legends.item.generic;

import com.github.faxundo.old_legends.util.OLHelper;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OLGenericMiningTool extends MiningToolItem {

    private String id;
    private boolean isAwake = false;
    private int amountPassives;
    private boolean useCharges = false;
    private int maxCharges = 1;

    public OLGenericMiningTool(float attackDamage, float attackSpeed, ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings) {
        super(attackDamage, attackSpeed, material, effectiveBlocks, settings.fireproof());
    }


    public void setId(String id) {
        this.id = "tooltip.old_legends." + id;
    }

    public String getId() {
        return id;
    }

    public void setAwake(boolean awake) {
        isAwake = awake;
    }

    public boolean isAwake() {
        return isAwake;
    }

    public void setAmountPassives(int amountPassives) {
        this.amountPassives = amountPassives;
    }

    public int getAmountPassives() {
        return amountPassives;
    }

    public boolean isUseCharges() {
        return useCharges;
    }

    public int getMaxCharges() {
        return maxCharges;
    }

    public void setMaxCharges(int maxCharges) {
        this.maxCharges = maxCharges;
    }

    @Override
    public Text getName(ItemStack itemStack) {
        return OLHelper.getNameHelper(this.getDefaultStack(), isAwake());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        OLHelper.appendTooltipHelper(stack, tooltip, isAwake(), getAmountPassives(), getId(), isUseCharges(), getMaxCharges());
        super.appendTooltip(stack, world, tooltip, context);
    }
}
