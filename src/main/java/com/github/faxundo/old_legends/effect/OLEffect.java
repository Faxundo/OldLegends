package com.github.faxundo.old_legends.effect;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.effect.custom.BaneOfTheVillage;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class OLEffect {

    public static StatusEffect BANE_OF_THE_VILLAGE;

    public static StatusEffect registerBaneOfTheVillage(String name) {
        return Registry.register(Registries.STATUS_EFFECT, OldLegends.identifier(name),
                new BaneOfTheVillage());
    }

    public static void registerEffects(){
        BANE_OF_THE_VILLAGE = registerBaneOfTheVillage("bane_of_the_village");
    }
}
