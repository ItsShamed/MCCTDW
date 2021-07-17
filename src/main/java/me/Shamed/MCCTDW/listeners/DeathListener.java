package me.Shamed.MCCTDW.listeners;

import me.Shamed.MCCTDW.MCCTDW;
import me.Shamed.MCCTDW.webhook.components.formatted.PlayerDeathMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    private MCCTDW plugin;

    public DeathListener(MCCTDW plugin){
        this.plugin=plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDeath(PlayerDeathEvent event){
        plugin.getWebhook().send(new PlayerDeathMessage(event));
    }
}
