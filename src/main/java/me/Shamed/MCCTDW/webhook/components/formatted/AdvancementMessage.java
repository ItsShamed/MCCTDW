package me.Shamed.MCCTDW.webhook.components.formatted;

import me.Shamed.MCCTDW.utils.gson.LocaleProvider;
import me.Shamed.MCCTDW.webhook.components.DiscordMessage;
import me.Shamed.MCCTDW.webhook.components.embed.DiscordEmbed;
import me.Shamed.MCCTDW.webhook.components.embed.DiscordEmbedAttributes;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.jetbrains.annotations.NotNull;

public class AdvancementMessage extends DiscordMessage {

    public AdvancementMessage(@NotNull PlayerAdvancementDoneEvent event){
        Player p = event.getPlayer();

        DiscordEmbed embed = new DiscordEmbed();
        embed.setTitle(String.format("%s earned an achievement.", p.getName()))
                .setDescription(LocaleProvider.translateLocale(convertNamespaceToLocale(event.getAdvancement().getKey().getKey())+".description"));

        embed.setColor("#55FFFF");
        embed.addField(new DiscordEmbedAttributes.DiscordEmbedField("Achievement", LocaleProvider.translateLocale(convertNamespaceToLocale(event.getAdvancement().getKey().getKey())+".title")))
        .addField(new DiscordEmbedAttributes.DiscordEmbedField("Namespaced Key", event.getAdvancement().getKey().getKey(), true));

        embed.setThumbnail(new DiscordEmbedAttributes.DiscordEmbedMedia.DiscordEmbedThumbnail(String.format("https://minotar.net/helm/%s/100.png", p.getUniqueId())));
        this.addEmbed(embed);

    }

    private String convertNamespaceToLocale(String namespacedKey){
        return "advancements."+namespacedKey.replace('/','.');
    }
}
