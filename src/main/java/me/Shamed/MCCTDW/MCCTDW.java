package me.Shamed.MCCTDW;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class MCCTDW extends JavaPlugin implements CommandExecutor {

    private Boolean IsReady = false;
    private String DiscordWebhook;

    @Override
    public void onLoad(){
        this.saveDefaultConfig();
    }

    @Override
    public void onEnable(){

    }

    @Override
    public void onDisable(){

    }

    public void onFailure(String reason){

    }

    private Boolean globalStatrupChecks(){
        return false;
    }

    public Boolean IsReady(){
        return this.IsReady;
    }

    public void setReady(Boolean ready) {
        IsReady = ready;
    }


}
