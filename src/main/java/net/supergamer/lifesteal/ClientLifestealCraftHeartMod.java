package net.supergamer.lifesteal;

import net.fabricmc.api.ClientModInitializer;
import net.supergamer.lifesteal.networking.ModMessages;

public class ClientLifestealCraftHeartMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();
    }
}
