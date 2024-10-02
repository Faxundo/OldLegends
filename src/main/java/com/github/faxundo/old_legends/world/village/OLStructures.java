package com.github.faxundo.old_legends.world.village;

import com.github.faxundo.old_legends.OldLegends;
import fzzyhmstrs.structurized_reborn.impl.FabricStructurePoolRegistry;
import net.minecraft.util.Identifier;

public class OLStructures {

    public static void registerVillageStructure() {
        FabricStructurePoolRegistry.registerSimple(
                Identifier.ofVanilla("village/plains/houses"),
                OldLegends.identifier("village/plains/longhouse"),
                3);
        FabricStructurePoolRegistry.registerSimple(
                Identifier.ofVanilla("village/plains/streets"),
                OldLegends.identifier("village/plains/longhouse_street"),
                3);
    }
}
