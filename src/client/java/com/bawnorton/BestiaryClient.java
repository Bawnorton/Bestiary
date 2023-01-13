package com.bawnorton;

import com.bawnorton.keybind.Keybinds;
import com.bawnorton.screen.BestiaryScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BestiaryClient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("bestiary");

	@Override
	public void onInitializeClient() {
		Keybinds.init();
		LOGGER.info("Client initialized");
		ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
			EntityDirectory.init();
			LOGGER.info("Entity directory initialized");
		});
	}

	public static void openBestiary() {
		MinecraftClient.getInstance().setScreen(new BestiaryScreen());
	}
}