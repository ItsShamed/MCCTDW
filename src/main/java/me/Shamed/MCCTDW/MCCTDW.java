package me.Shamed.MCCTDW;

import me.Shamed.MCCTDW.commands.ConfigCommandExecutor;
import me.Shamed.MCCTDW.commands.ReloadCommandExecutor;
import me.Shamed.MCCTDW.listeners.AdvancementListener;
import me.Shamed.MCCTDW.listeners.DeathListener;
import me.Shamed.MCCTDW.listeners.FundamentalListener;
import me.Shamed.MCCTDW.webhook.DiscordWebhook;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;
import java.util.logging.Logger;

public class MCCTDW extends JavaPlugin implements CommandExecutor {

    private Boolean ready = false;
    private DiscordWebhook webhook;
    private Logger logger = this.getLogger();

    public MCCTDW(){
        super();
    }

    protected MCCTDW(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file){
        super(loader, description, dataFolder, file);
    }

    @Override
    public void onLoad(){
        this.ready = globalStartupChecks();
    }

    @Override
    public void onEnable(){
        getCommand("dwurl").setExecutor(new ConfigCommandExecutor(this));
        getCommand("dwreload").setExecutor(new ReloadCommandExecutor(this));
        loadListeners();
    }

    @Override
    public void onDisable(){
        unloadListeners();
    }

    public void onFailure(String reason){
        logger.severe("The plugin encountered an error and thus stopped working for the following reason: " +
                reason);
        sendToAllOp(ChatColor.translateAlternateColorCodes('&', "&8[&bMCCTDW&8] &l&cThe plugin " +
                "encountered an error and thus stopped working for the following reason: " + reason+"\n" +
                "Do /dwreload to reload the plugin."));
        this.ready=false;
        unloadListeners();
    }

    private Boolean globalStartupChecks(){
        if(this.getConfig().getString("url")==null){
            this.saveDefaultConfig();
            return false;

        } else{
            this.webhook = new DiscordWebhook(this);
            return webhook.checkIntegrity();

        }
    }

    public Boolean isReady(){
        return this.ready;
    }

    public void setReady(Boolean ready) {
        this.ready = ready;
    }

    public void reload(){
        getLogger().info("Reloading plugin...");
        unloadListeners();
        if(globalStartupChecks()){
            this.ready=true;
            loadListeners();
            for (Player player:getServer().getOnlinePlayers()){
                if(player.isOp()){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            "&8[&bMCCTDW&8] &r&aThe plugin has been reloaded"));
                }
            }
            getLogger().info("Plugin reloaded.");
        }else{
            logger.warning("The plugin is not ready. A value may be wrong / missing in the config file, please" +
                    " fix it then do /dwreload (or use /dwurl). Either way," +
                    "an error occurred when trying to reload.");
            sendToAllOp(ChatColor.translateAlternateColorCodes('&', "&8[&bMCCTDW&8] &l&cThe" +
                    " plugin is not ready. A value may be wrong / missing in the config file, please fix it then do" +
                    " /dwreload (or use /dwurl). Either way, an error occurred when trying to reload."));
        }
    }

    private void loadListeners(){
        if(this.ready){
            getServer().getPluginManager().registerEvents(new AdvancementListener(this), this);
            getServer().getPluginManager().registerEvents(new FundamentalListener(this), this);
            getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        }
        else {
            logger.warning("The plugin is not ready. A value may be wrong / missing in the config file, please " +
                    "fix it then do /dwreload (or use /dwurl). Either way," +
                    "an error occurred during plugin initialization.");
        }
    }

    private void unloadListeners(){
        this.ready=false;
        HandlerList.unregisterAll(this);
    }

    public void sendToAllOp(String msg){
        for(Player player:getServer().getOnlinePlayers()){
            if(player.hasPermission("mcctdw")){
                player.sendMessage(msg);
            }
        }
    }

    public DiscordWebhook getWebhook() {
        return webhook;
    }
}
