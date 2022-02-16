package me.jesusmx.hubcore.manager.commands.media;

import me.jesusmx.hubcore.PornHub;
import me.jesusmx.hubcore.utils.files.ConfigFile;
import me.jesusmx.hubcore.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class TelegramCommand extends Command {

    private ConfigFile toggle = PornHub.getInstance().getMainToggles();
    private ConfigFile config = PornHub.getInstance().getMainConfig();

    public TelegramCommand() {
        super("telegram");
        this.setUsage(CC.translate("&cUsage: /telegram"));
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (toggle.getBoolean("booleans.commands.telegram")) {
            config.getStringList("commands.telegram").stream().map(CC::translate).forEach(sender::sendMessage);
        }
        return true;
    }
}
