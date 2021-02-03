package com.mu.listener;

import com.mu.item.Items;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BackPackListener implements Listener {

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlock();
        ItemStack i = e.getItemInHand();
        String name = i.getItemMeta().getDisplayName();

        if (i.isSimilar(Items.BACK_PACK)) {
            e.setCancelled(true);
        }
    }
}
