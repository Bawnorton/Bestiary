package com.bawnorton;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BestiaryClient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("bestiary");

	@Override
	public void onInitializeClient() {
		LOGGER.info("Client initialized");
		Keybinds.init();
	}

	public static void openBestiary() {
		LOGGER.info("Opening bestiary");
		MinecraftClient.getInstance().setScreen(new BestiaryScreen());
	}
}