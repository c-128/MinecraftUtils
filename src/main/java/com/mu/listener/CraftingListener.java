package com.mu.listener;

import com.mu.item.Item;
import com.mu.main.Main;
import com.mu.utils.Recipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class CraftingListener implements Listener {

    @EventHandler
    public void onItemCraftEvent(InventoryClickEvent e) {
        if (e.getView().getTopInventory().getType() == InventoryType.WORKBENCH && e.getSlot() == 0) {
            Inventory inv = e.getView().getTopInventory();
            if (inv.getItem(0) == null) return;

            ItemStack result = inv.getItem(0);
            ArrayList<Recipe> recipes = Main.getRecipeMan().getRecipes();

            for (Recipe recipe : recipes) {
                if (result.isSimilar(recipe.getResult())) {
                    System.out.println("MATCHED!");
                    for (int i = 1; i < 9; i++) {
                        ItemStack craft = inv.getContents()[i].clone();
                        ItemStack rec = recipe.getMatrix()[i].clone();

                        if (craft == null) {
                            craft = Item.item(Material.AIR);
                        }
                        if (rec == null) {
                            rec = Item.item(Material.AIR);
                        }

                        /*int am = craft.getAmount() - rec.getAmount();
                        System.out.println("SLOT: " + i + ", NEW AMOUNT: " + am + " | CRAFT AMOUNT: " + craft.getAmount() + ", RECIPE AMOUNT: " + rec.getAmount());
                        craft.setAmount(am);

                        inv.setItem(i, craft);*/
                    }
                }
            }
        }
    }

    @EventHandler
    public void onItemPrepareCraftEvent(PrepareItemCraftEvent e) {
        ArrayList<Recipe> recipes = Main.getRecipeMan().getRecipes();
        System.out.println("RUN PREPARE ITEM CRAFT EVENT!");

        for (Recipe recipe : recipes) {
            int ok = 0;
            for (int i = 0; i < 9; i++) {
                ItemStack craft = null;
                if (e.getInventory().getMatrix()[i] != null) {
                    craft = e.getInventory().getMatrix()[i].clone();
                }
                ItemStack rec = recipe.getMatrix()[i].clone();

                if (craft == null) {
                    craft = Item.item(Material.AIR);
                }
                if (rec == null) {
                    rec = Item.item(Material.AIR);
                }

                if (craft.isSimilar(rec) && rec.getAmount() <= craft.getAmount()) {
                    ok++;
                } else {
                    if (rec.getType() == Material.AIR && craft.getType() == Material.AIR) {
                        ok++;
                    }
                }
            }

            if (ok == 9) {
                e.getInventory().setResult(recipe.getResult());
            }
        }
    }
}
