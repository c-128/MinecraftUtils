package com.mu.main;

import com.mu.blocks.Quarry;
import com.mu.commands.MinecraftUtilsCommand;
import com.mu.enchants.GlowEnchantment;
import com.mu.item.Items;
import com.mu.listener.*;
import com.mu.recepies.BackPackRecipe;
import com.mu.recepies.EnchantedGoldenAppleRecipe;
import com.mu.recepies.InfiniteFoodItemRecipe;
import com.mu.recepies.QuarryRecipe;
import com.mu.utils.Config;
import com.mu.utils.man.FilesMan;
import com.mu.utils.man.QuarryMan;
import com.mu.utils.man.UpdateMan;
import com.mu.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Random;

public final class Main extends JavaPlugin {

    public static Random R = new Random();
    public static String VERSION = "1.1";

    @Override
    public void onEnable() {
        try {
            GlowEnchantment.registerGlow();
            Items.init();

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

            Config.getConfig().addDefault("quarry.enabled", true);
            Config.getConfig().addDefault("quarry.blacklist", new String[]{"BEDROCK"});
            Config.getConfig().addDefault("quarry.speed", 1000);

            Config.getConfig().options().copyDefaults(true);
            Config.save();

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
            if (Config.getConfig().getBoolean("quarry.enabled")) {
                Bukkit.getPluginManager().registerEvents(new QuarryListener(), this);
                QuarryMan.init();
                new QuarryRecipe();
            }

            Bukkit.getPluginManager().registerEvents(new UpdateNotifyListener(), this);
            Bukkit.getPluginManager().registerEvents(new ExtraOreGenListener(), this);
            Bukkit.getPluginManager().registerEvents(new EnderWingListener(), this);

            getCommand("mu").setExecutor(new MinecraftUtilsCommand());

            Utils.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        try {
            if (Config.getConfig().getBoolean("quarry.enabled")) {
                QuarryMan.save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
