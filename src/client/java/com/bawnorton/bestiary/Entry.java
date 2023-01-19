package com.bawnorton.bestiary;

import com.bawnorton.Bestiary;
import com.bawnorton.mixin.invoker.AnimalModelInvoker;
import com.bawnorton.mixin.accessor.ModelPartAccessor;
import com.bawnorton.mixin.invoker.SinglePartEntityModelInvoker;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.EnderDragonEntityRenderer;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Entry {
    private final Identifier identifier;
    private final LivingEntity entity;
    private final String name;
    private final String description;
    private final int x;
    private final int y;
    private final int size;
    private final int experience;

    private boolean discovered;

    public static final Entry EMPTY = new Entry(null, null, "",  0, 0, 0, 0);

    protected Entry(Identifier identifier, LivingEntity entity, String description, int x, int y, int size, int experience) {
        this.identifier = identifier;
        this.entity = entity;
        this.description = description;
        this.x = x;
        this.y = y;
        this.size = size;
        this.experience = experience;

        this.discovered = false;
        this.name = Registries.ENTITY_TYPE.get(identifier).getName().getString();
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getSize() {
        return size;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public boolean discover() {
        if (!discovered) {
            discovered = true;
            return true;
        }
        return false;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Objects.equals(getIdentifier(), entry.getIdentifier());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentifier());
    }

    public static EntryBuilder builder() {
        return new EntryBuilder();
    }

    public static class EntryBuilder {
        private Identifier identifier = null;
        private LivingEntity entity = null;
        private String description = "";
        private int experience = -1;

        private static final int MAX_LENGTH = 256;

        public EntryBuilder setEntity(LivingEntity entity) {
            if(this.entity != null) throw new IllegalStateException("%s already set".formatted(identifier));
            if(entity == null) throw new IllegalArgumentException("Entity cannot be null");
            this.entity = entity;
            this.identifier = Registries.ENTITY_TYPE.getId(entity.getType());
            return this;
        }

        public EntryBuilder addParagraph(String description) {
            if(this.description.length() + description.length() > MAX_LENGTH) throw new IllegalArgumentException("[%s]: Description too long (%s/%s)".formatted(Registries.ENTITY_TYPE.getId(entity.getType()), this.description.length() + description.length(), MAX_LENGTH));
            if(description.length() == 0) throw new IllegalArgumentException("[%s]: Description cannot be empty".formatted(identifier));
            this.description += description + "\n";
            return this;
        }

        public EntryBuilder setExperience(int experience) {
            if(this.experience != -1) throw new IllegalStateException("[%s]: Experience already set".formatted(identifier));
            if(experience < 0) throw new IllegalArgumentException("[%s]: Experience must be greater than 0".formatted(identifier));
            this.experience = experience;
            return this;
        }

        private List<ModelPart> getChildren(ModelPart root) {
            List<ModelPart> children = new ArrayList<>();
            for (ModelPart child : ((ModelPartAccessor) (Object) root).getChildren().values()) {
                children.add(child);
                children.addAll(getChildren(child));
            }
            return children;
        }

        private int[] getDimensions() {
            EntityModel<? extends Entity> model = EntityDirectory.MODEL_MAP.get(Registries.ENTITY_TYPE.get(identifier));
            if(model == null) throw new NullPointerException("Model not found for %s".formatted(identifier));
            List<ModelPart> parts = new ArrayList<>();

            if(model instanceof AnimalModel<?> animalModel) {
                Iterable<ModelPart> headParts = ((AnimalModelInvoker) animalModel).getHeadParts();
                Iterable<ModelPart> bodyParts = ((AnimalModelInvoker) animalModel).getBodyParts();
                for(ModelPart part : headParts) {
                    parts.add(part);
                    parts.addAll(getChildren(part));
                }
                for(ModelPart part : bodyParts) {
                    parts.add(part);
                    parts.addAll(getChildren(part));
                }
            } else if (model instanceof SinglePartEntityModel<?> singlePartEntityModel) {
                ModelPart root = ((SinglePartEntityModelInvoker) singlePartEntityModel).getPart();
                parts.add(root);
                parts.addAll(getChildren(root));
            } else if (model instanceof EnderDragonEntityRenderer.DragonEntityModel) {
                parts = EntityDirectory.ENTITY_PARTS.get(EntityType.ENDER_DRAGON);
            } else if (model instanceof LlamaEntityModel) {
                parts = EntityDirectory.ENTITY_PARTS.get(EntityType.LLAMA);
            } else if (model instanceof RabbitEntityModel) {
                parts = EntityDirectory.ENTITY_PARTS.get(EntityType.RABBIT);
            } else if (model instanceof ShulkerEntityModel) {
                parts = EntityDirectory.ENTITY_PARTS.get(EntityType.SHULKER);
            } else {
                throw new IllegalStateException("[%s]: Model not supported: %s".formatted(identifier, model.getClass().getName()));
            }

            float minX = Float.MAX_VALUE;
            float maxX = Float.MIN_VALUE;
            float minY = Float.MAX_VALUE;
            float maxY = Float.MIN_VALUE;
            if(entity instanceof AllayEntity) {
                Bestiary.LOGGER.info(parts.size() + "");
            }
            for(ModelPart part: parts) {
                List<ModelPart.Cuboid> cuboids = ((ModelPartAccessor) (Object) part).getCuboids();
                for(ModelPart.Cuboid cuboid: cuboids) {
                    if(entity instanceof AllayEntity) {
                        Bestiary.LOGGER.info("Cuboid: min(%s, %s), max(%s, %s)".formatted(cuboid.minX, cuboid.minY, cuboid.maxX, cuboid.maxY));
                    }
                    minX = Math.min(minX, cuboid.minX);
                    maxX = Math.max(maxX, cuboid.maxX);
                    minY = Math.min(minY, cuboid.minY);
                    maxY = Math.max(maxY, cuboid.maxY);
                }
            }

            int x = (int) (minX * 16);
            int y = (int) (minY * 16);
            int size = (int) (50 / (maxX - minX + maxY - minY) * 8);
            Bestiary.LOGGER.info("Dimensions for %s: x=%d, y=%d, size=%d".formatted(identifier, x, y, size));
            return new int[] {x, y, size};
        }

        public Entry build() {
            if(this.entity == null) throw new IllegalStateException("Entity not set");
            if(this.description.length() == 0) throw new IllegalStateException("[%s]: Description not set".formatted(identifier));
            if(this.experience == -1) experience = 0;
            int[] dimensions = getDimensions();
            return new Entry(identifier, entity, description, dimensions[0], dimensions[1], dimensions[2], experience);
        }
    }
}
