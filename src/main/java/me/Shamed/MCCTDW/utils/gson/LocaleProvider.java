package me.Shamed.MCCTDW.utils.gson;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class LocaleProvider {

    private LocaleProvider(){

    }

    public static String translateLocale(String namespace){

        Gson gson = new Gson();
        InputStream locale = LocaleProvider.class.getClassLoader().getResourceAsStream("en_us.json");
        try(InputStreamReader streamReader = new InputStreamReader(locale, StandardCharsets.UTF_8); BufferedReader reader = new BufferedReader(streamReader)){
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine())!=null){
                stringBuilder.append(line).append("\n");
            }
            String json = stringBuilder.toString();
            return JsonParser.parseString(json).getAsJsonObject().get(namespace).getAsString();

        } catch (IOException e){
            return "Error";
        }
        

    }

}
