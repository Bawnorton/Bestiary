package com.bawnorton;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

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

    protected Entry(Identifier identifier, LivingEntity entity, String description, int x, int y, int size, int experience) {
        this.identifier = identifier;
        this.entity = entity;
        this.description = description;
        this.experience = experience;
        this.x = x;
        this.y = y;
        this.size = size;

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

    public static EntryBuilder builder() {
        return new EntryBuilder();
    }


    public static class EntryBuilder {
        private Identifier identifier = null;
        private LivingEntity entity = null;
        private String description = "";
        private int experience = -1;
        private int x = 0;
        private int y = 0;
        private int size = -1;

        private static final int MAX_LENGTH = 256;

        public EntryBuilder setEntity(LivingEntity entity) {
            if(this.entity != null) throw new IllegalStateException("Entity already set");
            this.entity = entity;
            this.identifier = Registries.ENTITY_TYPE.getId(entity.getType());
            return this;
        }

        public EntryBuilder setEntity(Entity entity) {
            if(entity instanceof LivingEntity) {
                return setEntity((LivingEntity) entity);
            }
            throw new IllegalArgumentException("Entity must be a LivingEntity");
        }

        public EntryBuilder setPos(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public EntryBuilder setSize(int size) {
            if(size <= 0) throw new IllegalArgumentException("Size must be greater than 0");
            this.size = size;
            return this;
        }

        public EntryBuilder addParagraph(String description) {
            if(this.description.length() + description.length() > MAX_LENGTH) throw new IllegalArgumentException("Description too long");
            if(description.length() == 0) throw new IllegalArgumentException("Description cannot be empty");
            this.description += description + "\n";
            return this;
        }

        public EntryBuilder setExperience(int experience) {
            if(this.experience != -1) throw new IllegalStateException("Experience already set");
            if(experience < 0) throw new IllegalArgumentException("Experience cannot be negative");
            this.experience = experience;
            return this;
        }

        public Entry build() {
            if(this.entity == null) throw new IllegalStateException("Entity not set");
            if(this.description.length() == 0) throw new IllegalStateException("Description not set");
            if(this.experience == -1) experience = 0;
            if(this.size == -1) size = 1;
            return new Entry(identifier, entity, description, x, y, size, experience);
        }
    }
}
