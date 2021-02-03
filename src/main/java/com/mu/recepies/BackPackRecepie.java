package com.mu.recepies;

import com.mu.item.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

public class BackPackRecepie {

    public BackPackRecepie() {
        ShapedRecipe re = new ShapedRecipe(Item.item(Material.BARREL, 1, ChatColor.GOLD + "Backpack"));

        re.shape("LLL", "LSL", "LLL");
        re.setIngredient('L', Material.LEATHER);
        re.setIngredient('S', Material.SHULKER_BOX);

        Bukkit.getServer().addRecipe(re);
    }
}
