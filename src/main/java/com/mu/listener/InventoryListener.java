package com.mu.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase("TITLE")) {
            e.setCancelled(true);
        }
    }
}
