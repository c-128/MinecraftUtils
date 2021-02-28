package com.mu.recepies;

import com.mu.item.Items;
import com.mu.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

public class PowerNodeRecipe {

    public PowerNodeRecipe() {
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class), "power_node");
        ShapedRecipe re = new ShapedRecipe(key, Items.POWER_CABLE);

        re.shape(" R ", "RIR", " R ");
        re.setIngredient('R', Material.REDSTONE);
        re.setIngredient('I', Material.IRON_INGOT);

        Bukkit.getServer().addRecipe(re);
    }
}
