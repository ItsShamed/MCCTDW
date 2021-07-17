package me.Shamed.MCCTDW.commands;

import me.Shamed.MCCTDW.MCCTDW;
import me.Shamed.MCCTDW.webhook.components.formatted.ChatMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReloadCommandExecutor implements CommandExecutor {
    private MCCTDW plugin;

    public ReloadCommandExecutor(MCCTDW plugin){
        this.plugin=plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(commandSender instanceof Player){
            if(!((Player) commandSender).hasPermission("mcctdw.use")){
                ((Player) commandSender).sendMessage(ChatColor.translateAlternateColorCodes('&', "&l&cYou are not allowed to execute this command."));
                return false;
            }
        }

        if(label.equalsIgnoreCase("dwreload")){
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&bMCCTDW&8] &rReloading plugin..."));
            plugin.reload();
            return true;


        }

        return false;
    }
}
