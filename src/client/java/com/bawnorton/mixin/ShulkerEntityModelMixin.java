package com.bawnorton.mixin;

import com.bawnorton.bestiary.EntityDirectory;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.ShulkerEntityModel;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ShulkerEntityModel.class)
public class ShulkerEntityModelMixin {
    @Shadow @Final private ModelPart base;
    @Shadow @Final private ModelPart lid;
    @Shadow @Final private ModelPart head;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(ModelPart root, CallbackInfo ci) {
        EntityDirectory.ENTITY_PARTS.put(EntityType.SHULKER, List.of(
                base, lid, head
        ));
    }
}
