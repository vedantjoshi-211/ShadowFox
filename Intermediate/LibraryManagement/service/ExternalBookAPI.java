package com.library.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExternalBookAPI {

    public static String fetchDescriptionByIsbn(String isbn) {
        try {
            URL url = new URL("https://openlibrary.org/isbn/" + isbn + ".json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            JsonObject json = JsonParser.parseReader(new InputStreamReader(conn.getInputStream())).getAsJsonObject();
            return json.has("description") ? json.get("description").getAsString() : "";
        } catch (Exception e) {
            return "";
        }
    }
}
