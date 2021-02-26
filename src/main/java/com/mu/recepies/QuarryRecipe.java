package com.mu.recepies;

import com.mu.item.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

public class QuarryRecipe {

    public QuarryRecipe() {
        ShapedRecipe r = new ShapedRecipe(Items.QUARRY);

        r.shape(" D ", "DPD", " D ");
        r.setIngredient('P', Material.DIAMOND_PICKAXE);
        r.setIngredient('D', Material.DIAMOND);

        Bukkit.getServer().addRecipe(r);
    }
}
