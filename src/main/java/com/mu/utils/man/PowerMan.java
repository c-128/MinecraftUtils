package com.mu.utils.man;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mu.blocks.PowerNetwork;
import com.mu.blocks.PowerNode;
import com.mu.main.Main;
import com.mu.utils.Stats;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PowerMan {

    private static Thread t;

    public static void init() throws FileNotFoundException {
        File dir = new File(Stats.WORKSPACE + "/power/");
        dir.mkdirs();
        String[] contents = dir.list();

        for (String fi : contents) {
            File f = new File(Stats.WORKSPACE + "/power/" + fi);

            Scanner sc = new Scanner(f);
            String data = "";
            while (sc.hasNextLine()) {
                data = data + sc.nextLine() + "\r\n";
            }
            sc.close();

            PowerNetwork network = new PowerNetwork();
            JsonObject json = new JsonParser().parse(data).getAsJsonObject();
            network.setPowerLevel(json.get("powerlevel").getAsLong());
            network.setUUID(json.get("uuid").getAsString());
            network.setPowerGeneratoin(json.get("gen").getAsLong());
            network.setPowerConsumtion(json.get("cons").getAsLong());

            JsonArray nodes = json.get("nodes").getAsJsonArray();
            for (int i = 0; i < nodes.size(); i++) {
                JsonObject obj = nodes.get(i).getAsJsonObject();
                PowerNode node = new PowerNode();
                World w = Bukkit.getWorld(obj.get("world").getAsString());
                node.setBlock(w.getBlockAt(obj.get("x").getAsInt(), obj.get("y").getAsInt(), obj.get("z").getAsInt()));
                node.setNetwork(network);

                Main.getPowerMan().addNode(node);
                network.addNode(node);
            }
        }

        t = new Thread(() -> {
            while (true) {
                try {
                    for (PowerNetwork network : Main.getPowerMan().getNetworks()) {
                        network.caculatePower();
                    }
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    public static void save() throws IOException {
        t.stop();
        ArrayList<PowerNetwork> networks = Main.getPowerMan().getNetworks();

        for (PowerNetwork network : networks) {
            File f = new File(Stats.WORKSPACE + "/power/" + network.getUUID() + ".json");
            if (!f.exists()) {
                f.createNewFile();
            }

            FileWriter wr = new FileWriter(f);
            wr.write(network.toJson().toString());
            wr.close();
        }
    }
}
