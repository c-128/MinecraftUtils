package com.mu.recepies;

import com.mu.item.Items;
import com.mu.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

public class InfiniteFoodItemRecipe {

    public InfiniteFoodItemRecipe() {
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class), "infinte_food_item");
        ShapedRecipe re = new ShapedRecipe(key, Items.INFINITE_FOOD_ITEM);

        re.shape("GGG", "GAG", "GGG");
        re.setIngredient('G', Material.GOLD_BLOCK);
        re.setIngredient('A', Material.ENCHANTED_GOLDEN_APPLE);

        Bukkit.getServer().addRecipe(re);
    }
}
