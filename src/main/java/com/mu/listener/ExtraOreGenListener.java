package com.mu.listener;

import com.mu.main.Main;
import com.mu.utils.Config;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;

import java.util.Random;

public class ExtraOreGenListener implements Listener {

    @EventHandler
    public void onChunkGenerateEvent(ChunkPopulateEvent e) {
        try {
            World w = e.getWorld();
            Chunk c = e.getChunk();

            if (w.getEnvironment() == World.Environment.NORMAL) {
                if (Config.getConfig().getBoolean("oregen.extra-diamonds")) {
                    Random r = Main.R;

                    int x = (r.nextInt(15) + 1);
                    int z = (r.nextInt(15) + 1);
                    int y = r.nextInt(3) + 5;

                    if (c.getBlock(x, y, z).getType() == Material.STONE) {
                        c.getBlock(x, y, z).setType(Material.DIAMOND_ORE);
                        if (r.nextBoolean()) {
                            c.getBlock(x + 1, y, z).setType(Material.DIAMOND_ORE);
                            if (r.nextBoolean()) {
                                c.getBlock(x + 1, y, z + 1).setType(Material.DIAMOND_ORE);
                            }
                        }
                    }
                }
            } else if (w.getEnvironment() == World.Environment.NETHER) {
                if (Config.getConfig().getBoolean("oregen.extra-debris")) {
                    Random r = Main.R;

                    int x = (r.nextInt(15) + 1);
                    int z = (r.nextInt(15) + 1);
                    int y = r.nextInt(3) + 5;

                    if (c.getBlock(x, y, z).getType() == Material.STONE) {
                        c.getBlock(x, y, z).setType(Material.DIAMOND_ORE);
                    }
                }
            }
        } catch (IllegalArgumentException ex) {
        }
    }
}
