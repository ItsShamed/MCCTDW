package me.Shamed.MCCTDW.listeners;

import me.Shamed.MCCTDW.MCCTDW;
import me.Shamed.MCCTDW.webhook.components.formatted.ChatMessage;
import me.Shamed.MCCTDW.webhook.components.formatted.JoinMessage;
import me.Shamed.MCCTDW.webhook.components.formatted.QuitMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.net.MalformedURLException;

public class FundamentalListener implements Listener {

    private MCCTDW plugin;

    public FundamentalListener(MCCTDW plugin){
        this.plugin=plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onChat(AsyncPlayerChatEvent event) {
        try {
            plugin.getWebhook().send(new ChatMessage(event));
        } catch (Exception e) {
            plugin.getLogger().severe(e.getStackTrace().toString());
            plugin.onFailure(e.getClass().getName()+": "+e.getMessage());
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event){
        plugin.getWebhook().send(new JoinMessage(event.getPlayer()));
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerQuit(PlayerQuitEvent event){
        plugin.getWebhook().send(new QuitMessage(event.getPlayer()));
    }

}
