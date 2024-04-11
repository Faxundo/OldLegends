package com.github.faxundo.old_legends.datagen;

import com.github.faxundo.old_legends.item.OLItem;
import com.github.faxundo.old_legends.util.OLTag;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class OLItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public OLItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {

        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(OLTag.Items.RUNE)
                .add(OLItem.SKY_RUNE)
                .add(OLItem.DEATH_RUNE)
                .add(OLItem.TIME_RUNE);
        getOrCreateTagBuilder(OLTag.Items.SKY_REPAIR)
                .add(OLItem.SKY_RUNE);
        getOrCreateTagBuilder(ItemTags.TRIM_TEMPLATES)
                .add(OLItem.AWAKENING_UPGRADE);
        getOrCreateTagBuilder(ItemTags.TRIM_MATERIALS)
                .add(OLItem.END_EXTRACT);
        getOrCreateTagBuilder(OLTag.Items.CAN_AWAKE)
                .add(OLItem.EMERALD_MOURNING)
                .add(OLItem.SWALLOWS_STORM)
                .add(OLItem.FLUTTER_ECHO);
        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(OLItem.EMERALD_MOURNING)
                .add(OLItem.EMERALD_MOURNING_AWAKE);
        getOrCreateTagBuilder(OLTag.Items.SHIELD)
                .add(OLItem.SWALLOWS_STORM)
                .add(OLItem.SWALLOWS_STORM_AWAKE);
        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(OLItem.FLUTTER_ECHO)
                .add(OLItem.FLUTTER_ECHO_AWAKE);
    }
}
