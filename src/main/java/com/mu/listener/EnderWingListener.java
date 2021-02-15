package com.mu.listener;

import com.mu.item.Items;
import com.mu.main.Main;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class EnderWingListener implements Listener {
    List<String> cooldown = new ArrayList<String>();

    @EventHandler
    public void onFly(PlayerToggleFlightEvent e) {
        if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
            Player p = e.getPlayer();
            if (cooldown.contains(p.getUniqueId().toString())) return;
            if (p.getInventory().getChestplate().equals(Items.ENDER_WINGS)) {
                p.setVelocity(p.getLocation().getDirection().multiply(1).setY(1));
                cooldown.add(e.getPlayer().getUniqueId().toString());

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        cooldown.remove(e.getPlayer().getUniqueId().toString());
                    }
                }.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 10, 0);
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().getInventory().addItem(Items.ENDER_WINGS);
        e.getPlayer().setAllowFlight(true);
    }
}
