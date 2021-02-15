package com.mu.recepies;

import com.mu.item.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

public class EnchantedGoldenAppleRecipe {

    public EnchantedGoldenAppleRecipe() {
        ShapedRecipe re = new ShapedRecipe(Items.INFINITE_FOOD_ITEM);

        re.shape("GGG", "GAG", "GGG");
        re.setIngredient('G', Material.GOLD_BLOCK);
        re.setIngredient('A', Material.APPLE);

        Bukkit.getServer().addRecipe(re);
    }
}
