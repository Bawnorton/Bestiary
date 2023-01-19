package com.bawnorton.mixin;

import com.bawnorton.bestiary.EntityDirectory;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.LlamaEntityModel;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(LlamaEntityModel.class)
public class LlamaEntityModelMixin {
    @Shadow @Final private ModelPart head;
    @Shadow @Final private ModelPart body;
    @Shadow @Final private ModelPart rightHindLeg;
    @Shadow @Final private ModelPart leftHindLeg;
    @Shadow @Final private ModelPart rightFrontLeg;
    @Shadow @Final private ModelPart leftFrontLeg;
    @Shadow @Final private ModelPart rightChest;
    @Shadow @Final private ModelPart leftChest;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(ModelPart part, CallbackInfo ci) {
        EntityDirectory.ENTITY_PARTS.put(EntityType.LLAMA, List.of(
                head, body, rightHindLeg, leftHindLeg, rightFrontLeg, leftFrontLeg, rightChest, leftChest
        ));
    }
}
