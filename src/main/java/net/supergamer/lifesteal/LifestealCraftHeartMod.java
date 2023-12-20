package net.supergamer.lifesteal;

import net.fabricmc.api.ModInitializer;

import net.supergamer.lifesteal.Items.ModItems;
import net.supergamer.lifesteal.networking.ModMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LifestealCraftHeartMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("lifesteal");
	public static final String MOD_ID = "lifesteal";

	@Override
	public void onInitialize() {
		ModItems.registerModItems();

		ModMessages.registerC2SPackets();
	}
}