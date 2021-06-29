package com.cbw.fideltour.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArcMenuDummyContent {

    public static class DummyItem {

        public String id;
        public String content;

        public DummyItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }

    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        addItem(new DummyItem("1", "Alimentation Tunisienne"));
        addItem(new DummyItem("2", "Cafés"));
        addItem(new DummyItem("3", "Restaurants"));
        addItem(new DummyItem("4", "Musées"));
        addItem(new DummyItem("5", "Patrimoines tunisiens"));
        addItem(new DummyItem("6", "Traditions tunisiennes"));
        addItem(new DummyItem("7", "Théatres"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
}
