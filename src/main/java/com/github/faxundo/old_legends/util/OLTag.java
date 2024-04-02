package com.github.faxundo.old_legends.util;

import com.github.faxundo.old_legends.OldLegends;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class OLTag {

    public static class Items {

        public static final TagKey<Item> CAN_AWAKE =
                createTag("can_awake");
        public static final TagKey<Item> RUNE =
                createTag("rune");
        public static final TagKey<Item> SKY_REPAIR =
                createTag("sky_repair");
        public static final TagKey<Item> SHIELD =
                createTag("shield");


        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(OldLegends.MOD_ID, name));
        }
    }
}
