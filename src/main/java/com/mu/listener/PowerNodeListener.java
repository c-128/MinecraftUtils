package com.mu.listener;

import com.mu.blocks.PowerNetwork;
import com.mu.blocks.PowerNode;
import com.mu.item.Items;
import com.mu.main.Main;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
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
        }
    }
}
