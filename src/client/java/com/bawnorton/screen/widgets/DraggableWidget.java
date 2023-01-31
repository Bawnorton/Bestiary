package com.bawnorton.screen.widgets;

import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;

public class DraggableWidget extends ClickableWidget {
    final DraggablePoint point;
    final ClickableWidget interactable;

    public DraggableWidget(ClickableWidget interactable) {
        super(interactable.getX(), interactable.getY(), interactable.getWidth(), interactable.getHeight(), ScreenTexts.EMPTY);
        this.point = new DraggablePoint();
        this.interactable = interactable;
    }

    @Override
    protected boolean clicked(double mouseX, double mouseY) {
        return super.clicked(mouseX, mouseY) || this.point.isMouseOver(mouseX, mouseY);
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        if(!this.point.isMouseOver(mouseX, mouseY)) this.interactable.onClick(mouseX, mouseY);
        else this.point.onClick(mouseX, mouseY);
    }

    @Override
    public void onRelease(double mouseX, double mouseY) {
        if(!this.point.isMouseOver(mouseX, mouseY)) this.interactable.onRelease(mouseX, mouseY);
        else this.point.onRelease(mouseX, mouseY);
    }

    private void move(double mouseX, double mouseY) {
        if(this.point.isDragging()) {
            this.point.setX((int) (mouseX - this.point.getWidth() / 2));
            this.point.setY((int) (mouseY - this.point.getHeight() / 2));
            this.interactable.setX(this.point.getX() - this.getWidth() / 2 + this.point.getWidth() / 2);
            this.interactable.setY(this.point.getY() + this.point.getHeight() / 2);
            this.setX(this.interactable.getX());
            this.setY(this.interactable.getY());
        }
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        move(mouseX, mouseY);
        this.interactable.render(matrices, mouseX, mouseY, delta);
        this.point.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
        this.appendDefaultNarrations(builder);
    }

    private class DraggablePoint extends ClickableWidget {
        private boolean dragging = false;

        protected DraggablePoint() {
            super(DraggableWidget.this.getX() + DraggableWidget.this.getWidth() / 2 - 3, DraggableWidget.this.getY() - 3, 6, 6, ScreenTexts.EMPTY);
        }

        @Override
        public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            if (this.isHovered()) {
                fill(matrices, this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight(), 0xFFFFFFFF);
                fill(matrices, this.getX() + 1, this.getY() + 1, this.getX() + this.getWidth() - 1, this.getY() + this.getHeight() - 1, 0xFF000000);
            } else {
                fill(matrices, this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight(), 0xFF000000);
                fill(matrices, this.getX() + 1, this.getY() + 1, this.getX() + this.getWidth() - 1, this.getY() + this.getHeight() - 1, 0xFFFFFFFF);
            }
        }

        @Override
        protected void appendClickableNarrations(NarrationMessageBuilder builder) {
            this.appendDefaultNarrations(builder);
        }

        @Override
        public void onClick(double mouseX, double mouseY) {
            this.dragging = true;
        }

        @Override
        public void onRelease(double mouseX, double mouseY) {
            this.dragging = false;
        }

        public boolean isDragging() {
            return this.dragging;
        }
    }
}
