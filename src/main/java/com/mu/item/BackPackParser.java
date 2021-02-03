package com.mu.item;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BackPackParser {

    public static String toString(String uuid) {
        JsonObject json = new JsonObject();

        json.addProperty("uuid", uuid);
        json.addProperty("cont", InventoryParser.toString(Bukkit.createInventory(null, 27), ChatColor.GOLD + "Backpack"));

        return json.toString();
    }

    public static String toString(ItemStack i) {
        JsonObject json = new JsonObject();

        json.addProperty("uuid", i.getItemMeta().getLore().get(0));
        json.addProperty("cont", InventoryParser.toString(Bukkit.createInventory(null, 27), ChatColor.GOLD + "Backpack"));

        return json.toString();
    }

    public static String toString(String uuid, Inventory inv) {
        JsonObject json = new JsonObject();

        json.addProperty("uuid", uuid);
        json.addProperty("cont", InventoryParser.toString(inv, ChatColor.GOLD + "Backpack"));

        return json.toString();
    }

    public static String toString(ItemStack i, Inventory inv) {
        JsonObject json = new JsonObject();

        json.addProperty("uuid", i.getItemMeta().getLore().get(0));
        json.addProperty("cont", InventoryParser.toString(inv, ChatColor.GOLD + "Backpack"));

        return json.toString();
    }

    public static ItemStack toBackPack(String jsonstr) {
        JsonParser parser = new JsonParser();
        JsonObject json = (JsonObject) parser.parse(jsonstr);

        ItemStack i = Items.BACK_PACK;
        ItemMeta m = i.getItemMeta();

        List<String> lore = new ArrayList<>();
        lore.add(json.get("uuid").getAsString());
        m.setLore(lore);
        i.setItemMeta(m);

        return i;
    }
}
