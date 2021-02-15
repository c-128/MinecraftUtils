package com.mu.recepies;

import com.mu.item.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

public class BackPackRecipe {

    public BackPackRecipe() {
        ShapedRecipe re = new ShapedRecipe(Items.BACK_PACK);

        re.shape("LLL", "LSL", "LLL");
        re.setIngredient('L', Material.LEATHER);
        re.setIngredient('S', Material.SHULKER_BOX);

        Bukkit.getServer().addRecipe(re);
    }
}
