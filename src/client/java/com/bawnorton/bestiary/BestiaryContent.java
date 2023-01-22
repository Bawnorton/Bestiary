package com.bawnorton.bestiary;

import com.bawnorton.BestiaryClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BestiaryContent {
    public static final Map<EntityType<? extends Entity>, BestiaryEntry> ENTRIES = new HashMap<>();
    private static final List<BestiaryEntry> ACTIVE_PAGES = new ArrayList<>();

    public static void init() {
        BestiaryClient.LOGGER.info(ENTRIES.size() + " entries found.");
        ACTIVE_PAGES.addAll(ENTRIES.values());
    }

    public static void filter(String searchTerm) {
        Map<Integer, BestiaryEntry> filtered = new HashMap<>();
        int i = 0;
        for (BestiaryEntry bestiaryEntry : ENTRIES.values()) {
            if (bestiaryEntry.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                filtered.put(i++, bestiaryEntry);
                continue;
            }
            if (bestiaryEntry.getDescription().toLowerCase().contains(searchTerm.toLowerCase())) {
                filtered.put(i++, bestiaryEntry);
            }
        }
        ACTIVE_PAGES.clear();
        ACTIVE_PAGES.addAll(filtered.values());
    }

    public static void reset() {
        ACTIVE_PAGES.clear();
        init();
    }

    public static List<BestiaryEntry> getActivePages() {
        return ACTIVE_PAGES;
    }
}
