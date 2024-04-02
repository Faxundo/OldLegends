package com.github.faxundo.old_legends.item;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;
import com.github.faxundo.old_legends.util.OLHelper;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OLGenericShield extends FabricShieldItem {

    private String id;
    private boolean isAwake = false;
    private int amountPassives;
    private boolean useCharges = false;
    private int maxCharges = 1;

    public OLGenericShield(Settings settings, int coolDownTicks, int enchantability, TagKey<Item> repairItemTag) {
        super(settings.fireproof(), coolDownTicks, enchantability, repairItemTag);
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
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        OLHelper.appendTooltipHelper(stack, tooltip, isAwake(), getAmountPassives(), getId(), isUseCharges(), getMaxCharges());
        super.appendTooltip(stack, world, tooltip, context);
    }
}
