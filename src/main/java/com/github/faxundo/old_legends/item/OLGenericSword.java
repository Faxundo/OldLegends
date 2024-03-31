package com.github.faxundo.old_legends.item;

import com.github.faxundo.old_legends.util.OLHelper;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OLGenericSword extends SwordItem {

    private String id;
    private boolean isAwake = false;
    private int amountPassives;
    private boolean useCharges = false;
    private int maxCharges = 1;

    public OLGenericSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
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
