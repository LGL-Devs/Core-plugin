package net.lglprison.events;

import net.lglprison.Main;
import net.lglprison.mongo.Database;
import net.lglprison.mongo.Storage;
import net.lglprison.util.Chat;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

import static net.lglprison.events.blockBreak.blockbroken;

public class playerJoin implements Listener {

    private Main plugin;

    public playerJoin(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {

        Player p = event.getPlayer();

        UUID uuid = p.getUniqueId();

        if(Storage.findPlayer(uuid)) {
            Document doc = Storage.getPlayer(uuid);
            if(doc != null) {
                blockbroken.put(uuid, doc.getInteger("blocks"));
            } else {
                blockbroken.put(uuid, 0);
                Chat.console("Couldn't Load Player Data from Database");
                Chat.console("Creating new Player");
            }
        } else {
            blockbroken.put(uuid, 0);
            Chat.console("Couldn't Load Player Data from Database");
            Chat.console("Creating new Player");
        }
    }
}
