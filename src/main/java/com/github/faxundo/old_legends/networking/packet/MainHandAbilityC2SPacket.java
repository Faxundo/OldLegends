package com.github.faxundo.old_legends.networking.packet;

import com.github.faxundo.old_legends.OldLegends;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record MainHandAbilityC2SPacket(boolean pressKey) implements CustomPayload {

    public static final CustomPayload.Id<MainHandAbilityC2SPacket> PACKET_ID = new CustomPayload.Id<>(
            OldLegends.identifier("main_hand_ability"));

    public static final PacketCodec<RegistryByteBuf, MainHandAbilityC2SPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL, MainHandAbilityC2SPacket::pressKey,MainHandAbilityC2SPacket::new);

    public static boolean canSend() {
        return ClientPlayNetworking.canSend(PACKET_ID);
    }

    public void send() {
        ClientPlayNetworking.send(this);
    }

//    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
//                               PacketByteBuf buf, PacketSender responseSender) {
//        ItemStack stack = player.getMainHandStack();
//        Item item = stack.getItem();
//        if (item instanceof Ability abilityUser) {
//            abilityUser.useAbility(player);
//            return;
//        }
////        Map<Enchantment, Integer> listEnchantments = EnchantmentHelper.get(stack);
////        for (Enchantment enchantment : listEnchantments.keySet()) {
////            if (enchantment instanceof OLGenericEnchantment olGenericEnchantment) {
////                olGenericEnchantment.useAbility(player, stack);
////                break;
////            }
////        }
//    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}
