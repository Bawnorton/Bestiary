package com.bawnorton.mixin;

import com.bawnorton.bestiary.EntityDirectory;
import net.minecraft.client.render.entity.EnderDragonEntityRenderer;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

@Mixin(EntityModel.class)
public class EntityModelMixin {
    @Inject(method = "<init>(Ljava/util/function/Function;)V", at = @At("RETURN"))
    private void init(Function<?, ?> function, CallbackInfo ci) {
        EntityModel<? extends Entity> thisModel = (EntityModel<?>) (Object) this;
        if(thisModel instanceof EnderDragonEntityRenderer.DragonEntityModel) {
            EntityDirectory.MODEL_MAP.put(EntityType.ENDER_DRAGON, thisModel);
            return;
        } else if (thisModel instanceof IllagerEntityModel) {
            EntityDirectory.MODEL_MAP.put(EntityType.VINDICATOR, thisModel);
            EntityDirectory.MODEL_MAP.put(EntityType.EVOKER, thisModel);
            EntityDirectory.MODEL_MAP.put(EntityType.PILLAGER, thisModel);
            EntityDirectory.MODEL_MAP.put(EntityType.ILLUSIONER, thisModel);
            return;
        } else if (thisModel instanceof SpiderEntityModel) {
            EntityDirectory.MODEL_MAP.put(EntityType.SPIDER, thisModel);
            EntityDirectory.MODEL_MAP.put(EntityType.CAVE_SPIDER, thisModel);
            return;
        } else if (thisModel instanceof GuardianEntityModel) {
            EntityDirectory.MODEL_MAP.put(EntityType.GUARDIAN, thisModel);
            EntityDirectory.MODEL_MAP.put(EntityType.ELDER_GUARDIAN, thisModel);
            return;
        } else if (thisModel instanceof SquidEntityModel) {
            EntityDirectory.MODEL_MAP.put(EntityType.SQUID, thisModel);
            EntityDirectory.MODEL_MAP.put(EntityType.GLOW_SQUID, thisModel);
            return;
        } else if (thisModel instanceof ZombieEntityModel) {
            EntityDirectory.MODEL_MAP.put(EntityType.ZOMBIE, thisModel);
            EntityDirectory.MODEL_MAP.put(EntityType.DROWNED, thisModel);
            EntityDirectory.MODEL_MAP.put(EntityType.HUSK, thisModel);
            return;
        } else if (thisModel instanceof DonkeyEntityModel) {
            EntityDirectory.MODEL_MAP.put(EntityType.DONKEY, thisModel);
            EntityDirectory.MODEL_MAP.put(EntityType.MULE, thisModel);
            return;
        } else if (thisModel instanceof CowEntityModel) {
            EntityDirectory.MODEL_MAP.put(EntityType.COW, thisModel);
            EntityDirectory.MODEL_MAP.put(EntityType.MOOSHROOM, thisModel);
            return;
        } else if (thisModel instanceof PiglinEntityModel) {
            EntityDirectory.MODEL_MAP.put(EntityType.PIGLIN, thisModel);
            EntityDirectory.MODEL_MAP.put(EntityType.PIGLIN_BRUTE, thisModel);
            EntityDirectory.MODEL_MAP.put(EntityType.ZOMBIFIED_PIGLIN, thisModel);
            return;
        } else if (thisModel instanceof HoglinEntityModel) {
            EntityDirectory.MODEL_MAP.put(EntityType.HOGLIN, thisModel);
            EntityDirectory.MODEL_MAP.put(EntityType.ZOGLIN, thisModel);
            return;
        } else if (thisModel instanceof LargePufferfishEntityModel) {
            EntityDirectory.MODEL_MAP.put(EntityType.PUFFERFISH, thisModel);
            return;
        } else if (thisModel instanceof HorseEntityModel) {
            EntityDirectory.MODEL_MAP.put(EntityType.HORSE, thisModel);
            EntityDirectory.MODEL_MAP.put(EntityType.SKELETON_HORSE, thisModel);
            EntityDirectory.MODEL_MAP.put(EntityType.ZOMBIE_HORSE, thisModel);
            return;
        } else if (thisModel instanceof SkeletonEntityModel) {
            EntityDirectory.MODEL_MAP.put(EntityType.SKELETON, thisModel);
            EntityDirectory.MODEL_MAP.put(EntityType.STRAY, thisModel);
            EntityDirectory.MODEL_MAP.put(EntityType.WITHER_SKELETON, thisModel);
            return;
        } else if (thisModel instanceof LlamaEntityModel) {
            EntityDirectory.MODEL_MAP.put(EntityType.LLAMA, thisModel);
            EntityDirectory.MODEL_MAP.put(EntityType.TRADER_LLAMA, thisModel);
            return;
        } else if (thisModel instanceof LargeTropicalFishEntityModel) {
            EntityDirectory.MODEL_MAP.put(EntityType.TROPICAL_FISH, thisModel);
            return;
        } else if (thisModel instanceof WitchEntityModel) {
            EntityDirectory.MODEL_MAP.put(EntityType.WITCH, thisModel);
            return;
        } else if (thisModel instanceof VillagerResemblingModel) {
            EntityDirectory.MODEL_MAP.put(EntityType.VILLAGER, thisModel);
            EntityDirectory.MODEL_MAP.put(EntityType.WANDERING_TRADER, thisModel);
            return;
        }

        String[] path = thisModel.getClass().getName().split("\\.");
        String name = path[path.length - 1].split("EntityModel")[0];
        name = name.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
        EntityType<? extends LivingEntity> entityType = getEntityType(name);
        if(entityType == EntityType.PIG && !(thisModel instanceof PigEntityModel)) return;
        EntityDirectory.MODEL_MAP.put(entityType, thisModel);
    }

    @SuppressWarnings("unchecked")
    private static EntityType<? extends LivingEntity> getEntityType(String name) {
        Identifier id = new Identifier("minecraft", name);
        return (EntityType<? extends LivingEntity>) Registries.ENTITY_TYPE.get(id);
    }
}
