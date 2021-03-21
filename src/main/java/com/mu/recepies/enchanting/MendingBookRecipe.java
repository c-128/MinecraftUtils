package com.mu.recepies.enchanting;

import com.mu.item.Item;
import com.mu.main.Main;
import com.mu.utils.Recipe;
import com.mu.utils.man.RecipeMan;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public class MendingBookRecipe {

    public MendingBookRecipe() {
        Recipe recipe = new Recipe();
        recipe.shape("B  ", "   ", "   ");
        recipe.setIngredient('B', Item.item(Material.BOOK, 5));
        recipe.setResult(Item.item(Material.ENCHANTED_BOOK, 1, new Enchantment[]{Enchantment.MENDING}));

        Main.getRecipeMan().addRecipe(recipe);
    }
}
