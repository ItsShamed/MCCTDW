package me.Shamed.MCCTDW.webhook.components;

import com.google.gson.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import me.Shamed.MCCTDW.utils.gson.DateAdapter;
import me.Shamed.MCCTDW.webhook.components.embed.DiscordEmbed;
import me.Shamed.MCCTDW.webhook.components.embed.DiscordEmbedAttributes;

import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class DiscordMessage implements JsonSerializer<DiscordMessage> {
    @Nullable protected String content;
    @Nullable protected String username;
    @Nullable protected URL avatarUrl;
    protected boolean tts = false;
    @Nullable protected DiscordEmbed[] embeds;

    public DiscordMessage(){

    }

    public DiscordMessage(@Nullable String content) {
        this.content = content;
    }

    public DiscordMessage(@Nullable String content, @Nullable String username) {
        this.content = content;
        this.username = username;
    }

    public DiscordMessage(@Nullable String content, @Nullable String username, boolean tts) {
        this.content = content;
        this.username = username;
        this.tts = tts;
    }

    private void check() throws NullPointerException, IndexOutOfBoundsException{
        if(embeds!=null){
            for(DiscordEmbed embed:this.embeds){

                if(embed!=null)embed.check();
            }
        }
        if(!(this.content != null || this.embeds != null)) throw new NullPointerException("A message must contain at least an embed or a content");

        if(this.embeds!=null && this.embeds.length>10){
            throw new IndexOutOfBoundsException("Cannot insert more than 10 embeds in a message. " +
                    "Please refer to: https://discord.com/developers/docs/resources/webhook#execute-webhook-jsonform-params");
        }
    }

    @Nullable
    public String getContent() {
        return content;
    }

    @Nullable
    public String getUsername() {
        return username;
    }

    @Nullable
    public URL getAvatarUrl() {
        return avatarUrl;
    }

    public boolean isTTSEnabled() {
        return tts;
    }

    @Nullable
    public DiscordEmbed[] getEmbeds() {
        return embeds;
    }

    @Nullable
    public DiscordEmbed getEmbed(int index){
        if (this.embeds==null) return null;

        return this.embeds[index];
    }

    public DiscordMessage setContent(@Nullable String content) {
        this.content = content;
        return this;
    }

    public DiscordMessage setUsername(String username) {
        this.username = username;
        return this;
    }

    public DiscordMessage setAvatarUrl(URL avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public DiscordMessage setAvatarUrl(String url) throws MalformedURLException {
        this.avatarUrl = new URL(url);
        return this;
    }

    public DiscordMessage setTTS(boolean tts) {
        this.tts = tts;
        return this;
    }

    public DiscordMessage setEmbeds(DiscordEmbed[] embeds) {
        if(embeds.length<=10) this.embeds = embeds; else throw new IndexOutOfBoundsException("Cannot insert more than 10 embeds in a message. " +
                "Please refer to: https://discord.com/developers/docs/resources/webhook#execute-webhook-jsonform-params");
        return this;
    }

    public DiscordMessage setEmbed(DiscordEmbed embed, int index){
        /*List<DiscordEmbed> embedList;
        if(embeds==null){
            embedList = new ArrayList<DiscordEmbed>();
        } else{
            embedList = Arrays.asList(embeds);
        }

        embedList.set(index, embed);
        if(embedList.size()<=10) this.embeds = embedList.toArray(new DiscordEmbed[10]); else throw new IndexOutOfBoundsException("Cannot insert more than 10 embeds in a message. " +
                "Please refer to: https://discord.com/developers/docs/resources/webhook#execute-webhook-jsonform-params");*/
        this.embeds[index]=embed;
        return this;
    }

    public DiscordMessage addEmbed(DiscordEmbed embed){
        List<DiscordEmbed> embedList = new ArrayList<>();
        if(embeds!=null) Collections.addAll(embedList, this.embeds);
        Collections.addAll(embedList, embed);
        if(this.embeds!=null) this.embeds = embedList.toArray(this.embeds); else this.embeds = embedList.toArray(new DiscordEmbed[0]);




        return this;
    }

    @Override
    public JsonElement serialize(DiscordMessage discordMessage, Type type, JsonSerializationContext jsonSerializationContext) {


        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateAdapter()).registerTypeAdapter(DiscordEmbedAttributes.DiscordEmbedField.class, new DiscordEmbedAttributes.DiscordEmbedField()).create();
        JsonObject jsonMessage = new JsonObject();
        if(discordMessage.getContent()!=null)jsonMessage.addProperty("content", discordMessage.getContent());
        if(discordMessage.getUsername()!=null)jsonMessage.addProperty("username", discordMessage.getUsername());
        if(discordMessage.getAvatarUrl()!=null)jsonMessage.addProperty("avatar_url", discordMessage.getAvatarUrl().toString());
        jsonMessage.addProperty("tts", discordMessage.isTTSEnabled());
        if(discordMessage.getEmbeds()!=null)jsonMessage.add("embeds", gson.toJsonTree(discordMessage.getEmbeds()));

        return jsonMessage;
    }
}
