package me.jesusmx.hubcore.manager.commands;

import me.jesusmx.hubcore.PornHub;
import me.jesusmx.hubcore.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class PornHubCommand extends Command {

    public PornHubCommand() {
        super("PornHubCommand");
        this.setUsage(CC.translate("&cUsage: /pornhub reload"));
    }

    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (commandSender.hasPermission("pornhub.reload"))
        if (strings.length == 0) {
            commandSender.sendMessage(CC.translate(""));
            commandSender.sendMessage(CC.translate(""));
            commandSender.sendMessage(CC.translate(""));
            commandSender.sendMessage(CC.translate(""));
            commandSender.sendMessage(CC.translate(""));
            commandSender.sendMessage(CC.translate(""));
            return false;
        } else if(strings.length == 1) {
            if(strings[0].equalsIgnoreCase("reload")) {
                PornHub.getInstance().getMainConfig().reload();
                PornHub.getInstance().getMainSettings().reload();
                PornHub.getInstance().getMainTablist().reload();
                PornHub.getInstance().getMainToggles().reload();
                PornHub.getInstance().getMainScoreboard().reload();
                commandSender.sendMessage(CC.translate("&aSuccessfully all PornHub Files reloaded!"));
            }
        }
        return true;
    }
}
