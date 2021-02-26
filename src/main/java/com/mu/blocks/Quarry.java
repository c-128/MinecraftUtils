package com.mu.blocks;

import com.google.gson.JsonObject;
import com.mu.main.Main;
import com.mu.utils.Config;
import com.mu.utils.man.QuarryMan;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

public class Quarry {

    private Location miner;
    private Location l;
    private int r = 0;
    private int oldr;
    private boolean running;
    private boolean redstone;

    public Quarry() {

    }

    public Block getBlock() {
        return l.getWorld().getBlockAt(l);
    }

    public void setRedstoneEnabled(boolean redstone) {
        this.redstone = redstone;
    }

    public void toggleRedstoneEnabled() {
        this.redstone = !this.isRedstoneEnabled();
    }

    public boolean isRedstoneEnabled() {
        return this.redstone;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void toggleRunning() {
        this.running = !this.isRunning();
    }

    public boolean isRunning() {
        return this.running;
    }

    public void breakBlock() {
        Bukkit.getServer().getScheduler().runTask(Main.getPlugin(Main.class), () -> {
            int x = this.miner.getBlockX();
            int z = this.miner.getBlockZ();
            int y = this.miner.getBlockY();

            int wx = x + this.l.getBlockX();
            int wz = z + this.l.getBlockZ();

            Block cblock = getLocation().getWorld().getBlockAt(wx, y, wz);
            boolean breakble = true;
            for (String mat : Config.getConfig().getStringList("quarry.blacklist")) {
                if (Material.getMaterial(mat) == cblock.getType()) {
                    breakble = false;
                }
            }

            if (breakble) {
                Block storage = getLocation().getWorld().getBlockAt(getLocation().getBlockX(), getLocation().getBlockY() + 1, getLocation().getBlockZ());
                if (storage.getType() == Material.CHEST) {
                    Chest chest = (Chest) storage.getState();
                    chest.getBlockInventory().addItem(new ItemStack(cblock.getType()));
                } else {
                    Location droploc = new Location(storage.getLocation().getWorld(), storage.getX() + 0.5, getLocation().getBlockY() + 2, storage.getZ() + 0.5);

                    for (ItemStack i : cblock.getDrops()) {
                        breakBlock();
                        storage.getLocation().getWorld().dropItem(droploc, i);
                    }
                }
                cblock.setType(Material.AIR);
            }

            x--;
            if (x == (-this.getRadius() - 1)) {
                z--;
                x = this.getRadius();

                if (z == (-this.getRadius() - 1)) {
                    y--;
                    x = this.getRadius();
                    z = this.getRadius();
                }

                if (y == 0) {
                    QuarryMan.removeQuarry(this);
                }
            }
            setMiner(new Location(this.miner.getWorld(), x, y, z));
        });
    }

    public void setMiner(Location miner) {
        this.miner = miner;
    }

    public Location getMiner() {
        return this.miner;
    }

    public void setLocation(Location l) {
        this.l = l;
    }

    public Location getLocation() {
        return this.l;
    }

    public void setRadius(int r) {
        if (r == 0) {
            this.oldr = this.r;
        }
        this.r = r;
    }

    public int getRadius() {
        return this.r;
    }

    public String toJsonString() {
        JsonObject json = new JsonObject();

        JsonObject jl = new JsonObject();
        jl.addProperty("x", this.l.getBlockX());
        jl.addProperty("y", this.l.getBlockY());
        jl.addProperty("z", this.l.getBlockZ());
        jl.addProperty("world", this.l.getWorld().getName());
        json.add("location", jl);

        JsonObject ml = new JsonObject();
        ml.addProperty("x", this.miner.getBlockX());
        ml.addProperty("y", this.miner.getBlockY());
        ml.addProperty("z", this.miner.getBlockZ());
        ml.addProperty("world", this.miner.getWorld().getName());
        json.add("miner", ml);

        json.addProperty("radius", this.r);
        json.addProperty("running", this.running);
        json.addProperty("redstone-enabled", this.redstone);

        return json.toString();
    }
}
