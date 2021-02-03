package com.mu.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {

    public static ItemStack item(Material m) {
        ItemStack i = new ItemStack(m);
        return i;
    }

    public static ItemStack item(Material m, int am) {
        ItemStack i = new ItemStack(m, am);
        return i;
    }

    public static ItemStack item(Material m, int am, String name) {
        ItemStack i = new ItemStack(m, am);
        ItemMeta me = i.getItemMeta();
        me.setDisplayName(name);
        i.setItemMeta(me);

        return i;
    }
}
