package com.github.faxundo.old_legends.item;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.block.OLBlock;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.github.faxundo.old_legends.OldLegends.LOGGER;
import static com.github.faxundo.old_legends.OldLegends.MOD_NAME;

public class OLItemGroup {

    private static final RegistryKey<net.minecraft.item.ItemGroup> OLD_LEGENDS = RegistryKey.of(RegistryKeys.ITEM_GROUP,
            Identifier.of(OldLegends.MOD_ID, "old_legends"));

    public static void registerItemGroup () {
        LOGGER.info(">>> Registering " + MOD_NAME + " item groups.");
        Registry.register(Registries.ITEM_GROUP, OLD_LEGENDS, FabricItemGroup.builder()
                .icon(() -> new ItemStack(OLItem.ICON))
                .displayName(Text.translatable("item.old_legends.icon"))
                .entries((context, entries) -> {
                    entries.add(OLItem.BOOK_OF_THE_LEGENDS);
                    entries.add(OLItem.PALE_GEM);
                    entries.add(OLItem.END_EXTRACT);
                    entries.add(OLItem.AWAKENING_UPGRADE);
                    entries.add(OLItem.BLANK_RUNE);
                    entries.add(OLItem.SKY_RUNE);
                    entries.add(OLItem.SKY_RUNE_PAGE);
                    entries.add(OLItem.DEATH_RUNE);
                    entries.add(OLItem.DEATH_RUNE_PAGE);
                    entries.add(OLItem.TIME_RUNE);
                    entries.add(OLItem.TIME_RUNE_PAGE);
                    entries.add(OLItem.BLANK_BLUEPRINT);
                    entries.add(OLItem.RELIQUARY_BLUEPRINT);
                    entries.add(OLBlock.RELIQUARY_BLOCK);
                    entries.add(OLItem.RELIQUARY_PAGE);
                    entries.add(OLBlock.RUNE_TABLE);
                    entries.add(OLItem.EMERALD_MOURNING);
                    entries.add(OLItem.EMERALD_MOURNING_AWAKE);
                    entries.add(OLItem.EMERALD_MOURNING_PAGE);
                    entries.add(OLItem.SWALLOWS_STORM);
                    entries.add(OLItem.SWALLOWS_STORM_AWAKE);
                    entries.add(OLItem.SWALLOWS_STORM_PAGE);
                    entries.add(OLItem.FLUTTER_ECHO);
                    entries.add(OLItem.FLUTTER_ECHO_AWAKE);
                    entries.add(OLItem.FLUTTER_ECHO_PAGE);
                    entries.add(OLBlock.ECHO_ORE);
                    entries.add(OLBlock.ECHO_BLOCK);
                })
                .build()
        );
    }

}
