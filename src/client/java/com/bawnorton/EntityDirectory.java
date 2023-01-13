package com.bawnorton;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;

import java.util.List;

public class EntityDirectory {
    public static Entry ALLAY;
    public static Entry AXOLOTL;
    public static Entry BAT;
    public static Entry BEE;
    public static Entry BLAZE;
    public static Entry CAT;
    public static Entry CAMEL;
    public static Entry CAVE_SPIDER;
    public static Entry CHICKEN;
    public static Entry COD;
    public static Entry COW;
    public static Entry CREEPER;
    public static Entry DOLPHIN;
    public static Entry DONKEY;
    public static Entry DROWNED;
    public static Entry ELDER_GUARDIAN;
    public static Entry ENDER_DRAGON;
    public static Entry ENDERMAN;
    public static Entry ENDERMITE;
    public static Entry EVOKER;
    public static Entry FOX;
    public static Entry FROG;
    public static Entry GHAST;
    public static Entry GLOW_SQUID;
    public static Entry GOAT;
    public static Entry GUARDIAN;
    public static Entry HOGLIN;
    public static Entry HORSE;
    public static Entry HUSK;
    public static Entry ILLUSIONER;
    public static Entry IRON_GOLEM;
    public static Entry LLAMA;
    public static Entry MAGMA_CUBE;
    public static Entry MULE;
    public static Entry MOOSHROOM;
    public static Entry OCELOT;
    public static Entry PANDA;
    public static Entry PARROT;
    public static Entry PHANTOM;
    public static Entry PIG;
    public static Entry PIGLIN;
    public static Entry PIGLIN_BRUTE;
    public static Entry PILLAGER;
    public static Entry POLAR_BEAR;
    public static Entry PUFFERFISH;
    public static Entry RABBIT;
    public static Entry RAVAGER;
    public static Entry SALMON;
    public static Entry SHEEP;
    public static Entry SHULKER;
    public static Entry SILVERFISH;
    public static Entry SKELETON;
    public static Entry SKELETON_HORSE;
    public static Entry SLIME;
    public static Entry SNOW_GOLEM;
    public static Entry SPIDER;
    public static Entry SQUID;
    public static Entry STRAY;
    public static Entry STRIDER;
    public static Entry TADPOLE;
    public static Entry TRADER_LLAMA;
    public static Entry TROPICAL_FISH;
    public static Entry TURTLE;
    public static Entry VEX;
    public static Entry VILLAGER;
    public static Entry VINDICATOR;
    public static Entry WANDERING_TRADER;
    public static Entry WARDEN;
    public static Entry WITCH;
    public static Entry WITHER;
    public static Entry WITHER_SKELETON;
    public static Entry WOLF;
    public static Entry ZOGLIN;
    public static Entry ZOMBIE;
    public static Entry ZOMBIE_HORSE;
    public static Entry ZOMBIE_VILLAGER;
    public static Entry ZOMBIFIED_PIGLIN;
    public static Entry PLAYER;

    private static List<Entry> ENTRY_LIST;

    public static void init() {
        ALLAY = Entry.builder().setEntity(createEntity(EntityType.ALLAY)).setExperience(40).setPos(12, 8).setSize(51).addParagraph("The Allay is a small passive flying mob that collects and delivers items that match the item given to it by the player. It will also drop items at any nearby playing noteblock.").addParagraph("They can be found around woodland mansions and pillager outposts.").build();
        AXOLOTL = Entry.builder().setEntity(createEntity(EntityType.AXOLOTL)).setExperience(40).setPos(12, 8).setSize(49).addParagraph("The Axolotl is a semi-aquatic passive mob that can be found in the Lush Caves biome.").addParagraph("They attack other aquatic mobs and grant the player Regeneration when nearby as well as removing the Mining Fatigue effect.").build();
        BAT = Entry.builder().setEntity(createEntity(EntityType.BAT)).setExperience(40).setPos(20, 44).setSize(100).addParagraph("The Bat is a small passive flying mob that can be found in dark underground areas.").addParagraph("These creatures seem to be more active around the Halloween period.").build();
        BEE = Entry.builder().setEntity(createEntity(EntityType.BEE)).setExperience(40).setPos(8, 14).setSize(55).addParagraph("The Bee is a neutral flying mob that likes flowers and forests.").addParagraph("They live in bees nests naturally and produce honey that can be gathered with a bottle or shears.").addParagraph("Smoke seems to calm them down.").build();
        BLAZE = Entry.builder().setEntity(createEntity(EntityType.BLAZE)).setExperience(40).setPos(6, 32).setSize(87).addParagraph("The Blaze is a hostile mob that can be found in Nether Fortresses.").addParagraph("They are immune to fire and attack by shooting fireballs.").build();
        CAT = Entry.builder().setEntity(createEntity(EntityType.CAT)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        CAMEL = Entry.builder().setEntity(createEntity(EntityType.CAMEL)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        CAVE_SPIDER = Entry.builder().setEntity(createEntity(EntityType.CAVE_SPIDER)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        CHICKEN = Entry.builder().setEntity(createEntity(EntityType.CHICKEN)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        COD = Entry.builder().setEntity(createEntity(EntityType.COD)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        COW = Entry.builder().setEntity(createEntity(EntityType.COW)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        CREEPER = Entry.builder().setEntity(createEntity(EntityType.CREEPER)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        DOLPHIN = Entry.builder().setEntity(createEntity(EntityType.DOLPHIN)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        DONKEY = Entry.builder().setEntity(createEntity(EntityType.DONKEY)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        DROWNED = Entry.builder().setEntity(createEntity(EntityType.DROWNED)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        ELDER_GUARDIAN = Entry.builder().setEntity(createEntity(EntityType.ELDER_GUARDIAN)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        ENDER_DRAGON = Entry.builder().setEntity(createEntity(EntityType.ENDER_DRAGON)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        ENDERMAN = Entry.builder().setEntity(createEntity(EntityType.ENDERMAN)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        ENDERMITE = Entry.builder().setEntity(createEntity(EntityType.ENDERMITE)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        EVOKER = Entry.builder().setEntity(createEntity(EntityType.EVOKER)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        FOX = Entry.builder().setEntity(createEntity(EntityType.FOX)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        FROG = Entry.builder().setEntity(createEntity(EntityType.FROG)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        GHAST = Entry.builder().setEntity(createEntity(EntityType.GHAST)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        GLOW_SQUID = Entry.builder().setEntity(createEntity(EntityType.GLOW_SQUID)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        GOAT = Entry.builder().setEntity(createEntity(EntityType.GOAT)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        GUARDIAN = Entry.builder().setEntity(createEntity(EntityType.GUARDIAN)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        HOGLIN = Entry.builder().setEntity(createEntity(EntityType.HOGLIN)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        HORSE = Entry.builder().setEntity(createEntity(EntityType.HORSE)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        HUSK = Entry.builder().setEntity(createEntity(EntityType.HUSK)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        ILLUSIONER = Entry.builder().setEntity(createEntity(EntityType.ILLUSIONER)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        IRON_GOLEM = Entry.builder().setEntity(createEntity(EntityType.IRON_GOLEM)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        LLAMA = Entry.builder().setEntity(createEntity(EntityType.LLAMA)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        MAGMA_CUBE = Entry.builder().setEntity(createEntity(EntityType.MAGMA_CUBE)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        MOOSHROOM = Entry.builder().setEntity(createEntity(EntityType.MOOSHROOM)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        MULE = Entry.builder().setEntity(createEntity(EntityType.MULE)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        OCELOT = Entry.builder().setEntity(createEntity(EntityType.OCELOT)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        PANDA = Entry.builder().setEntity(createEntity(EntityType.PANDA)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        PARROT = Entry.builder().setEntity(createEntity(EntityType.PARROT)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        PHANTOM = Entry.builder().setEntity(createEntity(EntityType.PHANTOM)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        PIG = Entry.builder().setEntity(createEntity(EntityType.PIG)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        PIGLIN = Entry.builder().setEntity(createEntity(EntityType.PIGLIN)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        PIGLIN_BRUTE = Entry.builder().setEntity(createEntity(EntityType.PIGLIN_BRUTE)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        PILLAGER = Entry.builder().setEntity(createEntity(EntityType.PILLAGER)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        POLAR_BEAR = Entry.builder().setEntity(createEntity(EntityType.POLAR_BEAR)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        PUFFERFISH = Entry.builder().setEntity(createEntity(EntityType.PUFFERFISH)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        RABBIT = Entry.builder().setEntity(createEntity(EntityType.RABBIT)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        RAVAGER = Entry.builder().setEntity(createEntity(EntityType.RAVAGER)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        SALMON = Entry.builder().setEntity(createEntity(EntityType.SALMON)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        SHEEP = Entry.builder().setEntity(createEntity(EntityType.SHEEP)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        SHULKER = Entry.builder().setEntity(createEntity(EntityType.SHULKER)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        SILVERFISH = Entry.builder().setEntity(createEntity(EntityType.SILVERFISH)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        SKELETON = Entry.builder().setEntity(createEntity(EntityType.SKELETON)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        SKELETON_HORSE = Entry.builder().setEntity(createEntity(EntityType.SKELETON_HORSE)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        SLIME = Entry.builder().setEntity(createEntity(EntityType.SLIME)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        SNOW_GOLEM = Entry.builder().setEntity(createEntity(EntityType.SNOW_GOLEM)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        SPIDER = Entry.builder().setEntity(createEntity(EntityType.SPIDER)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        SQUID = Entry.builder().setEntity(createEntity(EntityType.SQUID)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        STRAY = Entry.builder().setEntity(createEntity(EntityType.STRAY)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        STRIDER = Entry.builder().setEntity(createEntity(EntityType.STRIDER)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        TADPOLE = Entry.builder().setEntity(createEntity(EntityType.TADPOLE)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        TRADER_LLAMA = Entry.builder().setEntity(createEntity(EntityType.TRADER_LLAMA)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        TROPICAL_FISH = Entry.builder().setEntity(createEntity(EntityType.TROPICAL_FISH)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        TURTLE = Entry.builder().setEntity(createEntity(EntityType.TURTLE)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        VEX = Entry.builder().setEntity(createEntity(EntityType.VEX)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        VILLAGER = Entry.builder().setEntity(createEntity(EntityType.VILLAGER)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        VINDICATOR = Entry.builder().setEntity(createEntity(EntityType.VINDICATOR)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        WANDERING_TRADER = Entry.builder().setEntity(createEntity(EntityType.WANDERING_TRADER)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        WARDEN = Entry.builder().setEntity(createEntity(EntityType.WARDEN)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        WITCH = Entry.builder().setEntity(createEntity(EntityType.WITCH)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        WITHER = Entry.builder().setEntity(createEntity(EntityType.WITHER)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        WITHER_SKELETON = Entry.builder().setEntity(createEntity(EntityType.WITHER_SKELETON)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        WOLF = Entry.builder().setEntity(createEntity(EntityType.WOLF)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        ZOGLIN = Entry.builder().setEntity(createEntity(EntityType.ZOGLIN)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        ZOMBIE = Entry.builder().setEntity(createEntity(EntityType.ZOMBIE)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        ZOMBIE_HORSE = Entry.builder().setEntity(createEntity(EntityType.ZOMBIE_HORSE)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        ZOMBIE_VILLAGER = Entry.builder().setEntity(createEntity(EntityType.ZOMBIE_VILLAGER)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        ZOMBIFIED_PIGLIN = Entry.builder().setEntity(createEntity(EntityType.ZOMBIFIED_PIGLIN)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();
        PLAYER = Entry.builder().setEntity(createEntity(EntityType.PLAYER)).setExperience(0).setPos(0, 0).setSize(0).addParagraph("Empty").build();

        ENTRY_LIST = List.of(ALLAY, AXOLOTL, BAT, BEE, BLAZE, CAT, CAMEL, CAVE_SPIDER, CHICKEN, COD, COW, CREEPER, DOLPHIN,
                DONKEY, DROWNED, ELDER_GUARDIAN, ENDER_DRAGON, ENDERMAN, ENDERMITE, EVOKER, FOX, FROG, GHAST, GLOW_SQUID, GOAT, GUARDIAN,
                HOGLIN, HORSE, HUSK, ILLUSIONER, IRON_GOLEM, LLAMA, MAGMA_CUBE, MULE, MOOSHROOM, OCELOT, PANDA, PARROT,
                PHANTOM, PIG, PIGLIN, PIGLIN_BRUTE, PILLAGER, POLAR_BEAR, PUFFERFISH, RABBIT, RAVAGER, SALMON, SHEEP,
                SHULKER, SILVERFISH, SKELETON, SKELETON_HORSE, SLIME, SNOW_GOLEM, SPIDER, SQUID, STRAY, STRIDER,
                TADPOLE, TRADER_LLAMA, TROPICAL_FISH, TURTLE, VEX, VILLAGER, VINDICATOR, WANDERING_TRADER, WARDEN,
                WITCH, WITHER, WITHER_SKELETON, WOLF, ZOGLIN, ZOMBIE, ZOMBIE_HORSE, ZOMBIE_VILLAGER, ZOMBIFIED_PIGLIN, PLAYER);
        BestiaryContent.init();
    }

    private static LivingEntity createEntity(EntityType<?> entityType) {
        return (LivingEntity) entityType.create(MinecraftClient.getInstance().world);
    }

    public static Entry getEntry(Entity entity) {
        if (entity instanceof PlayerEntity) {
            return PLAYER;
        }

        for (Entry entry : ENTRY_LIST) {
            if (entry.getEntity().getType() == entity.getType()) {
                return entry;
            }
        }

        return null;
    }

    public static List<Entry> getEntryList() {
        return ENTRY_LIST;
    }
}
