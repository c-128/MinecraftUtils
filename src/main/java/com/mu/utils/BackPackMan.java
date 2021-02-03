package com.mu.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mu.item.BackPackParser;
import com.mu.item.InventoryParser;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BackPackMan {

    public static ArrayList<Material> BLACK_LIST = new ArrayList<>();

    public static void addBackPack(String uuid) {
        try {
            String str = BackPackParser.toString(uuid);
            File bf = new File(Stats.WORKSPACE + "backpacks/" + uuid + ".bkp");
            if (!bf.exists()) {
                bf.createNewFile();
            }
            FileWriter wr = new FileWriter(bf);
            wr.write(str);
            wr.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void saveBackPack(String uuid, Inventory inv) {
        try {
            String str = BackPackParser.toString(uuid, inv);
            File bf = new File(Stats.WORKSPACE + "backpacks/" + uuid + ".bkp");
            if (!bf.exists()) {
                bf.createNewFile();
            }
            FileWriter wr = new FileWriter(bf);
            wr.write(str);
            wr.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Inventory getBackPack(String uuid) {
        try {
            File f = new File(Stats.WORKSPACE + "backpacks/" + uuid + ".bkp");
            if (!f.exists()) {
                f.createNewFile();
            }

            String str = "";
            Scanner scr = new Scanner(f);
            while (scr.hasNextLine()) {
                str += scr.nextLine();
            }
            scr.close();

            JsonParser parser = new JsonParser();
            JsonObject json = (JsonObject) parser.parse(str);

            Inventory inv = InventoryParser.toInv(json.get("cont").getAsString());

            return inv;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
