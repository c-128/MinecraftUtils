package com.mu.recepies;

import com.mu.item.Items;
import com.mu.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

public class PowerGeneratorRecipe {

    public PowerGeneratorRecipe() {
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class), "power_generator");
        ShapedRecipe re = new ShapedRecipe(key, Items.POWER_GENERATOR);

        re.shape("SIS", "IFI", "SLS");
        re.setIngredient('S', Material.STONE);
        re.setIngredient('I', Material.IRON_BLOCK);
        re.setIngredient('F', Material.FURNACE);
        re.setIngredient('L', Material.LAVA_BUCKET);

        Bukkit.getServer().addRecipe(re);
    }
}
