package com.mu.ui.quarry;

import com.mu.blocks.Quarry;
import com.mu.item.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MainQuarryUI {

    public static void open(Quarry q, Player p) {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Quarry");

        inv.setItem(0, Item.item(Material.GREEN_WOOL, 1, ChatColor.GREEN + "+1 Radius"));
        inv.setItem(1, Item.item(Material.DIAMOND_PICKAXE, 1, "Radius: " + q.getRadius()));
        inv.setItem(2, Item.item(Material.RED_WOOL, 1, ChatColor.RED + "-1 Radius"));
        inv.setItem(8, Item.item(Material.FURNACE, 1, q.getLocation().getBlockX() + ":" + q.getLocation().getBlockY() + ":" + q.getLocation().getBlockZ() + ":" + q.getLocation().getWorld().getName()));

        if (q.isRedstoneEnabled()) {
            inv.setItem(52, Item.item(Material.REDSTONE, 1, ChatColor.RED + "Disable Redstone"));
        } else {
            inv.setItem(52, Item.item(Material.GUNPOWDER, 1, ChatColor.GREEN + "Enable Redstone"));
        }

        if (q.isRunning()) {
            inv.setItem(53, Item.item(Material.RED_WOOL, 1, ChatColor.RED + "Stop"));
        } else {
            inv.setItem(53, Item.item(Material.GREEN_WOOL, 1, ChatColor.GREEN + "Start"));
        }

        p.openInventory(inv);
    }
}
