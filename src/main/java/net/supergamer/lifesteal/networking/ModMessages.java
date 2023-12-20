package net.supergamer.lifesteal.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import net.supergamer.lifesteal.LifestealCraftHeartMod;
import net.supergamer.lifesteal.networking.packets.AddC2SPacket;

public class ModMessages {
    public static final Identifier HEART_ID = new Identifier(LifestealCraftHeartMod.MOD_ID, "addheart");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(HEART_ID, AddC2SPacket::receive);
    }

    public static void registerS2CPackets() {

    }
}
