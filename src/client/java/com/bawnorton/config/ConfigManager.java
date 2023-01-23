package com.bawnorton.config;

import com.bawnorton.Bestiary;
import com.bawnorton.BestiaryClient;
import com.bawnorton.bestiary.BestiaryContent;
import com.bawnorton.bestiary.BestiaryEntry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path configPath = FabricLoader.getInstance().getConfigDir().resolve("bestiary.json");

    public static void loadConfig() {
        Config config = load();

        for (Config.Entry configEntry : config.entries) {
            Identifier id = configEntry.identifier;
            EntityType<? extends LivingEntity> entityType = (EntityType<? extends LivingEntity>) Registries.ENTITY_TYPE.get(id);
            if (entityType.getSpawnGroup().equals(SpawnGroup.MISC)) {
                Bestiary.LOGGER.warn("Entity " + id + " is probably not a living entity, skipping");
                continue;
            }

            if (entityType == EntityType.PIG && !id.equals(Registries.ENTITY_TYPE.getId(EntityType.PIG))) {
                Bestiary.LOGGER.warn("Unable to find entity matching \"%s\", using pig instead".formatted(id));
            }
            if (configEntry.scale == null || configEntry.scale < 0F) configEntry.scale = 1F;
            if (configEntry.yOffset == null) configEntry.yOffset = 0;
            if (configEntry.experience == null || configEntry.experience < 0) configEntry.experience = 0;
            if (configEntry.description == null) configEntry.description = id.toString().toUpperCase();

            if (configEntry.description.length() > 256) {
                Bestiary.LOGGER.warn("Description for \"%s\" is too long, truncating to 256 characters".formatted(id));
                configEntry.description = configEntry.description.substring(0, 256);
            }

            BestiaryEntry bestiaryEntry = BestiaryEntry.builder(entityType)
                    .setScale(configEntry.scale)
                    .setExperience(configEntry.experience)
                    .addY(configEntry.yOffset)
                    .setDescription(configEntry.description)
                    .build();

            BestiaryContent.ENTRIES.put(entityType, bestiaryEntry);
        }

        Config.update(config);
        save();
        BestiaryClient.LOGGER.info("Loaded %s entries from config".formatted(BestiaryContent.ENTRIES.size()));
    }

    private static Config load() {
        Config config = Config.getInstance();
        try {
            if (!Files.exists(configPath)) {
                Files.createDirectories(configPath.getParent());
                Files.createFile(configPath);
                return config;
            }
            try {
                config = GSON.fromJson(Files.newBufferedReader(configPath), Config.class);
            } catch (JsonSyntaxException e) {
                BestiaryClient.LOGGER.error("Failed to parse config file, using default config");
                config = new Config();
            }
        } catch (IOException e) {
            BestiaryClient.LOGGER.error("Failed to load config", e);
        }
        return config == null ? new Config() : config;
    }

    private static void save() {
        try {
            Files.write(configPath, GSON.toJson(Config.getInstance()).getBytes());
        } catch (IOException e) {
            BestiaryClient.LOGGER.error("Failed to save config", e);
        }
    }
}
