package com.mu.recepies;

import com.mu.item.Items;
import com.mu.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

public class QuarryRecipe {

    public QuarryRecipe() {
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class), "quarry");
        ShapedRecipe r = new ShapedRecipe(key, Items.QUARRY);

        r.shape(" D ", "DPD", " D ");
        r.setIngredient('P', Material.DIAMOND_PICKAXE);
        r.setIngredient('D', Material.DIAMOND);

        Bukkit.getServer().addRecipe(r);
    }
}
