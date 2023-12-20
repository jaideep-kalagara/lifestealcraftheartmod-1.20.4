package net.supergamer.lifesteal.networking.packets;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.supergamer.lifesteal.LifestealCraftHeartMod;

public class AddC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {

            EntityAttributeInstance healthAttribute = player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);

            if (healthAttribute != null) {
                // Create a unique identifier for your modifier (adjust this as needed)
                Identifier modifierId = new Identifier(LifestealCraftHeartMod.MOD_ID, "health_boost");

                    healthAttribute.setBaseValue(healthAttribute.getValue() + 2.0);
            }
        }
    }
