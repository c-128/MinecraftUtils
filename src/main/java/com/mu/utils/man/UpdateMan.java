package com.mu.utils.man;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.mu.main.Main;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateMan {

    public static boolean NEWEST;

    public static void update() {
        try {
            URL url = new URL("https://api.github.com/repos/c-128/MinecraftUtils/releases");

            HttpURLConnection client = (HttpURLConnection) url.openConnection();
            client.addRequestProperty("content-type", "application/json");
            client.connect();

            InputStream is = client.getInputStream();
            JsonParser parser = new JsonParser();
            JsonArray json = (JsonArray) parser.parse(new String(is.readAllBytes()));
            is.close();

            if (json.get(0).getAsJsonObject().get("tag_name").getAsString().equalsIgnoreCase(Main.VERSION)) {
                NEWEST = true;
            } else {
                NEWEST = false;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
