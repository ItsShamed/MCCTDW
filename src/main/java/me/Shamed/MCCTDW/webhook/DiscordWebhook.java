package me.Shamed.MCCTDW.webhook;

import me.Shamed.MCCTDW.MCCTDW;

import java.io.IOException;
import java.util.logging.Logger;

public class DiscordWebhook {

    private MCCTDW plugin;
    private Logger logger;
    private WebhookHandler handler;

    public DiscordWebhook(MCCTDW plugin){
        this.plugin=plugin;
        this.logger=plugin.getLogger();
        this.handler=new WebhookHandler(this,(String) plugin.getConfig().get("url"));
    }

    public void onFailure(String reason){
        plugin.onFailure(reason);
    }

    public Logger getLogger() {
        return logger;
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
