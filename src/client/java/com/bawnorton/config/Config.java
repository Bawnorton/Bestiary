package com.bawnorton.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.List;

public class Config {
    private static Config INSTANCE;

    // default values
    @Expose
    @SerializedName("entries")
    public List<Config.Entry> entries = List.of(
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.ALLAY), 0.9F, 40, "ALLAY"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.AXOLOTL), 0.8F, 15, 40, "AXOLOTL"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.BAT), 1.3F, 40, "BAT"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.BEE), 1.1F, 40, "BEE"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.BLAZE), 1F, 40, "BLAZE"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.CAT), 1F, 10, 0, "CAT"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.CAMEL), 1F, 0, "CAMEL"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.CAVE_SPIDER), 0.8F, 20,0, "CAVE_SPIDER"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.CHICKEN), 0.9F, 0, "CHICKEN"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.COD), 0.8F, 20, 0, "COD"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.COW), 1.1F, 0, "COW"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.CREEPER), 1.2F, 0, "CREEPER"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.DOLPHIN), 0.7F, 10, 0, "DOLPHIN"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.DONKEY), 1.4F, 0, "DONKEY"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.DROWNED), 1.1F, 0, "DROWNED"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.ELDER_GUARDIAN), 0.8F, 15,0, "ELDER_GUARDIAN"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.ENDER_DRAGON), 1F, 0, "ENDER_DRAGON"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.ENDERMAN), 1.2F, 0, "ENDERMAN"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.ENDERMITE), 1F, 20, 0, "ENDERMITE"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.EVOKER), 1.2F, 0, "EVOKER"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.FOX), 0.8F, 0, "FOX"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.FROG), 0.9F, 0, "FROG"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.GHAST), 1F, 20, 0, "GHAST"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.GLOW_SQUID), 0.7F, 40, 0, "GLOW_SQUID"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.GOAT), 1.2F, 0, "GOAT"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.GUARDIAN), 1F, 0, "GUARDIAN"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.HOGLIN), 1.2F, 0, "HOGLIN"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.HORSE), 1.3F, 0, "HORSE"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.HUSK), 1.2F, 0, "HUSK"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.ILLUSIONER), 1.2F, 0, "ILLUSIONER"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.IRON_GOLEM), 1.5F, 0, "IRON_GOLEM"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.LLAMA), 1.3F, 0, "LLAMA"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.MAGMA_CUBE), 5F, 10, 0, "MAGMA_CUBE"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.MULE), 1.4F, 0, "MULE"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.MOOSHROOM), 1.1F, 0, "MOOSHROOM"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.OCELOT), 0.8F, 10, 0, "OCELOT"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.PANDA), 1.1F, 15, 0, "PANDA"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.PARROT), 1.3F, 0, "PARROT"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.PHANTOM), 0.8F, 20, 0, "PHANTOM"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.PIG), 1.2F, 0, "PIG"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.PIGLIN), 1.1F, 0, "PIGLIN"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.PIGLIN_BRUTE), 1.1F, 0, "PIGLIN_BRUTE"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.PILLAGER), 1.2F, 0, "PILLAGER"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.PLAYER), 1F, 0, "PLAYER"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.POLAR_BEAR), 1.2F, 0, "POLAR_BEAR"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.PUFFERFISH), 1.4F, 0, "PUFFERFISH"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.RABBIT), 1.3F, 0, "RABBIT"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.RAVAGER), 1.3F, 0, "RAVAGER"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.SALMON), 0.9F, 0, "SALMON"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.SHEEP), 1.2F, 0, "SHEEP"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.SHULKER), 1.2F, 10, 0, "SHULKER"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.SILVERFISH), 0.6F, 0, "SILVERFISH"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.SKELETON), 1.2F, 0, "SKELETON"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.SKELETON_HORSE), 1.4F, 0, "SKELETON_HORSE"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.SLIME), 5F, 10, 0, "SLIME"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.SNOW_GOLEM), 1.2F, 0, "SNOW_GOLEM"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.SPIDER), 1.2F, 20, 0, "SPIDER"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.SQUID), 0.7F, 40, 0, "SQUID"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.STRAY), 1.2F, 0, "STRAY"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.STRIDER), 1.2F, 0, "STRIDER"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.TADPOLE), 1.2F, 10, 0, "TADPOLE"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.TRADER_LLAMA), 1.3F, 0, "TRADER_LLAMA"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.TROPICAL_FISH), 1.4F, 20, 0, "TROPICAL_FISH"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.TURTLE), 0.8F, 20,0, "TURTLE"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.VEX), 1.2F, 0, "VEX"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.VILLAGER), 1.2F, 0, "VILLAGER"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.VINDICATOR), 1.2F, 0, "VINDICATOR"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.WANDERING_TRADER), 1.2F, 0, "WANDERING_TRADER"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.WARDEN), 1.2F, 0, "WARDEN"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.WITCH), 1F, 0, "WITCH"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.WITHER), 1.2F, -10, 0, "WITHER"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.WITHER_SKELETON), 1.2F, 0, "WITHER_SKELETON"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.WOLF), 1.2F, 0, "WOLF"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.ZOGLIN), 1.3F, 5, 0, "ZOGLIN"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.ZOMBIE), 1.2F, 0, "ZOMBIE"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.ZOMBIE_HORSE), 1.4F, 5, 0, "ZOMBIE_HORSE"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.ZOMBIE_VILLAGER), 1.2F, 0, "ZOMBIE_VILLAGER"),
            Entry.of(Registries.ENTITY_TYPE.getId(EntityType.ZOMBIFIED_PIGLIN), 1.2F, 0, "ZOMBIFIED_PIGLIN")
    );

    public static Config getInstance() {
        if (INSTANCE == null) INSTANCE = new Config();
        return INSTANCE;
    }

    public static void update(Config config) {
        INSTANCE = config;
    }

    public static class Entry {
        @Expose
        @SerializedName("identifier")
        public Identifier identifier;
        @Expose
        @SerializedName("scale")
        public Float scale;
        @Expose
        @SerializedName("y_offset")
        public Integer yOffset;
        @Expose
        @SerializedName("experience")
        public Integer experience;
        @Expose
        @SerializedName("description")
        public String description;

        protected Entry(Identifier identifier, float scale, int yOffset, int experience, String description) {
            this.identifier = identifier;
            this.scale = scale;
            this.yOffset = yOffset;
            this.experience = experience;
            this.description = description;
        }

        public static Entry of(Identifier identifier, float scale, int yOffset, int experience, String description) {
            return new Entry(identifier, scale, yOffset, experience, description);
        }

        public static Entry of(Identifier identifier, float scale, int experience, String description) {
            return new Entry(identifier, scale, 0, experience, description);
        }
    }
}
