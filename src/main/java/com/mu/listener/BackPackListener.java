package com.mu.listener;

import com.mu.item.Items;
import com.mu.utils.man.BackPackMan;
import com.mu.utils.Stats;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BackPackListener implements Listener {

    @EventHandler
    public void onBlockPlaceEvent(PlayerInteractEvent e) {
        try {
            Player p = e.getPlayer();
            ItemStack i = e.getItem();
            String name = i.getItemMeta().getDisplayName();

            if (name.equalsIgnoreCase(ChatColor.GOLD + "Backpack")) {
                e.setCancelled(true);
                String uuid = i.getItemMeta().getLore().get(0);
                Stats.BACKPACKS.put(p.getName(), uuid);
                p.openInventory(BackPackMan.getBackPack(uuid));
            }
        } catch (NullPointerException ex) {
        }
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e) {
        try {
            String title = e.getView().getTitle();
            Material m = e.getCurrentItem().getType();
            String it = e.getCurrentItem().getItemMeta().getDisplayName();

            if (title.equalsIgnoreCase(ChatColor.GOLD + "Backpack")) {
                if (BackPackMan.BLACK_LIST.contains(m)) {
                    e.setCancelled(true);
                } else if (it.equalsIgnoreCase(ChatColor.GOLD + "Backpack")) {
                    e.setCancelled(true);
                }
            }
        } catch (NullPointerException ex) {
        }
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent e) {
        String title = e.getView().getTitle();
        Player p = (Player) e.getPlayer();

        if (title.equalsIgnoreCase(ChatColor.GOLD + "Backpack")) {
            BackPackMan.saveBackPack(Stats.BACKPACKS.get(p.getName()), e.getInventory());
            Stats.BACKPACKS.put(p.getName(), null);
        }
    }

    @EventHandler
    public void onItemCraftingEvent(CraftItemEvent e) {
        ItemStack re = e.getRecipe().getResult();

        if (re.isSimilar(Items.BACK_PACK)) {
            ItemStack i = Items.BACK_PACK;
            ItemMeta m = i.getItemMeta();

            List<String> lore = new ArrayList<>();
            String uuid = UUID.randomUUID().toString();

            lore.add(uuid);
            m.setLore(lore);
            i.setItemMeta(m);

            BackPackMan.addBackPack(uuid);
            e.getInventory().setResult(i);
        }
    }
}
