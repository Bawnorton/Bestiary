package com.bawnorton.bestiary;

import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;

import java.util.Objects;

public class Entry {
    private final Identifier identifier;
    private final LivingEntity entity;
    private final String name;
    private final String description;
    private final int size;
    private final int yOffset;
    private final int experience;

    private boolean discovered;

    public static final Entry EMPTY = new Entry(null, null, "", 0, 0, 0);

    protected Entry(Identifier identifier, LivingEntity entity, String description, int size, int yOffset, int experience) {
        this.identifier = identifier;
        this.entity = entity;
        this.description = description;
        this.size = size;
        this.yOffset = yOffset;
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
        private int experience = 0;
        private float scale = 1F;
        private int yOffset = 0;

        private static final int MAX_LENGTH = 256;

        public EntryBuilder setEntity(LivingEntity entity) {
            if(entity == null) throw new IllegalArgumentException("Entity cannot be null");
            this.entity = entity;
            this.identifier = Registries.ENTITY_TYPE.getId(entity.getType());
            return this;
        }

        public EntryBuilder addParagraph(String description) {
            if(this.description.length() + description.length() > MAX_LENGTH) throw new IllegalArgumentException("[%s]: Description too long (%s/%s)".formatted(Registries.ENTITY_TYPE.getId(entity.getType()), this.description.length() + description.length(), MAX_LENGTH));
            this.description += description + "\n";
            return this;
        }

        public EntryBuilder setExperience(int experience) {
            if(experience < 0) throw new IllegalArgumentException("[%s]: Experience must be greater than 0".formatted(identifier));
            this.experience = experience;
            return this;
        }

        public EntryBuilder setScale(float scale) {
            if(scale <= 0) throw new IllegalArgumentException("[%s]: Scale must be greater than 0".formatted(identifier));
            this.scale = scale;
            return this;
        }

        public EntryBuilder addY(int y) {
            this.yOffset += y;
            return this;
        }

        private int getSize() {
            Box boundingBox = entity.getBoundingBox();
            float width = (float) boundingBox.getXLength();
            float height = (float) boundingBox.getYLength();
            return (int) ((40 / ((width + height) / 2)) * scale);
        }

        public Entry build() {
            if(this.entity == null) throw new IllegalStateException("Entity not set");
            if(this.description.length() == 0) throw new IllegalStateException("[%s]: Description not set".formatted(identifier));
            return new Entry(identifier, entity, description, getSize(), yOffset, experience);
        }
    }
}
