package com.mu.utils.man;

import com.mu.utils.Recipe;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class RecipeMan {

    private ArrayList<Recipe> recipes = new ArrayList<>();

    public RecipeMan() {
    }

    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
    }

    public void removeRecipe(Recipe recipe) {
        this.recipes.remove(recipe);
    }

    public ArrayList<Recipe> getRecipes() {
        return this.recipes;
    }
}
