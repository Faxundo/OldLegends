package com.github.faxundo.old_legends.util;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.OLGenericItem;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Hand;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class OLHelper {

    private static final Style NAME = getStyle("name");
    private static final Style NAME_AWAKE = getStyle("name_awake");
    private static final Style ABILITY_NAME = getStyle("ability_name");
    private static final Style ABILITY_NAME_AWAKE = getStyle("ability_name_awake");
    private static final Style ABILITY = getStyle("ability");
    private static final Style ABILITY_AWAKE = getStyle("ability_awake");
    private static final Style SHIFT = getStyle("shift");
    private static final Style CHARGES = getStyle("charges");
    private static final Style SPECIAL = getStyle("special");

    public static Style getStyle(String styleType) {
        int common = 0xFFFFFF;
        int name = 0xE9581D;
        int name_awake = 0x911AE7;
        int shift = 0xEDE65A;
        int ability_name = 0x2DFEF8;
        int ability_name_awake = 0xBA5BFF;
        int ability = 0xFECB2D;
        int ability_awake = 0xF2A125;
        int charges = 0xCDFF00;
        int special = 0xE71AD7;

        Style COMMON = Style.EMPTY.withColor(TextColor.fromRgb(common));
        Style NAME = Style.EMPTY.withColor(TextColor.fromRgb(name));
        Style NAME_AWAKE = Style.EMPTY.withColor(TextColor.fromRgb(name_awake));
        Style SHIFT = Style.EMPTY.withColor(TextColor.fromRgb(shift)).withBold(true);
        Style ABILITY_NAME = Style.EMPTY.withColor(TextColor.fromRgb(ability_name)).withItalic(true);
        Style ABILITY_NAME_AWAKE = Style.EMPTY.withColor(TextColor.fromRgb(ability_name_awake)).withItalic(true);
        Style ABILITY = Style.EMPTY.withColor(TextColor.fromRgb(ability));
        Style ABILITY_AWAKE = Style.EMPTY.withColor(TextColor.fromRgb(ability_awake));
        Style CHARGES = Style.EMPTY.withColor(TextColor.fromRgb(charges));
        Style SPECIAL = Style.EMPTY.withColor(TextColor.fromRgb(special));

        return switch (styleType) {
            case "name" -> NAME;
            case "name_awake" -> NAME_AWAKE;
            case "shift" -> SHIFT;
            case "ability_name" -> ABILITY_NAME;
            case "ability_name_awake" -> ABILITY_NAME_AWAKE;
            case "ability" -> ABILITY;
            case "ability_awake" -> ABILITY_AWAKE;
            case "charges" -> CHARGES;
            case "special" -> SPECIAL;
            default -> COMMON;
        };
    }

    public static Text getNameHelper(ItemStack item, boolean awake) {
        if (item.getItem() instanceof OLGenericItem) {
            return Text.translatable(item.getTranslationKey()).setStyle(SPECIAL);
        }
        if (awake) {
            return Text.translatable(item.getTranslationKey()).setStyle(NAME_AWAKE);
        }
        return Text.translatable(item.getTranslationKey()).setStyle(NAME);
    }

    public static void appendTooltipHelper(ItemStack item, List<Text> tooltip, boolean awake, int amountPassives, String id, boolean useCharges, int maxCharges) {

        if (Screen.hasShiftDown()) {
            tooltip.add(Text.literal(""));
            if (awake) {
                for (int i = 1; i <= amountPassives; i++) {
                    String j = String.valueOf(i);
                    tooltip.add(Text.translatable(id + "_name_" + j).setStyle(ABILITY_NAME));
                    tooltip.add(Text.translatable(id + "_awake_" + j).setStyle(ABILITY));
                }

                tooltip.add(Text.translatable(id + "_name_active").setStyle(ABILITY_NAME_AWAKE));
                tooltip.add(Text.translatable(id + "_awake_active").setStyle(ABILITY_AWAKE));
            } else {
                for (int i = 1; i <= amountPassives; i++) {
                    String j = String.valueOf(i);
                    tooltip.add(Text.translatable(id + "_name_" + j).setStyle(ABILITY_NAME));
                    tooltip.add(Text.translatable(id + "_" + j).setStyle(ABILITY));
                }
            }

        } else {
            tooltip.add(Text.literal(""));
            tooltip.add(Text.translatable("tooltip.old_legends.shift").setStyle(SHIFT));
        }
        if (useCharges) {
            tooltip.add(Text.literal(""));
            NbtCompound nbtData = item.getOrCreateNbt();
            Text textCharges = Text.translatable("tooltip.old_legends.swallows_storm_charges")
                    .append(": " + nbtData.getInt("old_legends") + "/" + maxCharges)
                    .setStyle(CHARGES);
            tooltip.add(textCharges);

        }
    }

    public static void appendTooltipBlockHelper(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options, String id) {
        tooltip.add(Text.translatable(id).setStyle(SHIFT));
    }

    public static double getRandomNumber(int min, int max) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    }

    public static double getRandomDoubleNumber(double min, double max) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    }

    public static void clearCharges(ItemStack abilityStack) {
        NbtCompound nbtData = abilityStack.getOrCreateNbt();
        nbtData.putInt(OldLegends.MOD_ID, 0);
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

    public static int getCharges(ItemStack itemStack) {
        if (itemStack.hasNbt()) {
            if (itemStack.getNbt().contains(OldLegends.MOD_ID)) {
                return itemStack.getNbt().getInt(OldLegends.MOD_ID);
            }
        }
        return 0;
    }


    public static boolean isOreBlock(BlockState state) {
        return state.isIn(OLTag.Blocks.ORES);
    }

    public static List<Item> reliquaryItems () {
        List<Item> itemList = new ArrayList<>();
        itemList.add(Items.EXPERIENCE_BOTTLE);
        itemList.add(Items.EMERALD_BLOCK);
        itemList.add(Items.GLISTERING_MELON_SLICE);
        itemList.add(Items.EMERALD);
        itemList.add(Items.GOLDEN_CARROT);
        itemList.add(Items.DIAMOND);
//        itemList.add(Items.DIAMOND_AXE);
//        itemList.add(Items.DIAMOND_HOE);
//        itemList.add(Items.DIAMOND_PICKAXE);
//        itemList.add(Items.DIAMOND_SHOVEL);
//        itemList.add(Items.DIAMOND_SWORD);
        itemList.add(Items.GOLD_INGOT);
//        itemList.add(Items.GOLDEN_AXE);
//        itemList.add(Items.GOLDEN_HOE);
//        itemList.add(Items.GOLDEN_PICKAXE);
//        itemList.add(Items.GOLDEN_SHOVEL);
//        itemList.add(Items.GOLDEN_SWORD);
        itemList.add(Items.AMETHYST_SHARD);
        itemList.add(Items.DIAMOND_BLOCK);
        itemList.add(Items.GOLDEN_APPLE);
        itemList.add(Items.GOLD_BLOCK);
        itemList.add(Items.TOTEM_OF_UNDYING);
        itemList.add(Items.ENCHANTED_GOLDEN_APPLE);
        return itemList;
    }
}
