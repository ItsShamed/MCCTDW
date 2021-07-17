package me.Shamed.MCCTDW.utils.gson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class LocaleProvider {

    private LocaleProvider(){

    }

    @SuppressWarnings("deprecated")
    public static String translateLocale(String namespace){

        Gson gson = new Gson();
        InputStream locale = LocaleProvider.class.getClassLoader().getResourceAsStream("en_us.json");
        JsonObject jsonObject = gson.fromJson(new InputStreamReader(locale, StandardCharsets.UTF_8), JsonObject.class);
        return jsonObject.get(namespace).getAsString();

        

    }

}
