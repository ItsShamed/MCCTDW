package me.Shamed.MCCTDW.webhook.components.formatted;

import me.Shamed.MCCTDW.webhook.components.DiscordMessage;
import me.Shamed.MCCTDW.webhook.components.embed.DiscordEmbed;
import me.Shamed.MCCTDW.webhook.components.embed.DiscordEmbedAttributes;
import org.bukkit.entity.Player;

public class JoinMessage extends DiscordMessage {

    public JoinMessage(Player player){
        DiscordEmbed embed = new DiscordEmbed();
        embed.setAuthor(new DiscordEmbedAttributes.DiscordEmbedAuthor(String.format("%s joined the game.", player.getName()))
                .setIcon(String.format("https://minotar.net/helm/%s/100.png", player.getUniqueId())));
        embed.setColor("#55FF55");

        this.addEmbed(embed);
    }

}

