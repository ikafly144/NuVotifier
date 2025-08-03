package com.vexsoftware.votifier.sponge.cmd;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.net.VotifierSession;
import com.vexsoftware.votifier.sponge.NuVotifier;
import com.vexsoftware.votifier.util.ArgsToVote;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.spongepowered.api.command.CommandExecutor;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.exception.CommandException;
import org.spongepowered.api.command.parameter.CommandContext;
import org.spongepowered.api.command.parameter.Parameter;

import java.util.Collection;

public class TestVoteCmd implements CommandExecutor {

    private final NuVotifier plugin;

    public TestVoteCmd(NuVotifier plugin) {
        this.plugin = plugin;
    }

    @Override
    public CommandResult execute(CommandContext context) throws CommandException {
        Vote v;
        try {
            Collection<? extends String> a = context.all(Parameter.key("args", String.class));
            v = ArgsToVote.parse(a.toArray(new String[0]));
        } catch (IllegalArgumentException e) {
            context.sendMessage(Component.text("Error while parsing arguments to create test vote: " + e.getMessage()).color(NamedTextColor.DARK_RED));
            context.sendMessage(Component.text("Usage hint: /testvote [username] [serviceName=?] [username=?] [address=?] [localTimestamp=?] [timestamp=?]").color(NamedTextColor.GRAY));
            return CommandResult.error(Component.text("Error while parsing arguments to create test vote: " + e.getMessage()));
        }

        plugin.onVoteReceived(v, VotifierSession.ProtocolVersion.TEST, "localhost.test");
        return CommandResult.success();
    }
}
