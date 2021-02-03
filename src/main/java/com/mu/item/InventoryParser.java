package com.mu.item;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryParser {

    public static String toString(Inventory inv, String title) {
        JsonObject json = new JsonObject();
        JsonArray conts = new JsonArray();

        for (int i = 0; i < inv.getSize(); i++) {
            JsonObject item = new JsonObject();
            ItemStack it = inv.getItem(i);

            if (it != null) {
                item.addProperty("item", ItemParser.toString(it));
                item.addProperty("slot", i);
                conts.add(item);
            }
        }
        json.add("cont", conts);

        json.addProperty("size", inv.getSize());
        json.addProperty("stack", inv.getMaxStackSize());
        json.addProperty("name", title);

        return json.toString();
    }

    public static Inventory toInv(String jsonstr) {
        JsonParser parser = new JsonParser();
        JsonObject json = (JsonObject) parser.parse(jsonstr);
        Inventory inv = Bukkit.createInventory(null, json.get("size").getAsInt(), json.get("name").getAsString());
        inv.setMaxStackSize(json.get("stack").getAsInt());

        JsonArray conts = json.getAsJsonArray("cont");
        for (JsonElement elm : conts) {
            JsonObject obj = (JsonObject) elm;
            ItemStack it = ItemParser.toItem(obj.get("item").getAsString());
            inv.setItem(obj.get("slot").getAsInt(), it);
        }

        return inv;
    }
}
