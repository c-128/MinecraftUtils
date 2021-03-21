package com.mu.utils;

import com.mu.item.Item;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class Recipe {

    private String[] shape = new String[3];
    private HashMap<Character, ItemStack> ingredients = new HashMap<>();
    private ItemStack result;

    public Recipe() {
    }

    public void shape(String... shape) {
        this.shape = shape;
        System.out.println(this.shape[0]);
    }

    public ItemStack[] getMatrix() {
        ItemStack[] matrix = new ItemStack[9];
        int i = 0;
        for (String row : this.shape) {
            for (char key : row.toCharArray()) {
                if (key == ' ') {
                    matrix[i] = Item.item(Material.AIR);
                } else {
                    ItemStack item = this.ingredients.get(key);
                    if (item == null) {
                        matrix[i] = Item.item(Material.AIR);
                    } else {
                        matrix[i] = item;
                    }
                }
                i++;
            }
        }

        return matrix;
    }

    public void setIngredient(char key, ItemStack item) {
        this.ingredients.put(key, item);
    }

    public ItemStack getIngredient(char key) {
        return this.ingredients.get(key);
    }

    public HashMap<Character, ItemStack> getIngredients() {
        return this.ingredients;
    }

    public void setResult(ItemStack result) {
        this.result = result;
    }

    public ItemStack getResult() {
        return this.result;
    }
}
