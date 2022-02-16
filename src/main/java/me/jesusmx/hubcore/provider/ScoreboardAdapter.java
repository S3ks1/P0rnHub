package me.jesusmx.hubcore.provider;

import me.clip.placeholderapi.PlaceholderAPI;
import me.jesusmx.hubcore.PornHub;
import me.jesusmx.hubcore.utils.CC;
import me.jesusmx.hubcore.utils.files.ConfigFile;
import me.jesusmx.hubcore.utils.scoreboard.FrameAdapter;
import me.joeleoli.portal.shared.queue.Queue;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardAdapter implements FrameAdapter {

	private ConfigFile config = PornHub.getInstance().getMainScoreboard();

	/*	CODIGO DE MIERDA HECHO EN 1M  */
    /*  APRENDAN MINIMO A HACER ESTO SKIDDERS */

	@Override
	public String getTitle(Player player) {
		return config.getString(CC.translate("scoreboard.title"));
	}

	@Override
	public List<String> getLines(Player player) {
		List<String> toReturn = new ArrayList<>();

		Queue queue = Queue.getByPlayer(player.getUniqueId());

		if (queue == null) {
			for (String line : config.getStringList("scoreboard.normal")) {
				line = line.replaceAll("%arrowthing%", "\u00bb");
				line = PlaceholderAPI.setPlaceholders(player, line);
				toReturn.add(line);
			}

		} else {
			for (String line : config.getStringList("scoreboard.queue")) {
				line = line.replaceAll("%arrowthing%", "\u00bb");
				line = line.replaceAll("%queue%", queue.getName());
				line = line.replaceAll("%place%", String.valueOf(queue.getPosition(player.getUniqueId())));
				line = line.replaceAll("%total%", String.valueOf(queue.getPlayers().size()));
				line = PlaceholderAPI.setPlaceholders(player, line);
				toReturn.add(line);
			}
		}
		return toReturn;
	}
}
