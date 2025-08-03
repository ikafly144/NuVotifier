package com.vexsoftware.votifier.cmd;

import com.vexsoftware.votifier.NuVotifierBukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class NVReloadCmd implements CommandExecutor {

    private final NuVotifierBukkit plugin;

    public NVReloadCmd(NuVotifierBukkit plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        if (sender.hasPermission("nuvotifier.reload")) {
            sender.sendRichMessage("<gray>" + "Reloading NuVotifier...");
            if (plugin.reload()) {
                sender.sendRichMessage("<dark_green>" + "NuVotifier has been reloaded!");
            } else {
                sender.sendRichMessage("<dark_red>" + "Looks like there was a problem reloading NuVotifier, check the console!");
            }
        } else {
            sender.sendRichMessage("<dark_red>" + "You do not have permission to do this!");
        }
        return true;
    }
}
