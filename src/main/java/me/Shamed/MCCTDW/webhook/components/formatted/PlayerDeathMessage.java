package me.Shamed.MCCTDW.webhook.components.formatted;

import me.Shamed.MCCTDW.webhook.components.DiscordMessage;
import me.Shamed.MCCTDW.webhook.components.embed.DiscordEmbed;
import me.Shamed.MCCTDW.webhook.components.embed.DiscordEmbedAttributes;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class PlayerDeathMessage extends DiscordMessage {

    public PlayerDeathMessage(PlayerDeathEvent deathEvent){
        Player player = deathEvent.getEntity();
        String cause;

        if(player.getLastDamageCause() instanceof EntityDamageByEntityEvent){
            EntityDamageByEntityEvent damageEvent = (EntityDamageByEntityEvent) player.getLastDamageCause();
            cause = damageEvent.getDamager().getName();
        } else{
            Objects.requireNonNull(player.getLastDamageCause()).getCause();
            cause = player.getLastDamageCause().getCause().toString();
        }

        DiscordEmbed embed = new DiscordEmbed();

        embed.setTitle(String.format("%s died", player.getName()))
                .setDescription(deathEvent.getDeathMessage())
                .setThumbnail(new DiscordEmbedAttributes.DiscordEmbedMedia.DiscordEmbedThumbnail(String.format("https://minotar.net/helm/%s/100.png", player.getUniqueId())))
                .addField(new DiscordEmbedAttributes.DiscordEmbedField("Cause", cause))
                .addField(new DiscordEmbedAttributes.DiscordEmbedField("Coordinates", String.format("X: %f Y: %f Z: %f", player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()), true));

        this.addEmbed(embed);
    }

}
