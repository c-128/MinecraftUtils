package com.mu.commands;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MinecraftUtilsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Invalid arguments! Usage:");
                sender.sendMessage(ChatColor.RED + "/mu updates - List all of the updates");
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("updates")) {
                    try {
                        URL url = new URL("https://api.github.com/repos/c-128/MinecraftUtils/releases");

                        HttpURLConnection client = (HttpURLConnection) url.openConnection();
                        client.addRequestProperty("content-type", "application/json");
                        client.connect();

                        InputStream is = client.getInputStream();
                        JsonParser parser = new JsonParser();
                        JsonArray json = (JsonArray) parser.parse(new String(is.readAllBytes()));
                        is.close();

                        int i = json.size();
                        while (i > 0) {
                            i--;
                            JsonObject val = json.get(i).getAsJsonObject();

                            sender.sendMessage(ChatColor.GREEN + val.get("tag_name").getAsString() + ": " + val.get("name").getAsString());
                        }
                    } catch (IOException e) {
                        sender.sendMessage(ChatColor.RED + "Something went wrong! " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You don't have enough permission to run this command!");
        }

        return false;
    }
}
