package com.github.faxundo.old_legends.item.generic;

import com.github.faxundo.old_legends.util.OLHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class OLGenericShield extends ShieldItem {

    private String id;
    private boolean isAwake = false;
    private int amountPassives;
    private boolean useCharges = false;
    private int maxCharges = 1;

    public OLGenericShield(Settings settings) {
        super(settings.fireproof());
    }


    public void setId(String id) {
        this.id = "tooltip.old_legends." + id;
    }

    public String getId() {
        return id;
    }

    public void setAwake(boolean awake) {
        this.isAwake = awake;
    }

    public boolean isAwake() {
        return isAwake;
    }

    public void setUseCharges(boolean useCharges) {
        this.useCharges = useCharges;
    }

    public boolean isUseCharges() {
        return useCharges;
    }

    public void setMaxCharges(int maxCharges) {
        this.maxCharges = maxCharges;
    }

    public int getMaxCharges() {
        return maxCharges;
    }

    public void setAmountPassives(int amountPassives) {
        this.amountPassives = amountPassives;
    }

    public int getAmountPassives() {
        return amountPassives;
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
