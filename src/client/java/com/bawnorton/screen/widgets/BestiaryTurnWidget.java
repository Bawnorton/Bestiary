package com.bawnorton.screen.widgets;

import com.bawnorton.screen.BestiaryScreen;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.sound.SoundEvents;

public class BestiaryTurnWidget extends ButtonWidget {
    private final boolean isNextPage;
    private final boolean playSound;

    public BestiaryTurnWidget(int x, int y, boolean isNextPage, ButtonWidget.PressAction onPress, boolean playSound) {
        super(x, y, 18, 17, ScreenTexts.EMPTY, onPress, DEFAULT_NARRATION_SUPPLIER);
        this.isNextPage = isNextPage;
        this.playSound = playSound;
    }

    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BestiaryScreen.TEXTURE);
        int i = 100;
        int j = 221;
        if (this.isHovered()) {
            i -= 100;
        }
        if (!this.isNextPage) {
            j += 17;
        }

        this.drawTexture(matrices, this.getX(), this.getY(), i, j, 18, 17);
    }

    public void playDownSound(SoundManager soundManager) {
        if (this.playSound) {
            soundManager.play(PositionedSoundInstance.master(SoundEvents.ITEM_BOOK_PAGE_TURN, 1.0F));
        }
    }
}
