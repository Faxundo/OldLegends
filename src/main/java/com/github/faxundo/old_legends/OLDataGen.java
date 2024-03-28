package com.github.faxundo.old_legends;

import com.github.faxundo.old_legends.datagen.OLItemTagProvider;
import com.github.faxundo.old_legends.datagen.OLModelProvider;
import com.github.faxundo.old_legends.datagen.OLRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;

import static com.github.faxundo.old_legends.OldLegends.LOGGER;
import static com.github.faxundo.old_legends.OldLegends.MOD_NAME;

public class OLDataGen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator (FabricDataGenerator fabricDataGenerator){

        LOGGER.info(">>> Generating data for " + MOD_NAME);

        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(OLItemTagProvider::new);
        pack.addProvider(OLRecipeProvider::new);
        pack.addProvider(OLModelProvider::new);

    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        LOGGER.info(">>> Generating data features for " + MOD_NAME);
    }
}
