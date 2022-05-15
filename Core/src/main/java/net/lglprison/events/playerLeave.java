package net.lglprison.events;

import net.lglprison.Main;
import net.lglprison.mongo.Database;
import net.lglprison.mongo.Storage;
import net.lglprison.util.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class playerLeave implements Listener {

    private Main plugin;

    public playerLeave(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {

        Player p = event.getPlayer();

        int blocks = Database.blockbroken.get(p.getUniqueId());

        Storage.setPlayer(p.getUniqueId(), Storage.data.blocks, blocks);
    }

}
