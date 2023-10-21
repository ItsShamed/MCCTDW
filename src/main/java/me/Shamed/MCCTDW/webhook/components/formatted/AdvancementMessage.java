package me.Shamed.MCCTDW.webhook.components.formatted;

import me.Shamed.MCCTDW.utils.gson.LocaleProvider;
import me.Shamed.MCCTDW.webhook.components.DiscordMessage;
import me.Shamed.MCCTDW.webhook.components.embed.DiscordEmbed;
import me.Shamed.MCCTDW.webhook.components.embed.DiscordEmbedAttributes;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AdvancementMessage extends DiscordMessage {

    public AdvancementMessage(@NotNull PlayerAdvancementDoneEvent event){
        Player p = event.getPlayer();

        DiscordEmbed embed = new DiscordEmbed();
        AdvancementDisplay display = event.getAdvancement().getDisplay();
        String namespacedKey = event.getAdvancement().getKey().getKey();
        embed.setTitle(String.format("%s earned an achievement.", p.getName()))
                .setDescription(display != null ? asNonNull(display.getDescription()) :
                        LocaleProvider.translateLocale(convertNamespaceToLocale(namespacedKey + ".description")));

        embed.setColor("#55FFFF");
        embed.addField(new DiscordEmbedAttributes.DiscordEmbedField("Achievement", display != null
                        ? asNonNull(display.getTitle())
                        : LocaleProvider.translateLocale(convertNamespaceToLocale(namespacedKey + ".title"))))
        .addField(new DiscordEmbedAttributes.DiscordEmbedField("Namespaced Key", namespacedKey, true));

        embed.setThumbnail(new DiscordEmbedAttributes.DiscordEmbedMedia.DiscordEmbedThumbnail(String.format("https://minotar.net/helm/%s/100.png", p.getUniqueId())));
        this.addEmbed(embed);

    }

    private String convertNamespaceToLocale(String namespacedKey){
        return "advancements."+namespacedKey.replace('/','.');
    }

    @NotNull
    private String asNonNull(@Nullable String nullableString) {
        if (nullableString == null)
            return "(null)";
        return nullableString;
    }
}
