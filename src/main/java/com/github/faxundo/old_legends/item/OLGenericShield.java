package com.github.faxundo.old_legends.item;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;
import com.github.faxundo.old_legends.util.OLHelpers;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Style;
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
    private final Style NAME = OLHelpers.getStyle("name");
    private final Style NAME_AWAKE = OLHelpers.getStyle("name_awake");
    private final Style ABILITY_NAME = OLHelpers.getStyle("ability_name");
    private final Style ABILITY_NAME_AWAKE = OLHelpers.getStyle("ability_name_awake");
    private final Style ABILITY = OLHelpers.getStyle("ability");
    private final Style ABILITY_AWAKE = OLHelpers.getStyle("ability_awake");
    private final Style SHIFT = OLHelpers.getStyle("shift");
    private final Style CHARGES = OLHelpers.getStyle("charges");

    public OLGenericShield(Settings settings, int coolDownTicks, int enchantability, TagKey<Item> repairItemTag) {
        super(settings, coolDownTicks, enchantability, repairItemTag);
    }

    public void setId(String id) {
        this.id = "tooltip.old_legends." + id;
    }

    public void setAwake(boolean awake) {
        this.isAwake = awake;
    }

    public void setUseCharges(boolean useCharges) {
        this.useCharges = useCharges;
    }

    public void setMaxCharges(int maxCharges) {
        this.maxCharges = maxCharges;
    }

    public int getMaxCharges() {
        return maxCharges;
    }

    public boolean getAwake() {
        return isAwake;
    }

    public void setAmountPassives(int amountPassives) {
        this.amountPassives = amountPassives;
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
            for (int i = 1; i <= amountPassives; i++) {
                String j = String.valueOf(i);
                tooltip.add(Text.translatable(id + "_name_" + j).setStyle(ABILITY_NAME));
                tooltip.add(Text.translatable(id + "_" + j).setStyle(ABILITY));
            }
            if (isAwake) {
                for (int i = 1; i <= amountPassives; i++) {
                    String j = String.valueOf(i);
                    tooltip.add(Text.translatable(id + "_name_" + j).setStyle(ABILITY_NAME));
                    tooltip.add(Text.translatable(id + "_awake_" + j).setStyle(ABILITY));
                }
                Text textActiveAbility = Text.literal("[ " + "←" + " ]")
                        .append(id + "_awake_" + amountPassives + 1)
                        .setStyle(ABILITY_AWAKE);
                tooltip.add(Text.translatable(id + "_name_" + amountPassives + 1).setStyle(ABILITY_NAME_AWAKE));
                tooltip.add(textActiveAbility);
            }

        } else {
            tooltip.add(Text.literal(""));
            tooltip.add(Text.translatable("tooltip.old_legends.shift").setStyle(SHIFT));
        }
        if (useCharges) {
            tooltip.add(Text.literal(""));
            Text textCharges = Text.translatable("tooltip.old_legends.swallows_storm_charges")
                    .append(": " + stack.getNbt().getInt("old_legends") + "/" + maxCharges)
                    .setStyle(CHARGES);
            tooltip.add(textCharges);
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
