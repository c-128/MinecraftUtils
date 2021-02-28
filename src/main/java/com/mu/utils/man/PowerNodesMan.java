package com.mu.utils.man;

import com.mu.blocks.PowerNetwork;
import com.mu.blocks.PowerNode;
import org.bukkit.Location;

import java.util.ArrayList;

public class PowerNodesMan {

    private ArrayList<PowerNode> nodes = new ArrayList<>();

    public PowerNodesMan() {

    }

    public void addNode(PowerNode node) {
        this.nodes.add(node);
    }

    public void removeNode(PowerNode node) {
        this.nodes.remove(node);
    }

    public ArrayList<PowerNode> getNodes() {
        return this.nodes;
    }

    public PowerNode getByLocation(Location l) {
        for (PowerNode n : this.nodes) {
            Location loc = n.getBlock().getLocation();
            if (loc.getBlockX() == l.getBlockX()) {
                if (loc.getBlockY() == l.getBlockY()) {
                    if (loc.getBlockZ() == l.getBlockZ()) {
                        return n;
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<PowerNetwork> getNetworks() {
        ArrayList<PowerNetwork> networks = new ArrayList<>();

        for (PowerNode node : this.nodes) {
            if (!networks.contains(node.getNetwork())) {
                networks.add(node.getNetwork());
            }
        }

        return networks;
    }
}
