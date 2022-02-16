package me.jesusmx.hubcore.provider;

import me.clip.placeholderapi.PlaceholderAPI;
import me.jesusmx.hubcore.PornHub;
import me.jesusmx.hubcore.utils.CC;
import me.jesusmx.hubcore.utils.tablist.shared.entry.TabElement;
import me.jesusmx.hubcore.utils.tablist.shared.entry.TabElementHandler;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class TablistProvider implements TabElementHandler {

    @Override
    public TabElement getElement(Player player) {
        TabElement element = new TabElement();
        element.setHeader(CC.translate(""));
        element.setFooter(CC.translate(""));

        List list = Arrays.asList((Object[])new String[]{"left", "middle", "right", "far-right"});
        for (int i = 0; i < 4; ++i) {
            String s = (String)list.get(i);
            for (int l = 0; l < 20; ++l) {
                String str = PlaceholderAPI.setPlaceholders(player, PornHub.getInstance().getMainTablist().getString(String.valueOf(new StringBuilder()
                                .append("tab.")
                                .append(s)
                                .append(".")
                                .append(l + 1)))
/*
                                .replace("%rank%", rankdata.getColor() + rankdata.getDisplayName())
*/
                                .replace("%player%", player.getDisplayName())
                                .replace("%arrowthing%", "\u00bb"));

                element.add(i, l, str);
            }
        }
        return element;
    }
}