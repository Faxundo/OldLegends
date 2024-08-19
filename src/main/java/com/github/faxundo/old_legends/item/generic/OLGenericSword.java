package com.github.faxundo.old_legends.item.generic;

import com.github.faxundo.old_legends.util.OLHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class OLGenericSword extends SwordItem {

    private String id;
    private boolean isAwake = false;
    private int amountPassives;
    private boolean useCharges = false;
    private int maxCharges = 1;

    public OLGenericSword(ToolMaterial toolMaterial, Item.Settings settings) {
        super(toolMaterial, settings.fireproof());
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
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        OLHelper.appendTooltipHelper(stack, tooltip, isAwake(), getAmountPassives(), getId(), isUseCharges(), getMaxCharges());
        super.appendTooltip(stack, context, tooltip, type);
    }
}
