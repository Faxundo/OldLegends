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

public class OLItemGroup {

    private static final RegistryKey<net.minecraft.item.ItemGroup> OLD_LEGENDS = RegistryKey.of(RegistryKeys.ITEM_GROUP,
            new Identifier(OldLegends.MOD_ID, "old_legends"));

    public static void registerItemGroup () {
        Registry.register(Registries.ITEM_GROUP, OLD_LEGENDS, FabricItemGroup.builder()
                .icon(() -> new ItemStack(OLItem.ICON))
                .displayName(Text.translatable("item.oldlegends.icon"))
                .entries((context, entries) -> {
                    entries.add(OLItem.PALE_GEM);
                    entries.add(OLItem.END_EXTRACT);
                    entries.add(OLItem.AWAKENING_UPGRADE);
                    entries.add(OLItem.EMERALD_MOURNING);
                    entries.add(OLItem.EMERALD_MOURNING_AWAKE);
                })
                .build()
        );
    }
}
