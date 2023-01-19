package com.bawnorton.mixin.accessor;

import net.minecraft.client.model.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import java.util.Map;

@Mixin(ModelPart.class)
public interface ModelPartAccessor {
    @Accessor
    List<ModelPart.Cuboid> getCuboids();

    @Accessor
    Map<String, ModelPart> getChildren();
}
