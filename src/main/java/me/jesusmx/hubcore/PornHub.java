package me.jesusmx.hubcore;

import lombok.Getter;
import lombok.SneakyThrows;
import me.jesusmx.hubcore.manager.commands.PornHubCommand;
import me.jesusmx.hubcore.manager.commands.media.DiscordCommand;
import me.jesusmx.hubcore.manager.commands.media.TeamSpeakCommand;
import me.jesusmx.hubcore.manager.commands.media.TelegramCommand;
import me.jesusmx.hubcore.manager.commands.media.TwitterCommand;
import me.jesusmx.hubcore.manager.listeners.WorldListener;
import me.jesusmx.hubcore.provider.ScoreboardAdapter;
import me.jesusmx.hubcore.provider.TablistProvider;
import me.jesusmx.hubcore.utils.files.ConfigFile;
import me.jesusmx.hubcore.utils.CC;
import me.jesusmx.hubcore.utils.scoreboard.Frame;
import me.jesusmx.hubcore.utils.tablist.shared.TabHandler;
import me.jesusmx.hubcore.utils.tablist.v1_7_R4.v1_7_R4TabAdapter;
import me.jesusmx.hubcore.utils.tablist.v1_8_R3.v1_8_R3TabAdapter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Getter
public final class PornHub extends JavaPlugin {

    @Getter private static PornHub instance;
    private ConfigFile mainConfig;
    private ConfigFile mainToggles;
    private ConfigFile mainSettings;
    private ConfigFile mainTablist;
    private ConfigFile mainScoreboard;

    @Override
    public void onEnable() {
        PornHub.instance = this;
        this.saveDefaultConfig();

        this.mainConfig = new ConfigFile(this, "config");
        this.mainToggles = new ConfigFile(this, "toggles");
        this.mainSettings = new ConfigFile(this, "settings");
        this.mainTablist = new ConfigFile(this, "tablist");
        this.mainScoreboard = new ConfigFile(this, "scoreboard");

        if (mainSettings.getBoolean("settings.optimized-world")) {
            for (World world : Bukkit.getWorlds()) {
                world.setTime(0L);
                world.setStorm(false);
                world.setGameRuleValue("doDaylightCycle", "false");
                world.setGameRuleValue("doMobSpawning", "false");
                this.getServer().getConsoleSender().sendMessage(CC.translate("&7[&bPornHub&7] &aThe world has been optimized."));
            }
        }

        this.registerCommands();
        this.getServer().getConsoleSender().sendMessage(CC.translate("&7[&bPornHub&7] &aCommands loaded."));

        this.registerListeners();
        this.getServer().getConsoleSender().sendMessage(CC.translate("&7[&bPornHub&7] &aListeners loaded."));

        if (mainToggles.getBoolean("booleans.features.scoreboard")) {
            new Frame(this, new ScoreboardAdapter());
            this.getServer().getConsoleSender().sendMessage(CC.translate("&7[&bPornHub&7] &aScoreboard loaded."));
        }

        if (mainToggles.getBoolean("booleans.features.tablist")) {
            if (Bukkit.getVersion().contains("1.7")) {
                new TabHandler(new v1_7_R4TabAdapter(), new TablistProvider(), this, 20L);
            }
            if (Bukkit.getVersion().contains("1.8")) {
                new TabHandler(new v1_8_R3TabAdapter(), new TablistProvider(), this, 20L);
            }
        }
    }

    private void registerCommands() {
        Arrays.asList(new DiscordCommand(), new TeamSpeakCommand(), new TelegramCommand(), new TwitterCommand(), new PornHubCommand()).forEach(this::registerCommand);
    }

    private void registerListeners() {
        Arrays.asList(new WorldListener()).forEach(listener -> this.getServer().getPluginManager().registerEvents(listener, this));
    }

    @SneakyThrows
    public void registerCommand(Command cmd) {
        Field field = getServer().getClass().getDeclaredField("commandMap");
        field.setAccessible(true);
        CommandMap commandMap = (CommandMap) field.get(getServer());
        commandMap.register(this.getName(), cmd);
    }

    public Collection<? extends Player> getOnlinePlayers() {
        Collection<Player> collection = new ArrayList<>();
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            collection.add(player);
        }
        return collection;
    }

}
