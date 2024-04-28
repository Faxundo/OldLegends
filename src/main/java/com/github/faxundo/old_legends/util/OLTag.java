package com.github.faxundo.old_legends.util;

import com.github.faxundo.old_legends.OldLegends;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class OLTag {

    public static class Blocks {
        public static final TagKey<Block> ORES =
                createTag("ores");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, OldLegends.identifier(name));
        }
    }

    public static class Items {

        public static final TagKey<Item> CAN_AWAKE =
                createTag("can_awake");
        public static final TagKey<Item> RUNE =
                createTag("rune");
        public static final TagKey<Item> PAGE =
                createTag("page");
        public static final TagKey<Item> BLUEPRINT =
                createTag("blueprint");
        public static final TagKey<Item> SKY_REPAIR =
                createTag("sky_repair");
        public static final TagKey<Item> SHIELD =
                createTag("shield");


        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, OldLegends.identifier(name));
        }
    }
}
