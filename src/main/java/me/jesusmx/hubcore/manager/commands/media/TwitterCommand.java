package me.jesusmx.hubcore.manager.commands.media;

import me.jesusmx.hubcore.PornHub;
import me.jesusmx.hubcore.utils.files.ConfigFile;
import me.jesusmx.hubcore.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class TwitterCommand extends Command {

    private ConfigFile toggle = PornHub.getInstance().getMainToggles();
    private ConfigFile config = PornHub.getInstance().getMainConfig();

    public TwitterCommand() {
        super("twitter");
        this.setAliases(Arrays.asList("tw"));
        this.setUsage(CC.translate("&cUsage: /twitter"));
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (toggle.getBoolean("booleans.commands.twitter")) {
            config.getStringList("commands.twitter").stream().map(CC::translate).forEach(sender::sendMessage);
        }
        return true;
    }
}
