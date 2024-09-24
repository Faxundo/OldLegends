package com.github.faxundo.old_legends.util.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record SwordDataComponent(boolean emeraldMourning) {

    public static final Codec<SwordDataComponent> CODEC = RecordCodecBuilder.create(builder -> {
        return builder.group(
                Codec.BOOL.optionalFieldOf("emeraldMourning", false).forGetter(SwordDataComponent::emeraldMourning)
        ).apply(builder,SwordDataComponent::new);
    });


}
