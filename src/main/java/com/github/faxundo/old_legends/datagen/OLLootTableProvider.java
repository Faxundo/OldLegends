package com.github.faxundo.old_legends.datagen;

import com.github.faxundo.old_legends.block.OLBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class OLLootTableProvider extends FabricBlockLootTableProvider {

    public OLLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(OLBlock.RELIQUARY_BLOCK);
    }
}
