package me.Shamed.MCCTDW.webhook;

import com.sun.istack.internal.Nullable;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class WebhookHandler {

    private URL url;
    private Boolean valid;
    private DiscordWebhook discord;
    @Nullable private Logger logger;

    public WebhookHandler(DiscordWebhook discord, String url){
        try {
            this.url = new URL(url);
            this.valid = true;
        } catch (MalformedURLException ex){
            this.valid = false;
        }
        this.discord=discord;
        this.logger=discord.getLogger();
    }

    public WebhookHandler(DiscordWebhook discord, URL url){
        this.url=url;
        this.discord=discord;
        this.logger=discord.getLogger();
    }

    public URL getUrl(){
        return url;
    }

    public void onFailure(String reason){
        discord.onFailure(reason);
    }

    public String getStringURL(){
        return this.url.toString();
    }

    public DiscordWebhook getDiscordWebhook(){
        return discord;
    }

    public WebhookResponse get() throws IOException{
        GetRequest request = new GetRequest(this, url);
        return request.query();
    }

    public void post(String json){
        postRequest request = new postRequest(this, url, json);
        request.start();
    }

    @Nullable
    protected Logger getLogger(){
        return logger;
    }

    public Boolean isValid(){
        return this.valid;
    }

    protected class GetRequest{
        private WebhookHandler webhook;
        private URL url;

        protected GetRequest(WebhookHandler webhook, URL url){
            this.webhook=webhook;
            this.url=url;
        }

        public WebhookResponse query() throws IOException {
            HttpURLConnection client = (HttpURLConnection) url.openConnection();
            client.setRequestMethod("GET");
            client.setRequestProperty("User-Agent", "Spigot/0.1");

            int code = client.getResponseCode();
            StringBuilder response = new StringBuilder();

            try(BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()))){
                String line;
                while((line = in.readLine())!=null){
                    response.append(line);
                }
            }

            return new WebhookResponse(code, response.toString());
        }


    }


    protected class postRequest extends Thread{
        private WebhookHandler webhook;
        private URL url;
        private String json;

        protected postRequest(WebhookHandler webhook, URL url, String json){
            this.webhook=webhook;
            this.url=url;
            this.json=json;
        }

        @Override
        public void run() {
            HttpURLConnection client;
            try {
                client = (HttpURLConnection) url.openConnection();

                client.setRequestMethod("POST");
                client.setRequestProperty("User-Agent", "Spigot/0.1");
                client.setRequestProperty("Content-Type", "application/json");

                client.setDoOutput(true);
                try(DataOutputStream out = new DataOutputStream(client.getOutputStream())){
                    out.writeBytes(this.json);
                    out.flush();
                } catch (IOException e){
                    if(logger!=null)webhook.getLogger().severe(e.getStackTrace().toString()); else e.printStackTrace();
                    webhook.onFailure("Failed to write outgoing request.");
                }

                int responseCode = client.getResponseCode();

                if(!(responseCode>=200 && responseCode<300)){
                    webhook.onFailure("Discord responded with error status "+ responseCode);
                }

            } catch (IOException e) {
                if(logger!=null)webhook.getLogger().severe(e.getStackTrace().toString()); else e.printStackTrace();
                webhook.onFailure("Failed to open HTTP Connection.");
            }


        }
    }

}
