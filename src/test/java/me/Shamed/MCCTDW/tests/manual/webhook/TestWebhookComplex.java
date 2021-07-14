package me.Shamed.MCCTDW.tests.manual.webhook;

import me.Shamed.MCCTDW.webhook.DiscordWebhook;
import me.Shamed.MCCTDW.webhook.components.DiscordMessage;
import me.Shamed.MCCTDW.webhook.components.embed.DiscordEmbed;
import me.Shamed.MCCTDW.webhook.components.embed.DiscordEmbedAttributes;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

public class TestWebhookComplex {
    public static void main(String[] args) throws MalformedURLException {

        Scanner scanner = new Scanner(System.in);

        String url = scanner.next();

        DiscordWebhook webhook = new DiscordWebhook(url);
        boolean conTest = webhook.checkIntegrity();
        if(conTest){
            System.out.println("Connection test passed.");
        } else {
            System.err.println("Connection test failed.");
        }
        webhook.send("This is a test message.");

        DiscordEmbed embed1 = new DiscordEmbed().setAuthor(new DiscordEmbedAttributes.DiscordEmbedAuthor("Shamed", "https://github.com/ItsShamed"))
                .setTitle("Test embed 1")
                .setDescription("Test desc")
                .setUrl(new URL("https://github.com/ItsShamed"))
                .setTimestamp(new Date())
                .setColor("f06ed4")
                .setFooter(new DiscordEmbedAttributes.DiscordEmbedFooter("Test footer", "https://github.com/ItsShamed"))
                .setImage(new DiscordEmbedAttributes.DiscordEmbedMedia.DiscordEmbedImage("https://images.anime-pictures.net/7b1/7b14a75cb3e85a3766242172a197a7d4.png?if=ANIME-PICTURES.NET_-_704571-1920x1080-violet+evergarden-kyoto+animation-violet+evergarden+%28character%29-fikrimochizou-single-looking+at+viewer.png"))
                .setThumbnail(new DiscordEmbedAttributes.DiscordEmbedMedia.DiscordEmbedThumbnail("https://media-exp3.licdn.com/dms/image/C560BAQF4M0IT6_BcsA/company-logo_200_200/0/1620918122423?e=2159024400&v=beta&t=bS2Zs3ue9dfCics0Y1vaBxJJUKQJa_TudRCBf9zNfMY"))
                .setProvider(new DiscordEmbedAttributes.DiscordEmbedProvider("Shamed", "https://github.com/ItsShamed"))
                .addField(new DiscordEmbedAttributes.DiscordEmbedField("Field 1", "test", true))
                .addField(new DiscordEmbedAttributes.DiscordEmbedField("Field 2", "test", true))
                .addField(new DiscordEmbedAttributes.DiscordEmbedField("Field 3", "test", false))
                .addField(new DiscordEmbedAttributes.DiscordEmbedField("Field 4", "test", true));

        DiscordEmbed embed2 = new DiscordEmbed().setAuthor(new DiscordEmbedAttributes.DiscordEmbedAuthor("Shamed", "https://github.com/ItsShamed"))
                .setTitle("Test embed 2")
                .setDescription("Test desc 2")
                .setUrl(new URL("https://github.com/ItsShamed"))
                .setTimestamp(new Date())
                .setColor("#4287f5")
                .setFooter(new DiscordEmbedAttributes.DiscordEmbedFooter("Test footer 2", "https://github.com/ItsShamed"))
                .setImage(new DiscordEmbedAttributes.DiscordEmbedMedia.DiscordEmbedImage("https://images.anime-pictures.net/7b1/7b14a75cb3e85a3766242172a197a7d4.png?if=ANIME-PICTURES.NET_-_704571-1920x1080-violet+evergarden-kyoto+animation-violet+evergarden+%28character%29-fikrimochizou-single-looking+at+viewer.png"))
                .setThumbnail(new DiscordEmbedAttributes.DiscordEmbedMedia.DiscordEmbedThumbnail("https://media-exp3.licdn.com/dms/image/C560BAQF4M0IT6_BcsA/company-logo_200_200/0/1620918122423?e=2159024400&v=beta&t=bS2Zs3ue9dfCics0Y1vaBxJJUKQJa_TudRCBf9zNfMY"))
                .setProvider(new DiscordEmbedAttributes.DiscordEmbedProvider("Shamed", "https://github.com/ItsShamed"))
                .addField(new DiscordEmbedAttributes.DiscordEmbedField("Field 5", "test", true))
                .addField(new DiscordEmbedAttributes.DiscordEmbedField("Field 6", "test", true))
                .addField(new DiscordEmbedAttributes.DiscordEmbedField("Field 7", "test", false))
                .addField(new DiscordEmbedAttributes.DiscordEmbedField("Field 8", "test", true));

        DiscordMessage message = new DiscordMessage("This is a complex test message.", "Test username", false);
        message.setAvatarUrl(new URL("https://www.osustuff.org/img/avatars/base-images/2016-09-30/09-00-46_39455_thumb.png"))
        .addEmbed(embed1)
        .addEmbed(embed2);

        webhook.send(message);
    }
}
