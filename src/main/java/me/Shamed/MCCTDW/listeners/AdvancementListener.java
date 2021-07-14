package me.Shamed.MCCTDW.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import java.util.EventListener;

public class AdvancementListener implements Listener {

    public AdvancementListener(){}

    @EventHandler
    public void onPlayerAdvancement(PlayerAdvancementDoneEvent event){
        Player p = event.getPlayer();
        p.sendMessage(event.getAdvancement().getKey().getKey());
    }

}
