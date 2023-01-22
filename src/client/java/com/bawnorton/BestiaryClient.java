package com.bawnorton;

import com.bawnorton.bestiary.BestiaryContent;
import com.bawnorton.config.ConfigManager;
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

    public static void openBestiary() {
        MinecraftClient.getInstance().setScreen(new BestiaryScreen());
    }

    @Override
    public void onInitializeClient() {
        Keybinds.init();
        ConfigManager.loadConfig();

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            try {
                BestiaryContent.init();
                LOGGER.info("Bestiary content initialized");
            } catch (RuntimeException e) {
                MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.of("§c[Bestiary]: Bestiary failed to load. Please check the log for more information."));
                LOGGER.error("Failed to initialize bestiary content", e);
            }
        });
        LOGGER.info("Client initialized");
    }
}