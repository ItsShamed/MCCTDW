package me.Shamed.MCCTDW.webhook.components.formatted;

import me.Shamed.MCCTDW.webhook.components.DiscordMessage;
import me.Shamed.MCCTDW.webhook.components.embed.DiscordEmbed;
import me.Shamed.MCCTDW.webhook.components.embed.DiscordEmbedAttributes;
import org.bukkit.entity.Player;

public class QuitMessage extends DiscordMessage {

    public QuitMessage(Player player){
        DiscordEmbed embed = new DiscordEmbed();
        embed.setAuthor(new DiscordEmbedAttributes.DiscordEmbedAuthor(String.format("%s left the game.", player.getName()))
                .setIcon(String.format("https://minotar.gg/helm/%s/100.png", player.getUniqueId())));
        embed.setColor("#FF5555");

        this.addEmbed(embed);
    }
}
