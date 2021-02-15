package com.mu.main;

import com.mu.listener.BackPackListener;
import com.mu.listener.EnderWingListener;
import com.mu.listener.SpawnerListener;
import com.mu.recepies.BackPackRecepie;
import com.mu.utils.Config;
import com.mu.utils.FilesMan;
import com.mu.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            FilesMan.setup();

            saveDefaultConfig();
            Config.init();

            Config.getConfig().addDefault("backpack.enabled", true);
            Config.getConfig().addDefault("backpack.size", 27);
            Config.getConfig().addDefault("spawner.silktouch", true);

            Config.getConfig().options().copyDefaults(true);
            Config.save();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Config.getConfig().getBoolean("backpacks.enabled")) {
            new BackPackRecepie();
            Bukkit.getPluginManager().registerEvents(new BackPackListener(), this);
        }
        if (Config.getConfig().getBoolean("spawner.silktouch")) {
            Bukkit.getPluginManager().registerEvents(new SpawnerListener(), this);
        }

        Bukkit.getPluginManager().registerEvents(new EnderWingListener(), this);

        Utils.init();
    }

    @Override
    public void onDisable() {

    }
}
