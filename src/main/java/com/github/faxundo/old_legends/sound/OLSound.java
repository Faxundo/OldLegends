package com.github.faxundo.old_legends.sound;

import com.github.faxundo.old_legends.OldLegends;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static com.github.faxundo.old_legends.OldLegends.LOGGER;
import static com.github.faxundo.old_legends.OldLegends.MOD_NAME;

public class OLSound {

    public static final SoundEvent MOURNING_MOB_DEATH = registerSoundEvent("mourning_mob_death");
    public static final SoundEvent SWALLOWS_STORM_HEAL = registerSoundEvent("swallows_storm_heal");
    public static final SoundEvent SWALLOWS_STORM_EXPLOSION = registerSoundEvent("swallows_storm_explosion");
    public static final SoundEvent BOOK_OF_THE_LEGENDS_OPEN = registerSoundEvent("book_of_the_legends_open");
    public static final SoundEvent GRINNING_HOARDER_LAUGH = registerSoundEvent("grinning_hoarder_laugh");

    private static SoundEvent registerSoundEvent (String name) {
        Identifier id = Identifier.of(OldLegends.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        LOGGER.info(">>> Registering " + MOD_NAME + " sounds.");
    }
}
