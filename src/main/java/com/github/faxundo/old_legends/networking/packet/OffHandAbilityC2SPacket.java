package com.github.faxundo.old_legends.networking.packet;

import com.github.faxundo.old_legends.OldLegends;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record OffHandAbilityC2SPacket(boolean pressKey) implements CustomPayload {

    public static final CustomPayload.Id<OffHandAbilityC2SPacket> PACKET_ID = new CustomPayload.Id<>(
            OldLegends.identifier("off_hand_ability"));

    public static final PacketCodec<RegistryByteBuf, OffHandAbilityC2SPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL, OffHandAbilityC2SPacket::pressKey, OffHandAbilityC2SPacket::new);

//    public static void receive (MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
//                               PacketByteBuf buf, PacketSender responseSender) {
//        ItemStack stack = player.getOffHandStack();
//        Item item = stack.getItem();
//        if (item instanceof Ability abilityUser) {
//            abilityUser.useAbility(player);
//        }
//        Map<Enchantment, Integer> listEnchantments = EnchantmentHelper.get(stack);
//        for (Enchantment enchantment : listEnchantments.keySet()) {
//            if (enchantment instanceof OLGenericEnchantment olGenericEnchantment) {
//                olGenericEnchantment.useAbility(player, stack);
//                break;
//            }
//        }
//    }

    public static boolean canSend() {
        return ClientPlayNetworking.canSend(PACKET_ID);
    }

    public void send() {
        ClientPlayNetworking.send(this);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}
