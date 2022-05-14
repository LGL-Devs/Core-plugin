package net.lglprison.events;

import net.lglprison.Main;
import net.lglprison.mongo.Database;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static net.lglprison.mongo.Player.*;

public class playerJoin implements Listener {

    private static Main plugin;

    public playerJoin(Main plugin) {
        playerJoin.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {

        Player p = event.getPlayer();

        if(findPlayer(p)) {
            Document doc = getPlayer(p);

           Database.blockbroken.put(p.getUniqueId(), doc.getInteger("blocks"));
           return;
        }
        return;
    }
}
