package net.lglprison.events;

import net.lglprison.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashMap;
import java.util.UUID;

public class blockBreak implements Listener {
    private static Main plugin;

    public static HashMap<UUID, Integer> blocksbroken = new HashMap<UUID, Integer>();

    public blockBreak(Main plugin) {
        blockBreak.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent e) {
        Player p = e.getPlayer();

        blocksbroken.put(p.getUniqueId(), blocksbroken.get(p.getUniqueId()) + 1);
    }
}
