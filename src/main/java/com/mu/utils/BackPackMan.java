package com.mu.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BackPackMan {

    public static void addBackPack(String uuid) throws IOException {
        File f = new File(Stats.WORKSPACE + "backpacks/packs.list");

        if (!f.exists()) {
            f.createNewFile();
        }

        FileWriter wr = new FileWriter()
    }
}
