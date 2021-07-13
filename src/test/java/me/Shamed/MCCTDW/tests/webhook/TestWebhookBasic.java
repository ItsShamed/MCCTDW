package me.Shamed.MCCTDW.tests.webhook;

import me.Shamed.MCCTDW.webhook.DiscordWebhook;

public class TestWebhookBasic {
    public static void main(String[] args){
        DiscordWebhook webhook = new DiscordWebhook("https://discord.com/api/webhooks/863180540802105404/plG10QYWAbMqO6szFM22Huc3Zg9nE6p4Bg5n6oU-zPoaWULuqkDFaxaQF0q-c_iMFZGb");
        boolean conTest = webhook.checkIntegrity();
        if(conTest){
            System.out.println("Connection test passed.");
        } else {
            System.err.println("Connection test failed.");
        }
        webhook.send("This is a test message.");
    }
}
