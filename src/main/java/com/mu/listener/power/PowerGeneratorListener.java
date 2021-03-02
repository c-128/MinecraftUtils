package com.mu.listener.power;

import com.mu.blocks.PowerGenerator;
import com.mu.blocks.PowerNode;
import com.mu.item.Items;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class PowerGeneratorListener implements Listener {

    @EventHandler
    public void onBlockInteractEvent(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block b = e.getClickedBlock();

            if (b.getType() == Material.FURNACE) {
                Furnace f = (Furnace) b.getState();

                if (f.getCustomName().equalsIgnoreCase(Items.POWER_GENERATOR.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent e) {
        Block b = e.getBlock();

        if (b.getType() == Material.FURNACE) {
            Furnace f = (Furnace) b.getState();

            if (f.getCustomName().equalsIgnoreCase(Items.POWER_GENERATOR.getItemMeta().getDisplayName())) {
                e.setDropItems(false);
                b.getLocation().getWorld().dropItem(b.getLocation(), Items.POWER_GENERATOR);

                PowerNode node = new PowerNode();
                node.setBlock(b);
                ArrayList<PowerNode> attached = node.attachesToOtherNode();
                for (PowerNode n : attached) {
                    n.getNetwork().removeGenerator(100);
                }
            }
        }
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e) {
        Block b = e.getBlock();
        Player p = e.getPlayer();
        ItemStack i = e.getItemInHand();

        if (i.isSimilar(Items.POWER_GENERATOR)) {
            PowerGenerator generator = new PowerGenerator();
            PowerNode node = new PowerNode();
            node.setBlock(b);

            ArrayList<PowerNode> attached = node.attachesToOtherNode();
            if (attached.size() == 0) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "Error! The generator must be placed against one power node!");
            } else if (attached.size() == 1) {
                generator.setNetwork(attached.get(0).getNetwork());
                generator.getNetwork().addGenerator(100);
            } else {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "Error! The generator must be placed against one power node!");
            }
        }
    }
}
