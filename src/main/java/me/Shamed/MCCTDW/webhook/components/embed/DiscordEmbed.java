package me.Shamed.MCCTDW.webhook.components.embed;

import com.google.gson.*;
import com.sun.istack.internal.Nullable;
import me.Shamed.MCCTDW.utils.gson.DateAdapter;

import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DiscordEmbed implements JsonSerializer<DiscordEmbed> {
    @Nullable private String title;
    @Nullable private String description;
    @Nullable private URL url;
    @Nullable private Date timestamp;
    @Nullable private Long color;
    @Nullable private DiscordEmbedAttributes.DiscordEmbedFooter footer;
    @Nullable private DiscordEmbedAttributes.DiscordEmbedMedia.DiscordEmbedImage image;
    @Nullable private DiscordEmbedAttributes.DiscordEmbedMedia.DiscordEmbedThumbnail thumbnail;
    @Nullable private DiscordEmbedAttributes.DiscordEmbedMedia.DiscordEmbedVideo video;
    @Nullable private DiscordEmbedAttributes.DiscordEmbedProvider provider;
    @Nullable private DiscordEmbedAttributes.DiscordEmbedAuthor author;
    @Nullable private DiscordEmbedAttributes.DiscordEmbedField[] fields;

    public Long hexColor(String hexCode){
        String hex;
        if(hexCode.startsWith("#")){
            hex = hexCode.substring(1);
        } else if (hexCode.startsWith("0x")){
            hex = hexCode.substring(2);
        } else{
            hex = hexCode;
        }
        return Long.parseLong(hex, 16);
    }

    public DiscordEmbed() {
    }


    public void check() throws NullPointerException{
        if(!(this.title!=null || this.description!=null || this.footer!=null
                || this.image!=null || this.video!=null || this.thumbnail!=null
                || this.provider!=null || this.author!=null))throw new NullPointerException("Embed must contain at least one of the following: title, description, footer, image, video, thumbnail, provider or author.");

    }

    @Nullable
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @Nullable
    public URL getUrl() {
        return url;
    }

    @Nullable
    public Date getTimestamp() {
        return timestamp;
    }

    @Nullable
    public DiscordEmbedAttributes.DiscordEmbedFooter getFooter() {
        return footer;
    }

    @Nullable
    public DiscordEmbedAttributes.DiscordEmbedMedia.DiscordEmbedImage getImage() {
        return image;
    }

    @Nullable
    public DiscordEmbedAttributes.DiscordEmbedMedia.DiscordEmbedThumbnail getThumbnail() {
        return thumbnail;
    }

    @Nullable
    public DiscordEmbedAttributes.DiscordEmbedMedia.DiscordEmbedVideo getVideo() {
        return video;
    }

    @Nullable
    public DiscordEmbedAttributes.DiscordEmbedAuthor getAuthor() {
        return author;
    }

    @Nullable
    public DiscordEmbedAttributes.DiscordEmbedField[] getFields() {
        return fields;
    }

    @Nullable
    public DiscordEmbedAttributes.DiscordEmbedField getField(int index){
        return this.fields[index];
    }

    @Nullable
    public Long getColor() {
        return color;
    }

    @Nullable
    public DiscordEmbedAttributes.DiscordEmbedProvider getProvider() {
        return provider;
    }

    public DiscordEmbed setTitle(@Nullable String title) {
        this.title = title;
        return this;
    }

    public DiscordEmbed setDescription(@Nullable String description) {
        this.description = description;
        return this;
    }

    public DiscordEmbed setUrl(String url) throws MalformedURLException {
        this.url = new URL(url);
        return this;
    }

    public DiscordEmbed setUrl(@Nullable URL url) {
        this.url = url;
        return this;
    }

    public DiscordEmbed setTimestamp(@Nullable Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public DiscordEmbed setColor(@Nullable Long color) {
        this.color = color;
        return this;
    }

    public DiscordEmbed setColor(String colorCode){
        setColor(hexColor(colorCode));
        return this;
    }

    public DiscordEmbed setFooter(@Nullable DiscordEmbedAttributes.DiscordEmbedFooter footer) {
        this.footer = footer;
        return this;
    }

    public DiscordEmbed setImage(@Nullable DiscordEmbedAttributes.DiscordEmbedMedia.DiscordEmbedImage image) {
        this.image = image;
        return this;
    }

    public DiscordEmbed setThumbnail(@Nullable DiscordEmbedAttributes.DiscordEmbedMedia.DiscordEmbedThumbnail thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public DiscordEmbed setVideo(@Nullable DiscordEmbedAttributes.DiscordEmbedMedia.DiscordEmbedVideo video) {
        this.video = video;
        return this;
    }

    public DiscordEmbed setProvider(@Nullable DiscordEmbedAttributes.DiscordEmbedProvider provider) {
        this.provider = provider;
        return this;
    }

    public DiscordEmbed setAuthor(@Nullable DiscordEmbedAttributes.DiscordEmbedAuthor author) {
        this.author = author;
        return this;
    }


    public DiscordEmbed setFields(@Nullable DiscordEmbedAttributes.DiscordEmbedField[] fields) {
        this.fields = fields;
        return this;
    }

    public DiscordEmbed setField(DiscordEmbedAttributes.DiscordEmbedField field, int index){
        assert this.fields != null;
        this.fields[index]=field;
        return this;
    }

    public DiscordEmbed addField(DiscordEmbedAttributes.DiscordEmbedField field){
        List<DiscordEmbedAttributes.DiscordEmbedField> fieldList = new ArrayList<>();

        if(fields!=null) Collections.addAll(fieldList, fields);
        fieldList.add(field);


        if(fields!=null) this.fields = fieldList.toArray(this.fields); else this.fields = fieldList.toArray(new DiscordEmbedAttributes.DiscordEmbedField[0]);
        return this;
    }


    private String dateToISO(Date date){
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);
        return df.format(date);
    }

    @Override
    public JsonElement serialize(DiscordEmbed discordEmbed, Type type, JsonSerializationContext jsonSerializationContext) {


        Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm'Z'").registerTypeAdapter(Date.class, new DateAdapter()).registerTypeAdapter(DiscordEmbedAttributes.DiscordEmbedField.class, new DiscordEmbedAttributes.DiscordEmbedField()).create();
        JsonObject finalEmbed = new JsonObject();

        if(discordEmbed.getTitle()!=null)finalEmbed.addProperty("title", discordEmbed.getTitle());
        if(discordEmbed.getDescription()!=null)finalEmbed.addProperty("description", discordEmbed.getDescription());
        if(discordEmbed.getUrl()!=null)finalEmbed.addProperty("url", discordEmbed.getUrl().toString());
        if(discordEmbed.getTimestamp()!=null)finalEmbed.add("timestamp", gson.toJsonTree(discordEmbed.getTimestamp()));
        if(discordEmbed.getColor()!=null)finalEmbed.addProperty("color", discordEmbed.getColor());
        if(discordEmbed.getFooter()!=null)finalEmbed.add("footer", gson.toJsonTree(discordEmbed.getFooter()));
        if(discordEmbed.getImage()!=null)finalEmbed.add("image", gson.toJsonTree(discordEmbed.getImage()));
        if(discordEmbed.getThumbnail()!=null)finalEmbed.add("thumbnail", gson.toJsonTree(discordEmbed.getThumbnail()));
        if(discordEmbed.getVideo()!=null)finalEmbed.add("video", gson.toJsonTree(discordEmbed.getVideo()));
        if(discordEmbed.getProvider()!=null)finalEmbed.add("provider", gson.toJsonTree(discordEmbed.getProvider()));
        if(discordEmbed.getAuthor()!=null)finalEmbed.add("author", gson.toJsonTree(discordEmbed.getAuthor()));
        if(discordEmbed.getFields()!=null)finalEmbed.add("fields", gson.toJsonTree(discordEmbed.getFields()));
        return finalEmbed;
    }
}
