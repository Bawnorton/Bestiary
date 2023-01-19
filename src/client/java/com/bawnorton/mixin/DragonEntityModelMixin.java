package com.bawnorton.mixin;

import com.bawnorton.bestiary.EntityDirectory;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.EnderDragonEntityRenderer;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(EnderDragonEntityRenderer.DragonEntityModel.class)
public class DragonEntityModelMixin {

    @Shadow @Final private ModelPart head;
    @Shadow @Final private ModelPart jaw;
    @Shadow @Final private ModelPart neck;
    @Shadow @Final private ModelPart body;
    @Shadow @Final private ModelPart leftWing;
    @Shadow @Final private ModelPart leftWingTip;
    @Shadow @Final private ModelPart leftFrontLeg;
    @Shadow @Final private ModelPart leftFrontLegTip;
    @Shadow @Final private ModelPart leftFrontFoot;
    @Shadow @Final private ModelPart leftHindLeg;
    @Shadow @Final private ModelPart leftHindLegTip;
    @Shadow @Final private ModelPart leftHindFoot;
    @Shadow @Final private ModelPart rightWing;
    @Shadow @Final private ModelPart rightWingTip;
    @Shadow @Final private ModelPart rightFrontLeg;
    @Shadow @Final private ModelPart rightFrontLegTip;
    @Shadow @Final private ModelPart rightFrontFoot;
    @Shadow @Final private ModelPart rightHindLeg;
    @Shadow @Final private ModelPart rightHindLegTip;
    @Shadow @Final private ModelPart rightHindFoot;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(ModelPart part, CallbackInfo ci) {
        EntityDirectory.ENTITY_PARTS.put(EntityType.ENDER_DRAGON, List.of(
                head, jaw, neck, body, leftWing, leftWingTip, leftFrontLeg, leftFrontLegTip, leftFrontFoot, leftHindLeg,
                leftHindLegTip, leftHindFoot, rightWing, rightWingTip, rightFrontLeg, rightFrontLegTip, rightFrontFoot,
                rightHindLeg, rightHindLegTip, rightHindFoot
        ));
    }
}
