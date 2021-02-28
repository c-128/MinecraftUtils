package com.mu.blocks;

import java.util.ArrayList;

public class PowerNetwork {

    private ArrayList<PowerNode> nodes = new ArrayList<>();

    public PowerNetwork() {

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

    public PowerNetwork merchNetworks(PowerNetwork... net) {
        PowerNetwork done = new PowerNetwork();
        for (PowerNetwork n : net) {
            for (PowerNode node : n.getNodes()) {
                done.addNode(node);
            }
        }

        return done;
    }
}
