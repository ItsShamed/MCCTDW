package me.Shamed.MCCTDW.webhook.components.formatted;

import me.Shamed.MCCTDW.webhook.components.DiscordMessage;
import org.bukkit.entity.Player;

import java.net.MalformedURLException;
import java.net.URL;

public class ChatMessage extends DiscordMessage {

    public ChatMessage(Player player, String message) throws MalformedURLException {
        this.username = player.getName();
        this.avatarUrl= new URL(String.format("https://minotar.net/helm/%s/100.png", player.getUniqueId()));
        this.content = message;
    }

}
