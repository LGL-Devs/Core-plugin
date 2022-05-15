package net.lglprison.events;

import net.lglprison.Main;
import net.lglprison.mongo.Database;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class blockBreak implements Listener {
    private static Main plugin;

    public blockBreak(Main plugin) {
        blockBreak.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent e) {
        Player p = e.getPlayer();

        Database.blockbroken.put(p.getUniqueId(), Database.blockbroken.get(p.getUniqueId()) + 1);
    }
}
