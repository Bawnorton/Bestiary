package com.bawnorton.mixin;

import com.bawnorton.screen.widgets.DraggableWidget;
import com.bawnorton.screen.widgets.ResizeableWidget;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Screen.class)
public abstract class ScreenMixin {

    @Shadow protected abstract <T extends Element & Drawable & Selectable> T addDrawableChild(T drawableElement);

    @Inject(method = "addDrawableChild", at = @At("RETURN"))
    private <T extends Element & Drawable & Selectable> void addAnchors(T drawableElement, CallbackInfoReturnable<T> cir) {
        if (drawableElement instanceof ResizeableWidget resizeableWidget) {
            resizeableWidget.getAnchors().forEach(this::addDrawableChild);
            addDrawableChild(resizeableWidget.getDragAnchor());
        } else if(drawableElement instanceof DraggableWidget draggableWidget) {
            addDrawableChild(draggableWidget.getDragAnchor());
        }
    }
}
