package com.bawnorton.bestiary;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;

import java.util.Objects;

public class BestiaryEntry {
    public static final BestiaryEntry EMPTY = new BestiaryEntry(null, null, "", 0, 0, 0);
    private final Identifier identifier;
    private final EntityType<? extends LivingEntity> entityType;
    private final String name;
    private final String description;
    private final int size;
    private final int yOffset;
    private final int experience;
    private boolean discovered;
    private LivingEntity cachedEntity;

    protected BestiaryEntry(Identifier identifier, EntityType<? extends LivingEntity> entityType, String description, int size, int yOffset, int experience) {
        this.identifier = identifier;
        this.entityType = entityType;
        this.description = description;
        this.size = size;
        this.yOffset = yOffset;
        this.experience = experience;

        this.discovered = false;
        this.name = Registries.ENTITY_TYPE.get(identifier).getName().getString();
    }

    public static EntryBuilder builder(EntityType<? extends LivingEntity> entityType) {
        return new EntryBuilder(entityType);
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public EntityType<? extends LivingEntity> getEntityType() {
        return entityType;
    }

    public LivingEntity getEntity() {
        if(cachedEntity == null) cachedEntity = entityType.create(MinecraftClient.getInstance().world);
        return cachedEntity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getSize() {
        return size;
    }

    public int getYOffset() {
        return yOffset;
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
        BestiaryEntry bestiaryEntry = (BestiaryEntry) o;
        return Objects.equals(getIdentifier(), bestiaryEntry.getIdentifier());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentifier());
    }

    public static class EntryBuilder {
        private static final int MAX_LENGTH = 256;
        private final Identifier identifier;
        private final EntityType<? extends LivingEntity> entityType;
        private String description = "";
        private int experience = 0;
        private float scale = 1F;
        private int yOffset = 0;

        protected EntryBuilder(EntityType<? extends LivingEntity> entityType) {
            this.entityType = entityType;
            this.identifier = Registries.ENTITY_TYPE.getId(entityType);
        }

        public EntryBuilder setDescription(String description) {
            if (description.length() > MAX_LENGTH)
                throw new IllegalArgumentException("[%s]: Description too long (%s/%s)".formatted(identifier, description.length(), MAX_LENGTH));
            this.description = description;
            return this;
        }

        public EntryBuilder setExperience(int experience) {
            if (experience < 0)
                throw new IllegalArgumentException("[%s]: Experience must be greater than 0".formatted(identifier));
            this.experience = experience;
            return this;
        }

        public EntryBuilder setScale(float scale) {
            if (scale <= 0)
                throw new IllegalArgumentException("[%s]: Scale must be greater than 0".formatted(identifier));
            this.scale = scale;
            return this;
        }

        public EntryBuilder addY(int y) {
            this.yOffset += y;
            return this;
        }

        private int getSize() {
            EntityDimensions dimensions = entityType.getDimensions();
            return (int) ((40 / ((dimensions.width + dimensions.height) / 2)) * scale);
        }

        public BestiaryEntry build() {
            if (this.entityType == null) throw new IllegalStateException("Entity not set");
            if (this.description.length() == 0)
                throw new IllegalStateException("[%s]: Description not set".formatted(identifier));
            return new BestiaryEntry(identifier, entityType, description, getSize(), yOffset, experience);
        }
    }
}
