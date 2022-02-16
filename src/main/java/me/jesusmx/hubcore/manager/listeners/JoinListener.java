package me.jesusmx.hubcore.manager.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import me.jesusmx.hubcore.PornHub;
import me.jesusmx.hubcore.utils.CC;
import me.jesusmx.hubcore.utils.files.ConfigFile;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private ConfigFile toggle = PornHub.getInstance().getMainToggles();
    private ConfigFile config = PornHub.getInstance().getMainConfig();

    @EventHandler
    private void JoinListener(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);

        if (toggle.getBoolean("booleans.listeners.join-message")) {
            config.getStringList("join-message.lines").stream()
                    .map(line -> PlaceholderAPI.setPlaceholders(player, line))
                    .map(line -> line.replace("%player%", player.getName()))
                    .forEach(m -> player.sendMessage(CC.translate(m)));
        }

        if (toggle.getBoolean("booleans.listeners.sound")) {
            player.playSound(player.getLocation(), Sound.valueOf(config.getString("join-sound.sound").toUpperCase()), 1.0F, 1.0F);
        }


    }
}
