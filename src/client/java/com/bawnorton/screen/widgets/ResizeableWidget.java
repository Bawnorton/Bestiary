package com.bawnorton.screen.widgets;

import com.bawnorton.mixin.ClickableWidgetAccessor;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;

import java.util.List;

public class ResizeableWidget extends DraggableWidget {
    List<ResizeAnchor> anchors;
    private final float originalWidth;
    private final float originalHeight;

    public ResizeableWidget(ClickableWidget interactable) {
        super(interactable);

        anchors = List.of(
                new ResizeAnchor(ResizePoint.TOP_LEFT),
                new ResizeAnchor(ResizePoint.TOP_RIGHT),
                new ResizeAnchor(ResizePoint.BOTTOM_LEFT),
                new ResizeAnchor(ResizePoint.BOTTOM_RIGHT)
        );

        this.originalWidth = interactable.getWidth();
        this.originalHeight = interactable.getHeight();
    }

    private ResizeAnchor getAnchor(double mouseX, double mouseY) {
        return anchors.stream().filter(anchor -> anchor.isMouseOver(mouseX, mouseY)).findFirst().orElse(null);
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        ResizeAnchor anchor = this.getAnchor(mouseX, mouseY);
        if(anchor != null) anchor.onClick(mouseX, mouseY);
        else super.onClick(mouseX, mouseY);
    }

    @Override
    public void onRelease(double mouseX, double mouseY) {
        ResizeAnchor anchor = this.getAnchor(mouseX, mouseY);
        if(anchor != null) anchor.onRelease(mouseX, mouseY);
        else super.onRelease(mouseX, mouseY);
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.resize(mouseX, mouseY);
        super.move(mouseX, mouseY);

        matrices.push();
        // TODO: Scale the interactable to the size of the widget
        this.interactable.render(matrices, mouseX, mouseY, delta);
        matrices.pop();

        anchors.forEach(anchor -> anchor.render(matrices, mouseX, mouseY, delta));
        getDragAnchor().render(matrices, mouseX, mouseY, delta);
    }

    private void resize(double mouseX, double mouseY) {
        anchors.forEach(anchor -> {
            if(anchor.isDragging()) {
                int deltaX = (int) (anchor.startX - mouseX);
                int deltaY = (int) (anchor.startY - mouseY);
                switch (anchor.resizePoint) {
                    case TOP_LEFT -> {
                        this.interactable.setX((int) mouseX);
                        this.interactable.setY((int) mouseY);
                        this.interactable.setWidth(anchor.startWidth + deltaX);
                        ((ClickableWidgetAccessor) this.interactable).setHeight(anchor.startHeight + deltaY);
                    }
                    case TOP_RIGHT -> {
                        this.interactable.setY((int) mouseY);
                        this.interactable.setWidth(anchor.startWidth - deltaX);
                        ((ClickableWidgetAccessor) this.interactable).setHeight(anchor.startHeight + deltaY);
                    }
                    case BOTTOM_LEFT -> {
                        this.interactable.setX((int) mouseX);
                        this.interactable.setWidth(anchor.startWidth + deltaX);
                        ((ClickableWidgetAccessor) this.interactable).setHeight(anchor.startHeight - deltaY);
                    }
                    case BOTTOM_RIGHT -> {
                        this.interactable.setWidth(anchor.startWidth - deltaX);
                        ((ClickableWidgetAccessor) this.interactable).setHeight(anchor.startHeight - deltaY);
                    }
                }
                ((ClickableWidgetAccessor) this).setHeight(this.interactable.getHeight());
            }
            anchor.adjustPosistion();
            super.adjustPosition();
        });
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
        super.appendDefaultNarrations(builder);
    }

    public List<ResizeAnchor> getAnchors() {
        return anchors;
    }

    private enum ResizePoint {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }

    public class ResizeAnchor extends ClickableWidget {
        private boolean dragging = false;
        private final ResizePoint resizePoint;

        private int startX;
        private int startY;
        private int startWidth;
        private int startHeight;

        protected ResizeAnchor(ResizePoint resizePoint) {
            super(switch (resizePoint) {
                    case TOP_LEFT, BOTTOM_LEFT -> interactable.getX() - 3;
                    case TOP_RIGHT, BOTTOM_RIGHT -> interactable.getX() + interactable.getWidth() - 3;
                }, switch (resizePoint) {
                    case TOP_LEFT, TOP_RIGHT -> interactable.getY() - 3;
                    case BOTTOM_LEFT, BOTTOM_RIGHT -> interactable.getY() + interactable.getHeight() - 3;
                }, 6, 6, ScreenTexts.EMPTY);
            this.resizePoint = resizePoint;
        }

        private void adjustPosistion() {
            this.setX(switch (resizePoint) {
                case TOP_LEFT, BOTTOM_LEFT -> interactable.getX() - 3;
                case TOP_RIGHT, BOTTOM_RIGHT -> interactable.getX() + interactable.getWidth() - 3;
            });
            this.setY(switch (resizePoint) {
                case TOP_LEFT, TOP_RIGHT -> interactable.getY() - 3;
                case BOTTOM_LEFT, BOTTOM_RIGHT -> interactable.getY() + interactable.getHeight() - 3;
            });
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
            this.startX = (int) mouseX;
            this.startY = (int) mouseY;
            this.startWidth = ResizeableWidget.this.interactable.getWidth();
            this.startHeight = ResizeableWidget.this.interactable.getHeight();
        }

        @Override
        public void onRelease(double mouseX, double mouseY) {
            this.dragging = false;
        }

        private boolean isDragging() {
            return dragging;
        }
    }
}
