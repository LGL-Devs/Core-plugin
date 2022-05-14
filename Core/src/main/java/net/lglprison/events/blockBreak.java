package net.lglprison.events;

import net.lglprison.Main;
import net.lglprison.mongo.Database;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class blockBreak implements Listener {
    private Main plugin;

    public blockBreak(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent e) {
        Player p = e.getPlayer();

        int value = Database.blockbroken.get(p.getUniqueId());

        Database.blockbroken.put(p.getUniqueId(), value++);
        return;
    }
}
