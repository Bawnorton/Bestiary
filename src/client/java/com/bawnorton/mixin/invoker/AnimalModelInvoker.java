package com.bawnorton.mixin.invoker;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AnimalModel.class)
public interface AnimalModelInvoker {
    @Invoker("getHeadParts")
    Iterable<ModelPart> getHeadParts();

    @Invoker("getBodyParts")
    Iterable<ModelPart> getBodyParts();
}
