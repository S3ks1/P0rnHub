package me.jesusmx.hubcore.manager.commands.media;

import me.jesusmx.hubcore.PornHub;
import me.jesusmx.hubcore.utils.files.ConfigFile;
import me.jesusmx.hubcore.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class TeamSpeakCommand extends Command {

    private ConfigFile toggle = PornHub.getInstance().getMainToggles();
    private ConfigFile config = PornHub.getInstance().getMainConfig();

    public TeamSpeakCommand() {
        super("teamspeak");
        this.setAliases(Arrays.asList("ts3", "ts"));
        this.setUsage(CC.translate("&cUsage: /teamspeak"));
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (toggle.getBoolean("booleans.commands.teamspeak")) {
            config.getStringList("commands.teamspeak").stream().map(CC::translate).forEach(sender::sendMessage);
        }
        return true;
    }
}
