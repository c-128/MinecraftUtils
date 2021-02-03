package com.mu.main;

import com.mu.listener.BackPackListener;
import com.mu.recepies.BackPackRecepie;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        new BackPackRecepie();

        Bukkit.getPluginManager().registerEvents(new BackPackListener(), this);
    }

    @Override
    public void onDisable() {

    }
}
