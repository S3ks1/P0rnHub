package me.jesusmx.hubcore.manager.listeners;

import me.jesusmx.hubcore.PornHub;
import me.jesusmx.hubcore.utils.files.ConfigFile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class WorldListener implements Listener {

    private ConfigFile toggle = PornHub.getInstance().getMainToggles();
    private ConfigFile config = PornHub.getInstance().getMainConfig();

    @EventHandler
    private void NoPickupPlayer(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    private void NoDamage(EntityDamageEvent event) {
        if (toggle.getBoolean("booleans.world.no-damage"))
        event.setCancelled(true);
    }

    @EventHandler
    private void NoDrop(PlayerDropItemEvent event) {
        if (toggle.getBoolean("booleans.world.no-drop"));
        event.setCancelled(true);
    }

    @EventHandler
    private void NoPlaceBlocks(BlockPlaceEvent event) {
        if (toggle.getBoolean("booleans.world.no-blocks"));
        event.setCancelled(true);
    }

    @EventHandler
    private void NoBreakBlocks(BlockBreakEvent event) {
        if (toggle.getBoolean("booleans.world.no-break-blocks"));
    }

    @EventHandler
    private void NoFood(FoodLevelChangeEvent event) {
        if (toggle.getBoolean("booleans.world.no-feed"));
        event.setCancelled(true);
    }

    @EventHandler
    private void NoEntitySpawn(EntitySpawnEvent event) {
        if (toggle.getBoolean("booleans.world.no-spawn-mobs"));
        event.setCancelled(true);
    }

}