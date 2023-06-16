# Minecraft Chat To Discord Webhook
A simple and easy bridge plugin between your Minecraft chat
and Discord server using webhooks

## Usage

### Configuration

#### In-game

Use the `/dwurl [webhook URL]` command to set the URL pointing to your
Discord-compatible webhook.

#### Configuration file

The `config.yml` configuration file only consists of an `url` variable.
It should look like this:

```yml
url: "YOUR URL"
```

### Other commands

If you ever need to reload the plugin for some reason
(maybe the plugin crashed, which is very likely), use the `/dwreload`
command.

>**Warning**
> 
> The use of third-party reloaders is not recommended. It might not
> play well with the plugin load cycle. Use the `/dwreload` command
> if you want to reload the plugin.

## License

This plugin is licensed under the GPLv3 license.
Please refer to the [LICENSE](/LICENSE) for more information.