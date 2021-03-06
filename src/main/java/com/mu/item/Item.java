package com.mu.item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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

    public static ItemStack item(Material m, int am, Enchantment[] ench) {
        ItemStack i = new ItemStack(m, am);
        ItemMeta me = i.getItemMeta();
        for (Enchantment e : ench) {
            me.addEnchant(e, 1, true);
        }
        i.setItemMeta(me);


        return i;
    }

    public static ItemStack item(Material m, int am, String name) {
        ItemStack i = new ItemStack(m, am);
        ItemMeta me = i.getItemMeta();
        me.setDisplayName(name);
        i.setItemMeta(me);

        return i;
    }

    public static ItemStack item(Material m, int am, String name, Enchantment en, int level, boolean b) {
        ItemStack i = new ItemStack(m, am);
        ItemMeta me = i.getItemMeta();
        me.addEnchant(en, level, b);
        me.setDisplayName(name);

        return i;
    }

    public static ItemStack item(Material m, int am, String name, boolean u) {
        ItemStack i = new ItemStack(m, am);
        ItemMeta me = i.getItemMeta();
        me.setDisplayName(name);
        me.setUnbreakable(u);
        i.setItemMeta(me);


        return i;
    }

    public static ItemStack item(Material m, int am, String name, Enchantment[] ench) {
        ItemStack i = new ItemStack(m, am);
        ItemMeta me = i.getItemMeta();
        me.setDisplayName(name);
        for (Enchantment e : ench) {
            me.addEnchant(e, 1, true);
        }
        i.setItemMeta(me);


        return i;
    }
}
