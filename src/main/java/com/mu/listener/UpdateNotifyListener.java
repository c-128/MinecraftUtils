package com.mu.listener;

import com.mu.utils.UpdateMan;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateNotifyListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if (p.isOp()) {
            if (!UpdateMan.NEWEST) {
                p.sendMessage(ChatColor.GREEN + "A new version of MinecraftUtils is available! Please download it!");
            }
        }
    }
}
