package com.mu.listener;

import com.mu.item.Item;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SpawnerListener implements Listener {

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e) {
        try {
            Block b = e.getBlock();
            ItemStack i = e.getItemInHand();

            if (b.getType() == Material.SPAWNER) {
                CreatureSpawner cs = (CreatureSpawner) b.getState();
                EntityType et = EntityType.fromName(ChatColor.stripColor(i.getItemMeta().getLore().get(0)));
                cs.setSpawnedType(et);
                cs.update(true);
            }
        } catch (NullPointerException ex) {
        }
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent e) {
        Block b = e.getBlock();
        ItemStack i = e.getPlayer().getItemInHand();
        Location l = b.getLocation();
        World w = l.getWorld();

        if (b.getType() == Material.SPAWNER) {
            CreatureSpawner cs = (CreatureSpawner) b.getState();

            if (i.getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                ItemStack todorp = Item.item(Material.SPAWNER);
                ItemMeta meta = todorp.getItemMeta();

                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.GRAY + cs.getSpawnedType().name());
                meta.setLore(lore);
                todorp.setItemMeta(meta);

                w.dropItem(l, todorp);
            }
        }
    }
}
