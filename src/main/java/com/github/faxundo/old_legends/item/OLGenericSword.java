package com.github.faxundo.old_legends.item;

import com.github.faxundo.old_legends.util.OLHelpers;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OLGenericSword extends SwordItem {

    private String id;
    private boolean isAwake;
    protected Style NAME = OLHelpers.getStyle("name");
    protected Style NAME_AWAKE = OLHelpers.getStyle("name_awake");
    protected Style ABILITY = OLHelpers.getStyle("ability");
    protected Style SHIFT = OLHelpers.getStyle("shift");

    public OLGenericSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    public void setId(String id) {
        this.id = "tooltip.oldlegends." + id;
    }

    public void setAwake(boolean awake) {
        isAwake = awake;
    }

    @Override
    public Text getName(ItemStack itemStack) {
        if (isAwake) {
            return Text.translatable(this.getTranslationKey(itemStack)).setStyle(NAME_AWAKE);
        } else {
            return Text.translatable(this.getTranslationKey(itemStack)).setStyle(NAME);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.literal(""));
            tooltip.add(Text.translatable(id+"_1").setStyle(ABILITY));
            tooltip.add(Text.literal(""));
            tooltip.add(Text.translatable(id+"_2").setStyle(ABILITY));
            tooltip.add(Text.literal(""));
            if (isAwake) {
                tooltip.add(Text.translatable(id+"_3").setStyle(ABILITY));
                tooltip.add(Text.literal(""));
            }
        } else {
            tooltip.add(Text.literal(""));
            tooltip.add(Text.translatable("tooltip.oldlegends.shift").setStyle(SHIFT));
            tooltip.add(Text.literal(""));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
