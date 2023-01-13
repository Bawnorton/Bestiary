package com.bawnorton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BestiaryContent {
    private static final List<Entry> ACTIVE_PAGES = new ArrayList<>();

    public static void init() {
        ACTIVE_PAGES.addAll(EntityDirectory.getEntryList());
    }

    public static void filter(String searchTerm) {
        List<Entry> entries = EntityDirectory.getEntryList();
        Map<Integer, Entry> filtered = new HashMap<>();
        int i = 0;
        for(Entry entry: entries) {
            if (entry.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                filtered.put(i++, entry);
                continue;
            }
            if(entry.getDescription().toLowerCase().contains(searchTerm.toLowerCase())) {
                filtered.put(i++, entry);
            }
        }
        ACTIVE_PAGES.clear();
        ACTIVE_PAGES.addAll(filtered.values());
    }

    public static void reset() {
        ACTIVE_PAGES.clear();
        init();
    }

    public static List<Entry> getActivePages() {
        return ACTIVE_PAGES;
    }
}
