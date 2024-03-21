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
    protected Style ABILITY_NAME = OLHelpers.getStyle("ability_name");
    protected Style ABILITY_NAME_AWAKE = OLHelpers.getStyle("ability_name_awake");
    protected Style ABILITY = OLHelpers.getStyle("ability");
    protected Style ABILITY_AWAKE = OLHelpers.getStyle("ability_awake");
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
            if (isAwake) {
                tooltip.add(Text.literal(""));
                tooltip.add(Text.translatable(id+"_name_1").setStyle(ABILITY_NAME));
                tooltip.add(Text.translatable(id+"_awake_1").setStyle(ABILITY));
                tooltip.add(Text.translatable(id+"_name_2").setStyle(ABILITY_NAME));
                tooltip.add(Text.translatable(id+"_awake_2").setStyle(ABILITY));
                tooltip.add(Text.translatable(id+"_name_3").setStyle(ABILITY_NAME_AWAKE));
                tooltip.add(Text.translatable(id+"_awake_3").setStyle(ABILITY_AWAKE));
                tooltip.add(Text.literal(""));
            } else {
                tooltip.add(Text.literal(""));
                tooltip.add(Text.translatable(id+"_name_1").setStyle(ABILITY_NAME));
                tooltip.add(Text.translatable(id+"_1").setStyle(ABILITY));
                tooltip.add(Text.translatable(id+"_name_2").setStyle(ABILITY_NAME));
                tooltip.add(Text.translatable(id+"_2").setStyle(ABILITY));
            }
        } else {
            tooltip.add(Text.literal(""));
            tooltip.add(Text.translatable("tooltip.oldlegends.shift").setStyle(SHIFT));
            tooltip.add(Text.literal(""));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
