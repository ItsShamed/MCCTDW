package me.Shamed.MCCTDW.listeners;

import me.Shamed.MCCTDW.MCCTDW;
import me.Shamed.MCCTDW.webhook.components.formatted.AdvancementMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import java.util.EventListener;

public class AdvancementListener implements Listener {

    private MCCTDW plugin;

    public AdvancementListener(MCCTDW plugin){
        this.plugin=plugin;
    }

    @EventHandler
    public void onPlayerAdvancement(PlayerAdvancementDoneEvent event){
        plugin.getWebhook().send(new AdvancementMessage(event));
    }

}
