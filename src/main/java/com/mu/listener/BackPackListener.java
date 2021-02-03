package com.mu.listener;

import com.mu.item.Items;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BackPackListener implements Listener {

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlock();
        ItemStack i = e.getItemInHand();
        String name = i.getItemMeta().getDisplayName();

        if (i.isSimilar(Items.BACK_PACK)) {
            e.setCancelled(true);
            String uuid = i.getItemMeta().getLore().get(0);
        }
    }

    @EventHandler
    public void onItemCraftingEvent(CraftItemEvent e) {
        ItemStack re = e.getRecipe().getResult();

        if (re.isSimilar(Items.BACK_PACK)) {
            ItemStack i = Items.BACK_PACK;
            ItemMeta m = i.getItemMeta();

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + UUID.randomUUID().toString());
            m.setLore(lore);
            i.setItemMeta(m);

            e.getInventory().setResult(i);
        }
    }
}
