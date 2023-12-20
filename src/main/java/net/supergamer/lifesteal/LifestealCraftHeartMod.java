package net.supergamer.lifesteal;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.supergamer.lifesteal.Items.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
// word()
import static com.mojang.brigadier.arguments.StringArgumentType.word;
// literal("foo")
import static net.minecraft.server.command.CommandManager.literal;
// argument("bar", word())
import static net.minecraft.server.command.CommandManager.argument;
// Import everything in the CommandManager
import static net.minecraft.server.command.CommandManager.*;

public class LifestealCraftHeartMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("lifesteal");
	public static final String MOD_ID = "lifesteal";

	@Override
	public void onInitialize() {
		ModItems.registerModItems();


		ServerPlayerEvents.COPY_FROM.register((original, cloned, alive) -> {
				EntityAttributeInstance oldhealthAttribute = original.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
				EntityAttributeInstance healthAttribute = cloned.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
				if (healthAttribute != null) {
					// Create a unique identifier for your modifier (adjust this as needed)
					Identifier modifierId = new Identifier(LifestealCraftHeartMod.MOD_ID, "health_boost");

					assert oldhealthAttribute != null;
					healthAttribute.setBaseValue(oldhealthAttribute.getValue());
			}
		});

			CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
					dispatcher.register(literal("withdraw")
									.requires(source -> source.hasPermissionLevel(1))
							.then(argument("amount", IntegerArgumentType.integer())
									.executes(context -> {


										final int value = IntegerArgumentType.getInteger(context, "amount");
										EntityAttributeInstance healthAttribute = context.getSource().getPlayer().getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
										if (healthAttribute != null) {
											// Create a unique identifier for your modifier (adjust this as needed)
											Identifier modifierId = new Identifier(LifestealCraftHeartMod.MOD_ID, "health_boost");



											if (value > healthAttribute.getValue() / 2) {
												context.getSource().sendFeedback(() -> Text.literal("you do not have enough hearts"), false);
											} else {
												context.getSource().getPlayer().giveItemStack(ModItems.HEART.getDefaultStack().copyWithCount(value));
												healthAttribute.setBaseValue(healthAttribute.getValue() - value * 2);
												context.getSource().sendFeedback(() -> Text.literal("withdrawn " + value), false);
											}

										}
										return 0;
									})));
			});
	}
}