package com.mu.listener;

import com.mu.blocks.PowerNetwork;
import com.mu.blocks.PowerNode;
import com.mu.item.Items;
import com.mu.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class PowerNodeListener implements Listener {

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e) {
        Block b = e.getBlock();
        ItemStack i = e.getItemInHand();

        if (i.isSimilar(Items.POWER_CABLE)) {
            PowerNode node = new PowerNode();
            node.setBlock(b);
            Main.getPowerMan().addNode(node);

            ArrayList<PowerNode> attached = node.attachesToOtherNode();
            if (attached.size() == 0) {
                node.setNetwork(new PowerNetwork());
            } else if (attached.size() == 1) {
                node.setNetwork(attached.get(0).getNetwork());
            } else {
                ArrayList<PowerNetwork> merched = new ArrayList<>();
                for (PowerNode n : attached) {
                    if (!merched.contains(n.getNetwork())) {
                        merched.add(n.getNetwork());
                    }
                }

                PowerNetwork done = merched.get(0).merchNetworks(merched.toArray(new PowerNetwork[merched.size()]));
                node.setNetwork(done);
            }
            node.getNetwork().addNode(node);

            System.out.println("Nodes in network: " + node.getNetwork().getNodes().toString());
            System.out.println("All nodes: " + Main.getPowerMan().getNodes().toString());
            System.out.println("Network: " + node.getNetwork().toString());
            System.out.println("--------------------------------------------------------------");

            node.getNetwork().setPowerLevel(node.getNetwork().getNodes().size() * 100);
        }
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent e) {
        Block b = e.getBlock();

        if (b.getType() == Material.REDSTONE_LAMP) {
            PowerNode node = Main.getPowerMan().getByLocation(b.getLocation());

            if (node != null) {
                Main.getPowerMan().removeNode(node);
                e.setDropItems(false);
                b.getLocation().getWorld().dropItem(b.getLocation(), Items.POWER_CABLE);
            }
        }
    }

    @EventHandler
    public void onBlockInteractEvent(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block b = e.getClickedBlock();
            Player p = e.getPlayer();
            if (p.getItemInHand().getType() == Material.STICK) {
                PowerNode node = Main.getPowerMan().getByLocation(b.getLocation());

                if (node == null) {
                    p.sendMessage(ChatColor.RED + "Please right click on a Power Cable");
                } else {
                    p.sendMessage(ChatColor.GREEN + "----------------------------------------------");
                    p.sendMessage(ChatColor.YELLOW + "X: " + b.getX() + ", Y: " + b.getY() + ", Z: " + b.getZ());
                    p.sendMessage(ChatColor.YELLOW + "Power Level: " + node.getNetwork().getPowerLevel());
                    p.sendMessage(ChatColor.YELLOW + "Power Network UUID: " + node.getNetwork().getUUID());
                    p.sendMessage(ChatColor.GREEN + "----------------------------------------------");
                }
            }
        }
    }
}
