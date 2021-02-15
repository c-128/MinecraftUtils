package com.mu.main;

import com.mu.listener.*;
import com.mu.recepies.BackPackRecipe;
import com.mu.recepies.EnchantedGoldenAppleRecipe;
import com.mu.recepies.InfiniteFoodItemRecipe;
import com.mu.utils.Config;
import com.mu.utils.FilesMan;
import com.mu.utils.UpdateMan;
import com.mu.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Random;

public final class Main extends JavaPlugin {

    public static Random R = new Random(Bukkit.getWorld("world").getSeed());
    public static String VERSION = "0.2";

    @Override
    public void onEnable() {
        try {
            UpdateMan.update();

            FilesMan.setup();

            saveDefaultConfig();
            Config.init();

            Config.getConfig().addDefault("backpack.enabled", true);
            Config.getConfig().addDefault("backpack.size", 27);

            Config.getConfig().addDefault("spawner.silktouch", true);

            Config.getConfig().addDefault("oregen.extra-diamonds", true);
            Config.getConfig().addDefault("oregen.extra-debris", true);

            Config.getConfig().addDefault("food.infinite-food-item", true);
            Config.getConfig().addDefault("food.craftable-golden-apple", true);

            Config.getConfig().options().copyDefaults(true);
            Config.save();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Config.getConfig().getBoolean("backpack.enabled")) {
            new BackPackRecipe();
            Bukkit.getPluginManager().registerEvents(new BackPackListener(), this);
        }
        if (Config.getConfig().getBoolean("spawner.silktouch")) {
            Bukkit.getPluginManager().registerEvents(new SpawnerListener(), this);
        }
        if (Config.getConfig().getBoolean("food.infinite-food-item")) {
            new InfiniteFoodItemRecipe();
            Bukkit.getPluginManager().registerEvents(new InfiniteFoodItemListener(), this);
        }
        if (Config.getConfig().getBoolean("food.craftable-golden-apple")) {
            new EnchantedGoldenAppleRecipe();
        }

        Bukkit.getPluginManager().registerEvents(new UpdateNotifyListener(), this);
        Bukkit.getPluginManager().registerEvents(new ExtraOreGenListener(), this);
        Bukkit.getPluginManager().registerEvents(new EnderWingListener(), this);

        Utils.init();
    }

    @Override
    public void onDisable() {

    }
}
