package com.mu.recepies;

import com.mu.item.Item;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

public class BackPackRecepie {

    public BackPackRecepie() {
        ShapedRecipe re = new ShapedRecipe(Item.item(Material.PLAYER_HEAD, 1, ChatColor.BOLD + "" + ChatColor.GOLD + "Backpack"));
    }
}
