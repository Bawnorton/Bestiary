package com.bawnorton;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Keybinds {
    private static KeyBinding openBestiary;

    public static void init() {
        openBestiary = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.bawnorton.openbestiary",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_B,
                        "category.bawnorton.bestiary"
                )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openBestiary.wasPressed()) {
                BestiaryClient.openBestiary();
            }
        });
    }
}
