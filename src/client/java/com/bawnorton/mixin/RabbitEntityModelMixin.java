package com.bawnorton.mixin;

import com.bawnorton.bestiary.EntityDirectory;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.RabbitEntityModel;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(RabbitEntityModel.class)
public class RabbitEntityModelMixin {
    @Shadow @Final private ModelPart leftHindLeg;
    @Shadow @Final private ModelPart rightHindLeg;
    @Shadow @Final private ModelPart leftHaunch;
    @Shadow @Final private ModelPart rightHaunch;
    @Shadow @Final private ModelPart body;
    @Shadow @Final private ModelPart leftFrontLeg;
    @Shadow @Final private ModelPart rightFrontLeg;
    @Shadow @Final private ModelPart head;
    @Shadow @Final private ModelPart rightEar;
    @Shadow @Final private ModelPart leftEar;
    @Shadow @Final private ModelPart tail;
    @Shadow @Final private ModelPart nose;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        EntityDirectory.ENTITY_PARTS.put(EntityType.RABBIT, List.of(
                leftHindLeg, rightHindLeg, leftHaunch, rightHaunch, body, leftFrontLeg, rightFrontLeg, head, rightEar, leftEar, tail, nose
        ));
    }
}
