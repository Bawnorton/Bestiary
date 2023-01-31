package com.bawnorton.screen.widgets;

import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.screen.ScreenTexts;

public class ResizeableWidget extends ClickableWidget {


    public ResizeableWidget(ClickableWidget interactable) {
        super(interactable.getX(), interactable.getY(), interactable.getWidth(), interactable.getHeight(), ScreenTexts.EMPTY);
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
        this.appendDefaultNarrations(builder);
    }

    private enum ResizePoint {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }

    private class ResizeAnchor extends ClickableWidget {
        private ResizePoint resizePoint;

        public ResizeAnchor(ResizePoint resizePoint) {
            super(switch (resizePoint) {
                    case TOP_LEFT, BOTTOM_LEFT -> ResizeableWidget.this.getX() - 3;
                    case TOP_RIGHT, BOTTOM_RIGHT -> ResizeableWidget.this.getX() + ResizeableWidget.this.getWidth() + 3;
                }, switch (resizePoint) {
                    case TOP_LEFT, TOP_RIGHT -> ResizeableWidget.this.getY() - 3;
                    case BOTTOM_LEFT, BOTTOM_RIGHT -> ResizeableWidget.this.getY() + ResizeableWidget.this.getHeight() + 3;
                }, 6, 6, ScreenTexts.EMPTY);
            this.resizePoint = resizePoint;
        }

        @Override
        protected void appendClickableNarrations(NarrationMessageBuilder builder) {
            this.appendDefaultNarrations(builder);
        }
    }
}
