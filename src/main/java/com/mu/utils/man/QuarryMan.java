package com.mu.utils.man;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mu.blocks.Quarry;
import com.mu.utils.Config;
import com.mu.utils.Stats;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class QuarryMan {

    private static File f = new File(Stats.WORKSPACE + "/quarry/quarries.ls");
    private static ArrayList<Quarry> QUARRIES = new ArrayList<>();
    private static Thread t;

    public static void init() throws IOException {
        new File(Stats.WORKSPACE + "/quarry/").mkdirs();
        if (!f.exists()) {
            f.createNewFile();

            FileWriter wr = new FileWriter(f);
            wr.write("[]");
            wr.close();
        }

        String jsonstr = "";
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            jsonstr = jsonstr + s.nextLine() + "\r\n";
        }
        s.close();

        JsonArray json = (JsonArray) new JsonParser().parse(jsonstr);

        for (int i = 0; i < json.size(); i++) {
            JsonObject jsonobj = (JsonObject) new JsonParser().parse(json.get(i).getAsString());
            Quarry quarry = new Quarry();

            JsonObject jsonloc = jsonobj.getAsJsonObject("location");
            World w = Bukkit.getWorld(jsonloc.get("world").getAsString());
            int x = jsonloc.get("x").getAsInt();
            int y = jsonloc.get("y").getAsInt();
            int z = jsonloc.get("z").getAsInt();
            Location l = new Location(w, x, y, z);

            JsonObject jsonminer = jsonobj.getAsJsonObject("miner");
            World mw = Bukkit.getWorld(jsonminer.get("world").getAsString());
            int mx = jsonminer.get("x").getAsInt();
            int my = jsonminer.get("y").getAsInt();
            int mz = jsonminer.get("z").getAsInt();
            Location ml = new Location(mw, mx, my, mz);

            quarry.setMiner(ml);
            quarry.setLocation(l);
            quarry.setRadius(jsonobj.get("radius").getAsInt());
            quarry.setRunning(jsonobj.get("running").getAsBoolean());
            quarry.setRedstoneEnabled(jsonobj.get("redstone-enabled").getAsBoolean());

            QUARRIES.add(quarry);
        }

        t = new Thread(() -> {
            while (true) {
                try {
                    for (Quarry q : QUARRIES) {
                        if (q.isRunning() || (q.isRedstoneEnabled() && (q.getBlock().isBlockIndirectlyPowered() || q.getBlock().isBlockIndirectlyPowered()))) {
                            q.breakBlock();
                        }
                    }

                    TimeUnit.SECONDS.sleep(Config.getConfig().getInt("quarry.speed"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    public static void addQuarry(Quarry q) {
        QUARRIES.add(q);
    }

    public static void removeQuarry(Quarry q) {
        QUARRIES.remove(q);
    }

    public static ArrayList<Quarry> getQuarries() {
        return QUARRIES;
    }

    public static void save() throws IOException {
        JsonArray array = new JsonArray();

        for (Quarry q : QUARRIES) {
            array.add(q.toJsonString());
        }

        FileWriter wr = new FileWriter(f);
        wr.write(array.toString());
        wr.close();

        t.stop();
    }
}
