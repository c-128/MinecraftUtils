package com.mu.listener;

import com.mu.blocks.Quarry;
import com.mu.item.Item;
import com.mu.item.Items;
import com.mu.ui.quarry.MainQuarryUI;
import com.mu.utils.man.QuarryMan;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ConcurrentModificationException;

public class QuarryListener implements Listener {

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e) {

        try {
            String title = e.getView().getTitle();

            if (title.equalsIgnoreCase(ChatColor.GOLD + "Quarry")) {
                e.setCancelled(true);
                ItemStack i = e.getCurrentItem();
                Inventory inv = e.getClickedInventory();
                String name = i.getItemMeta().getDisplayName();
                Quarry q = null;

                String[] args = inv.getItem(8).getItemMeta().getDisplayName().split(":");
                for (Quarry qu : QuarryMan.getQuarries()) {
                    if (qu.getLocation().getBlockX() == Integer.parseInt(args[0])) {
                        if (qu.getLocation().getBlockY() == Integer.parseInt(args[1])) {
                            if (qu.getLocation().getBlockZ() == Integer.parseInt(args[2])) {
                                if (qu.getLocation().getWorld().getName().equalsIgnoreCase(args[3])) {
                                    q = qu;
                                }
                            }
                        }
                    }
                }

                Location ql = q.getLocation();

                if (name.equalsIgnoreCase(ChatColor.GREEN + "+1 Radius")) {
                    if (q.getRadius() != 100) {
                        q.setRadius(q.getRadius() + 1);
                        q.setMiner(new Location(ql.getWorld(), q.getRadius(), ql.getBlockY() - 1, q.getRadius()));
                        inv.setItem(1, Item.item(Material.DIAMOND_PICKAXE, 1, "Radius: " + q.getRadius()));
                    }
                } else if (name.equalsIgnoreCase(ChatColor.RED + "-1 Radius")) {
                    if (q.getRadius() != 1) {
                        q.setRadius(q.getRadius() - 1);
                        q.setMiner(new Location(ql.getWorld(), q.getRadius(), ql.getBlockY() - 1, q.getRadius()));
                        inv.setItem(1, Item.item(Material.DIAMOND_PICKAXE, 1, "Radius: " + q.getRadius()));
                    }
                } else if (name.equalsIgnoreCase(ChatColor.RED + "Stop")) {
                    q.toggleRunning();
                    if (q.isRunning()) {
                        inv.setItem(53, Item.item(Material.RED_WOOL, 1, ChatColor.RED + "Stop"));
                    } else {
                        inv.setItem(53, Item.item(Material.GREEN_WOOL, 1, ChatColor.GREEN + "Start"));
                    }
                } else if (name.equalsIgnoreCase(ChatColor.GREEN + "Start")) {
                    q.toggleRunning();
                    if (q.isRunning()) {
                        inv.setItem(53, Item.item(Material.RED_WOOL, 1, ChatColor.RED + "Stop"));
                    } else {
                        inv.setItem(53, Item.item(Material.GREEN_WOOL, 1, ChatColor.GREEN + "Start"));
                    }
                } else if (name.equalsIgnoreCase(ChatColor.GREEN + "Enable Redstone")) {
                    q.toggleRedstoneEnabled();
                    if (q.isRedstoneEnabled()) {
                        inv.setItem(52, Item.item(Material.REDSTONE, 1, ChatColor.RED + "Disable Redstone"));
                    } else {
                        inv.setItem(52, Item.item(Material.GUNPOWDER, 1, ChatColor.GREEN + "Enable Redstone"));
                    }
                } else if (name.equalsIgnoreCase(ChatColor.RED + "Disable Redstone")) {
                    q.toggleRedstoneEnabled();
                    if (q.isRedstoneEnabled()) {
                        inv.setItem(52, Item.item(Material.REDSTONE, 1, ChatColor.RED + "Disable Redstone"));
                    } else {
                        inv.setItem(52, Item.item(Material.GUNPOWDER, 1, ChatColor.GREEN + "Enable Redstone"));
                    }
                }
            }
        } catch (NullPointerException ex) {
        }
    }

    @EventHandler
    public void onPlayerBlockInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (!p.isSneaking()) {
            if (e.hasBlock() && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Block b = e.getClickedBlock();
                Location l = b.getLocation();

                for (Quarry q : QuarryMan.getQuarries()) {
                    if (l.getBlockX() == q.getLocation().getBlockX()) {
                        if (l.getBlockY() == q.getLocation().getBlockY()) {
                            if (l.getBlockZ() == q.getLocation().getBlockZ()) {
                                if (l.getWorld().getName().equalsIgnoreCase(q.getLocation().getWorld().getName())) {
                                    e.setCancelled(true);
                                    MainQuarryUI.open(q, p);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e) {
        ItemStack i = e.getItemInHand();
        Block b = e.getBlock();
        Location l = b.getLocation();

        if (i.isSimilar(Items.QUARRY)) {
            Quarry q = new Quarry();
            q.setRadius(10);
            q.setLocation(b.getLocation());
            q.setMiner(new Location(l.getWorld(), q.getRadius(), l.getBlockY() - 1, q.getRadius()));
            q.setRunning(false);
            q.setRedstoneEnabled(true);

            QuarryMan.addQuarry(q);
        }
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent e) {
        try {
            Block b = e.getBlock();
            Location l = e.getBlock().getLocation();

            for (Quarry q : QuarryMan.getQuarries()) {
                if (l.getBlockX() == q.getLocation().getBlockX()) {
                    if (l.getBlockY() == q.getLocation().getBlockY()) {
                        if (l.getBlockZ() == q.getLocation().getBlockZ()) {
                            QuarryMan.removeQuarry(q);
                            e.setCancelled(true);
                            b.setType(Material.AIR);
                            l.getWorld().dropItem(l, Items.QUARRY);
                        }
                    }
                }
            }
        } catch (ConcurrentModificationException ex) {
        }
    }
}
