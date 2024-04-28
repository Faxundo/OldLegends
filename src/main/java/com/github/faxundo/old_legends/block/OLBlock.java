package com.github.faxundo.old_legends.block;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.block.custom.EchoBlock;
import com.github.faxundo.old_legends.block.custom.EchoOreBlock;
import com.github.faxundo.old_legends.block.custom.ReliquaryBlock;
import com.github.faxundo.old_legends.block.custom.RuneTableBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static com.github.faxundo.old_legends.OldLegends.LOGGER;
import static com.github.faxundo.old_legends.OldLegends.MOD_NAME;

public class OLBlock {

    public static final Block RELIQUARY_BLOCK = registerBlock("reliquary",
            new ReliquaryBlock(FabricBlockSettings.copyOf(Blocks.GOLD_BLOCK)));
    public static final Block ECHO_ORE = registerBlock("echo_ore",
            new EchoOreBlock(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE).luminance(5)));
    public static final Block ECHO_BLOCK = registerBlock("echo_block",
            new EchoBlock(FabricBlockSettings.copyOf(Blocks.STONE).luminance(3)));
    public static final Block RUNE_TABLE = registerBlock("rune_table",
            new RuneTableBlock(FabricBlockSettings.copyOf(Blocks.CRAFTING_TABLE)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, OldLegends.identifier(name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, OldLegends.identifier(name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerOLBlocks() {
        LOGGER.info(">>> Registering " + MOD_NAME + " blocks.");
    }

}