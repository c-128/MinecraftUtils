package com.mu.main;

import com.mu.listener.BackPackListener;
import com.mu.recepies.BackPackRecepie;
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        new BackPackRecepie();

        Bukkit.getPluginManager().registerEvents(new BackPackListener(), this);

        Utils.init();
    }

    @Override
    public void onDisable() {

    }
}
