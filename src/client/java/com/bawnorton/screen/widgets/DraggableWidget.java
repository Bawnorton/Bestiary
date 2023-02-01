package com.bawnorton.screen.widgets;

import com.bawnorton.mixin.ClickableWidgetAccessor;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;

public class DraggableWidget extends ClickableWidget {
    private final DraggableAnchor anchor;
    protected final ClickableWidget interactable;

    public DraggableWidget(ClickableWidget interactable) {
        super(interactable.getX(), interactable.getY(), interactable.getWidth(), interactable.getHeight(), ScreenTexts.EMPTY);
        this.interactable = interactable;

        this.anchor = new DraggableAnchor();
    }

    @Override
    protected boolean clicked(double mouseX, double mouseY) {
        return super.clicked(mouseX, mouseY) || this.anchor.isMouseOver(mouseX, mouseY);
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        if(!this.anchor.isMouseOver(mouseX, mouseY)) this.interactable.onClick(mouseX, mouseY);
        else this.anchor.onClick(mouseX, mouseY);
    }

    @Override
    public void onRelease(double mouseX, double mouseY) {
        if(!this.anchor.isDragging()) this.interactable.onRelease(mouseX, mouseY);
        else this.anchor.onRelease(mouseX, mouseY);
    }

    protected void move(double mouseX, double mouseY) {
        if(this.anchor.isDragging()) {
            this.anchor.setX((int) (mouseX - this.anchor.getWidth() / 2));
            this.anchor.setY((int) (mouseY - this.anchor.getHeight() / 2));
            this.interactable.setX(this.anchor.getX() - this.getWidth() / 2 + this.anchor.getWidth() / 2);
            this.interactable.setY(this.anchor.getY() + this.anchor.getHeight() / 2);
            this.setX(this.interactable.getX());
            this.setY(this.interactable.getY());
        }
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.move(mouseX, mouseY);
        this.interactable.render(matrices, mouseX, mouseY, delta);
        this.anchor.render(matrices, mouseX, mouseY, delta);
    }

    protected void adjustPosition() {
        this.anchor.setX(interactable.getX() + interactable.getWidth() / 2 - 3);
        this.anchor.setY(interactable.getY() - 3);
        this.setX(interactable.getX());
        this.setY(interactable.getY());
        this.setWidth(interactable.getWidth());
        ((ClickableWidgetAccessor) this).setHeight(interactable.getHeight());
    }

    public DraggableAnchor getDragAnchor() {
        return anchor;
    }

    public ClickableWidget getInteractable() {
        return interactable;
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
        this.appendDefaultNarrations(builder);
    }

    public class DraggableAnchor extends ClickableWidget {
        private boolean dragging = false;

        protected DraggableAnchor() {
            super(interactable.getX() + interactable.getWidth() / 2 - 3, interactable.getY() - 3, 6, 6, ScreenTexts.EMPTY);
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
