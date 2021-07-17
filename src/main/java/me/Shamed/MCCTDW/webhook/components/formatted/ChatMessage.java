package me.Shamed.MCCTDW.webhook.components.formatted;

import me.Shamed.MCCTDW.webhook.components.DiscordMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.net.MalformedURLException;
import java.net.URL;

public class ChatMessage extends DiscordMessage {

    public ChatMessage(AsyncPlayerChatEvent event) throws MalformedURLException {
        this.username = event.getPlayer().getName();
        this.avatarUrl= new URL(String.format("https://minotar.net/helm/%s/100.png", event.getPlayer().getUniqueId()));
        this.content = event.getMessage();
    }

}
