package com.mu.utils;

import com.mu.main.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    private static File f;
    private static FileConfiguration cf;

    public static void init() throws IOException {
        f = new File(Main.getPlugin(Main.class).getDataFolder(), "config.yml");

        if (!f.exists()) {
            f.createNewFile();
        }

        cf = YamlConfiguration.loadConfiguration(f);
    }

    public static FileConfiguration getConfig() {
        return cf;
    }

    public static void save() throws IOException {
        cf.save(f);
    }
}
