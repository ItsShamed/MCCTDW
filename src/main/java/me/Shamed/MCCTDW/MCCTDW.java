package me.Shamed.MCCTDW;

import me.Shamed.MCCTDW.listeners.AdvancementListener;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;

public class MCCTDW extends JavaPlugin implements CommandExecutor {

    private Boolean IsReady = false;
    private String DiscordWebhook;

    public MCCTDW(){
        super();
    }

    protected MCCTDW(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file){
        super(loader, description, dataFolder, file);
    }

    @Override
    public void onLoad(){
        this.saveDefaultConfig();
    }

    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(new AdvancementListener(), this);
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
