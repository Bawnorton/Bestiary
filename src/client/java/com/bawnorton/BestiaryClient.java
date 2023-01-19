package com.bawnorton;

import com.bawnorton.bestiary.EntityDirectory;
import com.bawnorton.keybind.Keybinds;
import com.bawnorton.screen.BestiaryScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BestiaryClient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("bestiary");

	@Override
	public void onInitializeClient() {
		Keybinds.init();
		LOGGER.info("Client initialized");
		ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
			try {
				EntityDirectory.init();
				LOGGER.info("Entity directory initialized");
			} catch (RuntimeException e) {
				MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.of("§c[Bestiary]: Bestiary failed to load. Please check the log for more information."));
				LOGGER.error("Failed to initialize entity directory", e);
			}
		});
	}

	public static void openBestiary() {
		MinecraftClient.getInstance().setScreen(new BestiaryScreen());
	}
}