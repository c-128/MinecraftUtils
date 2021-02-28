package com.mu.blocks;

import com.mu.main.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.ArrayList;

public class PowerNode {

    private Block b;
    private PowerNetwork network;

    public PowerNode() {

    }

    public void setBlock(Block b) {
        this.b = b;
    }

    public Block getBlock() {
        return this.b;
    }

    public void setNetwork(PowerNetwork network) {
        this.network = network;
    }

    public PowerNetwork getNetwork() {
        return this.network;
    }

    public ArrayList<PowerNode> attachesToOtherNode() {
        ArrayList<PowerNode> nodes = new ArrayList<>();

        if (Main.getPowerMan().getByLocation(this.b.getRelative(BlockFace.EAST).getLocation()) != null) {
            nodes.add(Main.getPowerMan().getByLocation(this.b.getRelative(BlockFace.EAST).getLocation()));
        }
        if (Main.getPowerMan().getByLocation(this.b.getRelative(BlockFace.NORTH).getLocation()) != null) {
            nodes.add(Main.getPowerMan().getByLocation(this.b.getRelative(BlockFace.NORTH).getLocation()));
        }
        if (Main.getPowerMan().getByLocation(this.b.getRelative(BlockFace.WEST).getLocation()) != null) {
            nodes.add(Main.getPowerMan().getByLocation(this.b.getRelative(BlockFace.WEST).getLocation()));
        }
        if (Main.getPowerMan().getByLocation(this.b.getRelative(BlockFace.SOUTH).getLocation()) != null) {
            nodes.add(Main.getPowerMan().getByLocation(this.b.getRelative(BlockFace.SOUTH).getLocation()));
        }
        if (Main.getPowerMan().getByLocation(this.b.getRelative(BlockFace.UP).getLocation()) != null) {
            nodes.add(Main.getPowerMan().getByLocation(this.b.getRelative(BlockFace.UP).getLocation()));
        }
        if (Main.getPowerMan().getByLocation(this.b.getRelative(BlockFace.DOWN).getLocation()) != null) {
            nodes.add(Main.getPowerMan().getByLocation(this.b.getRelative(BlockFace.DOWN).getLocation()));
        }
        return nodes;
    }
}
