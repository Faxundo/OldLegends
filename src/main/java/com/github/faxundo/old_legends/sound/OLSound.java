package com.github.faxundo.old_legends.sound;

import com.github.faxundo.old_legends.OldLegends;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class OLSound {

    public static final SoundEvent MOURNING_MOB_DEATH = registerSoundEvent("mourning_mob_death");

    private static SoundEvent registerSoundEvent (String name) {
        Identifier id = new Identifier(OldLegends.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        OldLegends.LOGGER.info("Registering Sounds for " + OldLegends.MOD_NAME);
    }
}
