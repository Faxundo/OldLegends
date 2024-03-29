package com.github.faxundo.old_legends.item;

import com.github.faxundo.old_legends.OldLegends;
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
            new Identifier(OldLegends.MOD_ID, "old_legends"));

    public static void registerItemGroup () {
        LOGGER.info(">>> Registering " + MOD_NAME + " item groups.");
        Registry.register(Registries.ITEM_GROUP, OLD_LEGENDS, FabricItemGroup.builder()
                .icon(() -> new ItemStack(OLItem.ICON))
                .displayName(Text.translatable("item.old_legends.icon"))
                .entries((context, entries) -> {
                    entries.add(OLItem.PALE_GEM);
                    entries.add(OLItem.END_EXTRACT);
                    entries.add(OLItem.AWAKENING_UPGRADE);
                    entries.add(OLItem.SKY_RUNE);
                    entries.add(OLItem.EMERALD_MOURNING);
                    entries.add(OLItem.EMERALD_MOURNING_AWAKE);
                    entries.add(OLItem.SWALLOWS_STORM);
//                    entries.add(OLItem.SWALLOWS_STORM_AWAKE);
                })
                .build()
        );
    }
}
