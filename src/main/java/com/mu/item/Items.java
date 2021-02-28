package com.mu.item;

import com.mu.enchants.GlowEnchantment;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class Items {

    public static ItemStack BACK_PACK = Item.item(Material.BARREL, 1, ChatColor.GOLD + "Backpack");
    public static ItemStack ENDER_WINGS = Item.item(Material.ELYTRA, 1, ChatColor.DARK_PURPLE + "Ender Wings", true);
    public static ItemStack INFINITE_FOOD_ITEM = Item.item(Material.ENCHANTED_GOLDEN_APPLE, 1, ChatColor.GOLD + "Infinite Food");
    public static ItemStack QUARRY = null;
    public static ItemStack POWER_CABLE = null;

    public static void init() {
        Enchantment[] ENCH_INVIS = {GlowEnchantment.getGlow()};
        QUARRY = Item.item(Material.FURNACE, 1, ChatColor.GOLD + "Quarry", ENCH_INVIS);
        POWER_CABLE = Item.item(Material.REDSTONE_LAMP, 1, ChatColor.GOLD + "Power Cable", ENCH_INVIS);
    }
}
