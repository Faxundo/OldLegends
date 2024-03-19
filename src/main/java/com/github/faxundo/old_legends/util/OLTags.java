package com.github.faxundo.old_legends.util;

import com.github.faxundo.old_legends.OldLegends;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class OLTags {
    public static class Items {

        public static final TagKey<Item> OLD_LEGENDS_ITEMS =
                createTag("old_legends_items");
        public static final TagKey<Item> OLD_LEGENDS_SWORDS =
                createTag("old_legends_swords");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(OldLegends.MOD_ID, name));
        }
    }
}
