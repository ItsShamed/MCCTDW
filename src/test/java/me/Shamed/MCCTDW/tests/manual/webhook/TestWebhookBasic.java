package me.Shamed.MCCTDW.tests.manual.webhook;

import me.Shamed.MCCTDW.webhook.DiscordWebhook;

public class TestWebhookBasic {
    public static void main(String[] args){
        DiscordWebhook webhook = new DiscordWebhook("https://discord.com/api/webhooks/1041419605958209568/sA2bX2k4NvyQ1ZQ5KwRrfUjdxzuq-6rYdKSXcb8-NQKWCJSMo4tWgAyK8H5wB0Kzinu2");
        boolean conTest = webhook.checkIntegrity();
        if(conTest){
            System.out.println("Connection test passed.");
        } else {
            System.err.println("Connection test failed.");
        }
        webhook.send("This is a test message.");
    }
}
