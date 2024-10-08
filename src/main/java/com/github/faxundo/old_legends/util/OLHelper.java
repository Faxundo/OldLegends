package com.github.faxundo.old_legends.util;

import com.github.faxundo.old_legends.item.custom.BookOfTheLegends;
import com.github.faxundo.old_legends.item.custom.SwallowsStorm;
import com.github.faxundo.old_legends.item.generic.OLGenericItem;
import com.github.faxundo.old_legends.item.generic.OLGenericPage;
import com.github.faxundo.old_legends.item.generic.OLGenericRune;
import com.github.faxundo.old_legends.villager.OLVillager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.World;

import java.util.List;

public class OLHelper {

    public static final String SHIFT_TOOLTIP = "tooltip.old_legends.shift";

    private static final Style UNCOMMON = getStyle("uncommon");
    private static final Style RARE = getStyle("rare");
    private static final Style NAME = getStyle("name");
    private static final Style NAME_AWAKE = getStyle("name_awake");
    private static final Style ABILITY_NAME = getStyle("ability_name");
    private static final Style ABILITY_NAME_AWAKE = getStyle("ability_name_awake");
    private static final Style ABILITY = getStyle("ability");
    private static final Style ABILITY_AWAKE = getStyle("ability_awake");
    private static final Style SHIFT = getStyle("shift");
    private static final Style CHARGES = getStyle("charges");
    private static final Style SPECIAL = getStyle("special");
    private static final Style ERROR = getStyle("error");

    public static Style getStyle(String styleType) {
        int common = 0xFFFFFF;
        int uncommon = 0xE2DF1D;
        int rare = 0x1DE2C4;
        int name = 0xE9581D;
        int name_awake = 0x911AE7;
        int shift = 0xEDE65A;
        int ability_name = 0x2DFEF8;
        int ability_name_awake = 0xBA5BFF;
        int ability = 0xFECB2D;
        int ability_awake = 0xF2A125;
        int charges = 0xCDFF00;
        int special = 0xE71AD7;
        int error = 0xDA2424;
        int message = 0x3ADA24;

        Style COMMON = Style.EMPTY.withColor(TextColor.fromRgb(common));
        Style UNCOMMON = Style.EMPTY.withColor(TextColor.fromRgb(uncommon));
        Style RARE = Style.EMPTY.withColor(TextColor.fromRgb(rare));
        Style NAME = Style.EMPTY.withColor(TextColor.fromRgb(name));
        Style NAME_AWAKE = Style.EMPTY.withColor(TextColor.fromRgb(name_awake));
        Style SHIFT = Style.EMPTY.withColor(TextColor.fromRgb(shift)).withBold(true);
        Style ABILITY_NAME = Style.EMPTY.withColor(TextColor.fromRgb(ability_name)).withItalic(true);
        Style ABILITY_NAME_AWAKE = Style.EMPTY.withColor(TextColor.fromRgb(ability_name_awake)).withItalic(true);
        Style ABILITY = Style.EMPTY.withColor(TextColor.fromRgb(ability));
        Style ABILITY_AWAKE = Style.EMPTY.withColor(TextColor.fromRgb(ability_awake));
        Style CHARGES = Style.EMPTY.withColor(TextColor.fromRgb(charges));
        Style SPECIAL = Style.EMPTY.withColor(TextColor.fromRgb(special));
        Style ERROR = Style.EMPTY.withColor(TextColor.fromRgb(error));
        Style MESSAGE = Style.EMPTY.withColor(TextColor.fromRgb(message));

        return switch (styleType) {
            case "uncommon" -> UNCOMMON;
            case "rare" -> RARE;
            case "name" -> NAME;
            case "name_awake" -> NAME_AWAKE;
            case "shift" -> SHIFT;
            case "ability_name" -> ABILITY_NAME;
            case "ability_name_awake" -> ABILITY_NAME_AWAKE;
            case "ability" -> ABILITY;
            case "ability_awake" -> ABILITY_AWAKE;
            case "charges" -> CHARGES;
            case "special" -> SPECIAL;
            case "error" -> ERROR;
            case "message" -> MESSAGE;
            default -> COMMON;
        };
    }

    public static Text getNameHelper(ItemStack item, boolean awake) {
        if (item.getItem() instanceof OLGenericPage) {
            return Text.translatable(item.getTranslationKey()).setStyle(UNCOMMON);
        }
        if (item.getItem() instanceof OLGenericRune) {
            return Text.translatable(item.getTranslationKey()).setStyle(RARE);
        }
        if (item.getItem() instanceof OLGenericItem) {
            return Text.translatable(item.getTranslationKey()).setStyle(SPECIAL);
        }
        if (awake) {
            return Text.translatable(item.getTranslationKey()).setStyle(NAME_AWAKE);
        }
        return Text.translatable(item.getTranslationKey()).setStyle(NAME);
    }

    public static void appendTooltipHelper(ItemStack itemStack, List<Text> tooltip) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable(BookOfTheLegends.USAGE).setStyle(SHIFT));
        } else {
            tooltip.add(Text.literal(""));
            tooltip.add(Text.translatable(SHIFT_TOOLTIP).setStyle(SHIFT));
        }
    }

    public static void appendTooltipHelper(ItemStack itemStack, List<Text> tooltip, boolean awake, int amountPassives, String id, boolean useCharges, int maxCharges) {

        if (Screen.hasShiftDown()) {
            tooltip.add(Text.literal(""));
            if (awake) {
                for (int i = 1; i <= amountPassives; i++) {
                    String j = String.valueOf(i);
                    tooltip.add(Text.literal("✦ ")
                            .append(Text.translatable(id + "_name_" + j))
                            .setStyle(ABILITY_NAME));
                    tooltip.add(Text.literal(" ")
                            .append(Text.translatable(id + "_awake_" + j))
                            .setStyle(ABILITY));
                }
                tooltip.add(Text.literal("✦ ")
                        .append(Text.translatable(id + "_name_active"))
                        .setStyle(ABILITY_NAME_AWAKE));
                tooltip.add(Text.literal(" ")
                        .append(Text.translatable(id + "_awake_active"))
                        .setStyle(ABILITY_AWAKE));
            } else {
                for (int i = 1; i <= amountPassives; i++) {
                    String j = String.valueOf(i);
                    tooltip.add(Text.literal("✦ ")
                            .append(Text.translatable(id + "_name_" + j))
                            .setStyle(ABILITY_NAME));
                    tooltip.add(Text.literal(" ")
                            .append(Text.translatable(id + "_" + j))
                            .setStyle(ABILITY));
                }
            }

        } else {
            tooltip.add(Text.literal(""));
            tooltip.add(Text.translatable(SHIFT_TOOLTIP).setStyle(SHIFT));
        }
        if (useCharges) {
            tooltip.add(Text.literal(""));

            if (itemStack.contains(OLDataComponent.CHARGES)) {
                int charges = itemStack.get(OLDataComponent.CHARGES);

                Text textCharges = Text.translatable(SwallowsStorm.CHARGES)
                        .append(": " + charges + "/" + maxCharges)
                        .setStyle(CHARGES);
                tooltip.add(textCharges);
            }
        }
    }

    public static void appendTooltipBlockHelper(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options, String id) {
        tooltip.add(Text.translatable(id).setStyle(SHIFT));
    }

    public static double getRandomNumber(int min, int max) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    }

    public static double getRandomDoubleNumber(double min, double max) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    }

    public static ItemStack getAbilityItemStack(PlayerEntity player, ItemStack item) {
        ItemStack mainHandStack = player.getMainHandStack();
        ItemStack offHandStack = player.getOffHandStack();
        return mainHandStack.getItem().equals(item.getItem()) ? mainHandStack : offHandStack.getItem().equals(item.getItem()) ? offHandStack : ItemStack.EMPTY;
    }

    //If you have the item
    public static boolean hasItemInHand(PlayerEntity player, Item item) {
        ItemStack mainHandStack = player.getMainHandStack();
        ItemStack offHandStack = player.getOffHandStack();
        return mainHandStack.getItem() == item || offHandStack.getItem() == item;
    }

    //What hand you have the item
    public static Hand getHandWithItem(PlayerEntity player, Item item) {
        if (player.getMainHandStack().getItem() == item) {
            return Hand.MAIN_HAND;
        } else if (player.getOffHandStack().getItem() == item) {
            return Hand.OFF_HAND;
        } else {
            return null;
        }
    }

    //Get the item in hand
    public static ItemStack getItemInHand(PlayerEntity player, Item item) {
        Hand handWithItem = getHandWithItem(player, item);
        if (handWithItem != null) {
            return handWithItem == Hand.MAIN_HAND ? player.getMainHandStack() : player.getOffHandStack();
        } else {
            return ItemStack.EMPTY;
        }
    }

    public static EquipmentSlot getItemSlot(PlayerEntity player, Item item) {
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.MAINHAND.getType()) {
                ItemStack mainHandStack = player.getMainHandStack();
                if (mainHandStack.getItem() == item) {
                    return slot;
                }
            } else if (slot.getType() == EquipmentSlot.OFFHAND.getType()) {
                ItemStack offHandStack = player.getOffHandStack();
                if (offHandStack.getItem() == item) {
                    return slot;
                }
            } else {
                ItemStack stackInSlot = player.getEquippedStack(slot);
                if (stackInSlot.getItem() == item) {
                    return slot;
                }
            }
        }
        return null;
    }

    public static int amountOfWise(World world, Box box) {
        List<VillagerEntity> wise = world.getEntitiesByType(EntityType.VILLAGER, box, villagerEntity -> {
            VillagerProfession villagerProfession = villagerEntity.getVillagerData().getProfession();
            return villagerProfession.equals(OLVillager.SAGE);
        });
        return wise.size();
    }
}
