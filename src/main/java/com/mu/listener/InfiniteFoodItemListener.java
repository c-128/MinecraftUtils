package com.mu.listener;

import com.mu.item.Items;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class InfiniteFoodItemListener implements Listener {

    @EventHandler
    public void onItemConsumeEvent(PlayerItemConsumeEvent e) {
        if (e.getItem().isSimilar(Items.INFINITE_FOOD_ITEM)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent e) {
        Player p = (Player) e.getEntity();

        if (p.getInventory().contains(Items.INFINITE_FOOD_ITEM)) {
            e.setFoodLevel(20);
        }
    }
}
