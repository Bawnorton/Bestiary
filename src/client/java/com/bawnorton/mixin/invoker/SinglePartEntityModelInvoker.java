package com.bawnorton.mixin.invoker;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SinglePartEntityModel.class)
public interface SinglePartEntityModelInvoker {
    @Invoker("getPart")
    ModelPart getPart();
}
