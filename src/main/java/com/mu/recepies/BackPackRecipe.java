package com.mu.recepies;

import com.mu.item.Items;
import com.mu.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

public class BackPackRecipe {

    public BackPackRecipe() {
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class), "backpack");
        ShapedRecipe re = new ShapedRecipe(key, Items.BACK_PACK);

        re.shape("LLL", "LSL", "LLL");
        re.setIngredient('L', Material.LEATHER);
        re.setIngredient('S', Material.SHULKER_BOX);

        Bukkit.getServer().addRecipe(re);
    }
}
