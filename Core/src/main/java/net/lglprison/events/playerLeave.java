package net.lglprison.events;

import net.lglprison.Main;
import net.lglprison.mongo.Database;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static net.lglprison.mongo.Player.*;

public class playerLeave implements Listener {

    private static Main plugin;

    public playerLeave(Main plugin) {
        playerLeave.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {

        Player p = event.getPlayer();

        int blocks = Database.blockbroken.get(p.getUniqueId());

        updatePlayer(p, currency.blocks, blocks);
    }

}
