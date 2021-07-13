package me.Shamed.MCCTDW.webhook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import me.Shamed.MCCTDW.MCCTDW;
import me.Shamed.MCCTDW.utils.gson.DateAdapter;
import me.Shamed.MCCTDW.webhook.components.DiscordMessage;
import me.Shamed.MCCTDW.webhook.components.embed.DiscordEmbed;
import me.Shamed.MCCTDW.webhook.components.embed.DiscordEmbedAttributes;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

public class DiscordWebhook {

    @Nullable private MCCTDW plugin;
    @Nullable private Logger logger;
    @NotNull final private WebhookHandler handler;
    private Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting()
            .registerTypeAdapter(DiscordEmbedAttributes.DiscordEmbedField.class, new DiscordEmbedAttributes.DiscordEmbedField())
            .registerTypeAdapter(DiscordMessage.class, new DiscordMessage())
            .registerTypeAdapter(DiscordEmbed.class, new DiscordEmbed())
            .registerTypeAdapter(Date.class, new DateAdapter())
            .create();

    public DiscordWebhook(String url){
        this.handler=new WebhookHandler(this, url);
    }

    public DiscordWebhook(MCCTDW plugin){
        this.plugin=plugin;
        this.logger=plugin.getLogger();
        this.handler=new WebhookHandler(this,(String) plugin.getConfig().get("url"));
    }

    public void onFailure(String reason){
        if(plugin!=null)plugin.onFailure(reason); else System.err.println("Discord webhook failure: "+reason);
    }

    @Nullable
    public Logger getLogger() {
        return logger;
    }

    public void send(String message){
        send(new DiscordMessage(message));
    }

    public void send(DiscordMessage message){
        try {
            System.out.println(gson.toJson(message, DiscordMessage.class));
            handler.post(gson.toJson(message, DiscordMessage.class));
        } catch (Exception e){
            if(logger!=null) logger.severe(e.getStackTrace().toString()); else e.printStackTrace();
            onFailure(e.getMessage());
        }
    }

    public Boolean checkIntegrity(){
        if (!handler.isValid()) return false;
        try{
            return handler.get().getStatusCode() >= 200 && handler.get().getStatusCode() < 300;
        } catch (IOException e){
            return false;
        }
    }
}
