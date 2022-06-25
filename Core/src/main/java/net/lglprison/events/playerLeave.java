package net.lglprison.events;

import net.lglprison.Main;
import net.lglprison.mongo.Storage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static net.lglprison.events.blockBreak.blocksbroken;

public class playerLeave implements Listener {
    private Main plugin;
    public playerLeave(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {

        Player p = event.getPlayer();

        int blocks = blocksbroken.get(p.getUniqueId());

        Storage.setPlayer(p.getUniqueId(), Storage.choices.blocks, blocks);
    }

}
