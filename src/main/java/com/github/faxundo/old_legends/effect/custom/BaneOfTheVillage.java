package com.github.faxundo.old_legends.effect.custom;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class BaneOfTheVillage extends StatusEffect {

    public BaneOfTheVillage() {
        super(StatusEffectCategory.HARMFUL, 0xff2929);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
