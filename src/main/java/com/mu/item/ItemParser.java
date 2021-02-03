package com.mu.item;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemParser {

    public static String toString(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        JsonObject json = new JsonObject();
        json.addProperty("material", item.getType().name());
        json.addProperty("amount", item.getAmount());
        json.addProperty("name", meta.getDisplayName());
        json.addProperty("durability", item.getDurability());

        JsonArray enchantments = new JsonArray();
        for (Enchantment en : meta.getEnchants().keySet()) {
            enchantments.add(en.getName() + ":" + meta.getEnchants().get(en));
        }
        json.add("enchants", enchantments);

        if (meta.getLore() != null) {
            JsonArray lore = new JsonArray();
            for (String l : meta.getLore()) {
                lore.add(l);
            }
            json.add("lore", lore);
        } else {
            json.add("lore", null);
        }

        return json.toString();
    }

    public static ItemStack toItem(String jsonstr) {
        JsonParser parser = new JsonParser();
        JsonObject json = (JsonObject) parser.parse(jsonstr);

        ItemStack item = new ItemStack(Material.matchMaterial(json.get(("material")).getAsString()));
        item.setDurability(json.get("durability").getAsShort());
        item.setAmount(json.get("amount").getAsInt());
        ItemMeta meta = item.getItemMeta();
        if (!json.get("name").equals("")) {
            meta.setDisplayName(json.get("name").getAsString());
        }

        JsonArray enchants = json.getAsJsonArray("enchants");
        for (int i = 0; i < enchants.size(); i++) {
            String en = enchants.get(i).getAsString();
            int level = Integer.parseInt(en.split(":")[1]);
            Enchantment enc = Enchantment.getByName(en.split(":")[0]);
            meta.addEnchant(enc, level, true);
        }

        if (!json.get("lore").isJsonNull()) {
            System.out.println("lol");
            JsonArray lore = json.getAsJsonArray("lore");
            if (lore != null) {
                ArrayList<String> itemlore = new ArrayList<>();
                for (int i = 0; i < lore.size(); i++) {
                    itemlore.add(lore.get(i).getAsString());
                }
                meta.setLore(itemlore);
            }
        }

        item.setItemMeta(meta);
        return item;
    }
}
