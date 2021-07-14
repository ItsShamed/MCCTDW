package me.Shamed.MCCTDW.webhook.components.embed;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;

public class DiscordEmbedAttributes {
    public static class DiscordEmbedFooter{
        @NotNull private String text;
        @Nullable private String url;

        public DiscordEmbedFooter(@NotNull String text){
            if(text !=null) this.text = text; else throw new NullPointerException("You need to specify a footer name");
        }

        public DiscordEmbedFooter(@NotNull String text, @Nullable String url){
            if(text !=null) this.text = text; else throw new NullPointerException("You need to specify a footer name");
            this.url=url;
        }

        @NotNull
        public String getText() {
            return text;
        }

        @Nullable
        public String getUrl() {
            return url;
        }

        public DiscordEmbedFooter setText(@NotNull String text) {
            if(text !=null) this.text = text; else throw new NullPointerException("You need to specify a footer name");
            return this;
        }

        public DiscordEmbedFooter setUrl(@Nullable String url) {
            this.url = url;
            return this;
        }
    }

    public static class DiscordEmbedMedia{
        @NotNull private String url;
        @Nullable private String proxy_url;
        @Nullable private Long height;
        @Nullable private Long width;

        public DiscordEmbedMedia(@NotNull String url){

            if(url!=null){
                this.url=url;
            } else{
                throw new NullPointerException("You need to specify a media url.");
            }

        }

        public DiscordEmbedMedia(@NotNull String url, @Nullable String proxy_url){
            if(url!=null){
                this.url=url;
            } else{
                throw new NullPointerException("You need to specify a media url.");
            }
            this.proxy_url=proxy_url;
        }

        public DiscordEmbedMedia(@NotNull String url, @Nullable String proxy_url, @Nullable Long height, @Nullable Long width){
            if(url!=null){
                this.url=url;
            } else{
                throw new NullPointerException("You need to specify a media url.");
            }
            this.proxy_url=proxy_url;
            this.height=height;
            this.width=width;
        }

        public DiscordEmbedMedia(@NotNull String url, @Nullable Long height, @Nullable Long width){
            if(url!=null){
                this.url=url;
            } else{
                throw new NullPointerException("You need to specify a media url.");
            }
            this.height=height;
            this.width=width;
        }

        @NotNull
        public String getUrl() {
            return url;
        }

        @Nullable
        public String getProxy_url() {
            return proxy_url;
        }

        @Nullable
        public Long getHeight() {
            return height;
        }

        @Nullable
        public Long getWidth() {
            return width;
        }

        public void setUrl(@NotNull String url) {
            if(url!=null){
                this.url=url;
            } else{
                throw new NullPointerException("You need to specify a media url.");
            }
        }

        public void setUrl(@NotNull String url, @Nullable String proxy_url) {
            if(url!=null){
                this.url=url;
            } else{
                throw new NullPointerException("You need to specify a media url.");
            }
            this.proxy_url = proxy_url;
        }


        public void setHeight(@Nullable Long height) {
            this.height = height;
        }

        public void setWidth(@Nullable Long width) {
            this.width = width;
        }

        public void setResolution(@Nullable Long width, @Nullable Long height){
            this.width=width;
            this.height=height;
        }

        public static class DiscordEmbedImage extends DiscordEmbedMedia{

            public DiscordEmbedImage(@NotNull String url) {
                super(url);
            }

            public DiscordEmbedImage(@NotNull String url, @Nullable String proxy_url) {
                super(url, proxy_url);
            }

            public DiscordEmbedImage(@NotNull String url, @Nullable String proxy_url, @Nullable Long height, @Nullable Long width) {
                super(url, proxy_url, height, width);
            }

            public DiscordEmbedImage(@NotNull String url, @Nullable Long height, @Nullable Long width) {
                super(url, height, width);
            }
        }

        public static class DiscordEmbedThumbnail extends DiscordEmbedMedia{

            public DiscordEmbedThumbnail(@NotNull String url) {
                super(url);
            }

            public DiscordEmbedThumbnail(@NotNull String url, @Nullable String proxy_url) {
                super(url, proxy_url);
            }

            public DiscordEmbedThumbnail(@NotNull String url, @Nullable String proxy_url, @Nullable Long height, @Nullable Long width) {
                super(url, proxy_url, height, width);
            }

            public DiscordEmbedThumbnail(@NotNull String url, @Nullable Long height, @Nullable Long width) {
                super(url, height, width);
            }
        }

        public class DiscordEmbedVideo extends DiscordEmbedMedia{

            public DiscordEmbedVideo(@NotNull String url) {
                super(url);
            }

            public DiscordEmbedVideo(@NotNull String url, @Nullable String proxy_url) {
                super(url, proxy_url);
            }

            public DiscordEmbedVideo(@NotNull String url, @Nullable String proxy_url, @Nullable Long height, @Nullable Long width) {
                super(url, proxy_url, height, width);
            }

            public DiscordEmbedVideo(@NotNull String url, @Nullable Long height, @Nullable Long width) {
                super(url, height, width);
            }
        }
    }

    public static class DiscordEmbedProvider{
        @NotNull private String name;
        @Nullable private String url;

        public DiscordEmbedProvider(@NotNull String name){
            if(name!=null) this.name=name; else throw new NullPointerException("You need to specify a provider name");
        }

        public DiscordEmbedProvider(@NotNull String name, @Nullable String url){
            if(name!=null) this.name=name; else throw new NullPointerException("You need to specify a provider name");
            this.url=url;
        }

        @NotNull
        public String getName() {
            return name;
        }

        @Nullable
        public String getUrl() {
            return url;
        }

        public DiscordEmbedProvider setName(@NotNull String name) {
            if(name!=null) this.name = name; else throw new NullPointerException("You need to specify a provider name");
            return this;
        }

        public DiscordEmbedProvider setUrl(@Nullable String url) {
            this.url = url;
            return this;
        }
    }

    public static class DiscordEmbedAuthor{
        @NotNull private String name;
        @Nullable private String url;
        @Nullable private String icon_url;
        @Nullable private String proxy_icon_url;

        public DiscordEmbedAuthor(@NotNull String name){
            if(name!=null) this.name=name; else throw new NullPointerException("You must specify an author name.");
        }

        public DiscordEmbedAuthor(@NotNull String name, @Nullable String url){
            if(name!=null) this.name=name; else throw new NullPointerException("You must specify an author name.");
            this.url=url;
        }

        public DiscordEmbedAuthor(@NotNull String name, @Nullable String url, @Nullable String icon_url){
            if(name!=null) this.name=name; else throw new NullPointerException("You must specify an author name.");
            this.url=url;
            this.icon_url=icon_url;
        }

        public DiscordEmbedAuthor(@NotNull String name, @Nullable String url, @Nullable String icon_url, @Nullable String proxy_icon_url){
            if(name!=null) this.name=name; else throw new NullPointerException("You must specify an author name.");
            this.url=url;
            this.icon_url=icon_url;
            this.proxy_icon_url=proxy_icon_url;
        }

        @NotNull
        public String getName() {
            return name;
        }

        @Nullable
        public String getUrl() {
            return url;
        }

        @Nullable
        public String getIcon_url() {
            return icon_url;
        }

        @Nullable
        public String getProxy_icon_url() {
            return proxy_icon_url;
        }

        public DiscordEmbedAuthor setName(@NotNull String name) {
            if(name!=null) this.name = name; else throw new NullPointerException("You need to specify an author name");
            return this;
        }

        public DiscordEmbedAuthor setUrl(@Nullable String url) {
            this.url = url;
            return this;
        }

        public DiscordEmbedAuthor setIcon(@Nullable String icon_url, @Nullable String proxy_icon_url) {
            this.icon_url = icon_url;
            this.icon_url = proxy_icon_url;
            return this;
        }

        public DiscordEmbedAuthor setIcon(@Nullable String icon_url) {
            this.icon_url = icon_url;
            return this;
        }
    }

    public static class DiscordEmbedField implements JsonSerializer<DiscordEmbedField> {
        @NotNull public String title;
        @NotNull public String text;
        public Boolean inline = false;

        public DiscordEmbedField(){}

        public DiscordEmbedField(@NotNull String title, @NotNull String text){
            if(title !=null) this.title = title; else throw new NullPointerException("You must specify an field name.");
            if(text !=null) this.title = text; else throw new NullPointerException("You must specify an field value.");
        }

        public DiscordEmbedField(@NotNull String title, @NotNull String text, Boolean inline){
            if(title !=null) this.title = title; else throw new NullPointerException("You must specify an field name.");
            if(text !=null) this.text = text; else throw new NullPointerException("You must specify an field value.");
            if(inline!=null) this.inline=inline; else this.inline=false;
        }

        @NotNull
        public String getTitle() {
            return title;
        }

        @NotNull
        public String getText() {
            return text;
        }

        @Nullable
        public boolean isInline() {
            return inline;
        }

        public DiscordEmbedField setTitle(@NotNull String title) {
            this.title = title;
            return this;
        }

        public DiscordEmbedField setText(@NotNull String text) {
            this.text = text;
            return this;
        }

        @Override
        public JsonElement serialize(DiscordEmbedField field, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject jsonField = new JsonObject();
            jsonField.addProperty("name", field.getTitle());
            jsonField.addProperty("value", field.getText());
            jsonField.addProperty("inline", field.isInline());

            return jsonField;
        }
    }


}
