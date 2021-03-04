package com.mu.listener;

import com.mu.utils.Config;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class VillagerZombifyListener implements Listener {

    @EventHandler
    public void onVillagerZombifyEvent(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType() == EntityType.ZOMBIE_VILLAGER) {
            Entity entity = e.getRightClicked();
            Player p = e.getPlayer();
            if (p.getItemInHand().getType() == Material.GOLDEN_APPLE) {
                ZombieVillager v = (ZombieVillager) entity;
                v.setConversionTime(Config.getConfig().getInt("villager.time"));
            }
        }
    }
}
