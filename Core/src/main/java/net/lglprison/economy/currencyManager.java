package net.lglprison.economy;

import net.lglprison.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class currencyManager implements Listener {
    private static Main plugin;
    public enum currency { silvers, doubloons, rubies, blocks }
    private static HashMap<UUID, Integer> silver = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> rubie = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> doubloon = new HashMap<UUID, Integer>();

    public currencyManager(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents( this, plugin);
    }

    public static void enable() {

    }

    public void addCurrencyToPlayer(Player p, currency c, int amount) {

        if(c.toString() == "silver") {
            silver.put(p.getUniqueId(), silver.get(p.getUniqueId()) + amount);
            return;
        }

        if(c.toString() == "rubie") {
            rubie.put(p.getUniqueId(), rubie.get(p.getUniqueId()) + amount);
            return;
        }

        if(c.toString() == "doubloon") {
            doubloon.put(p.getUniqueId(), doubloon.get(p.getUniqueId()) + amount);
            return;
        }

    }

    public void removeCurrencyFromPlayer(Player p, currency c, int amount) {

        if(c.toString() == "silver") {
            silver.put(p.getUniqueId(), silver.get(p.getUniqueId()) - amount);
            return;
        }

        if(c.toString() == "rubie") {
            rubie.put(p.getUniqueId(), rubie.get(p.getUniqueId()) - amount);
            return;
        }

        if(c.toString() == "doubloon") {
            doubloon.put(p.getUniqueId(), doubloon.get(p.getUniqueId()) - amount);
            return;
        }

    }

    public void setPlayerCurrency(Player p, currency c, int amount) {

        if(c.toString() == "silver") {
            silver.put(p.getUniqueId(), amount);
            return;
        }

        if(c.toString() == "rubie") {
            rubie.put(p.getUniqueId(), amount);
            return;
        }

        if(c.toString() == "doubloon") {
            doubloon.put(p.getUniqueId(), amount);
            return;
        }

    }

    public int getPlayerCurrency(Player p, currency c) {

        if(c.toString() == "silver") {
            return silver.get(p.getUniqueId());
        }

        if(c.toString() == "rubie") {
            return rubie.get(p.getUniqueId());
        }

        if(c.toString() == "doubloon") {
            return doubloon.get(p.getUniqueId());
        }

        return 0;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {

        Player p = event.getPlayer();

        // GET VALUES FROM DATABASE AND SET

        silver.put(p.getUniqueId(), 0);
        rubie.put(p.getUniqueId(), 0);
        doubloon.put(p.getUniqueId(), 0);

    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {

        Player p = event.getPlayer();

        // GET VALUE FROM HASHMAP AND SAVE TO DATABASE

        int silvers = silver.get(p.getUniqueId());
        int rubies = rubie.get(p.getUniqueId());
        int doubloons = doubloon.get(p.getUniqueId());

    }

    public static void disable() {

        for (Map.Entry s : silver.entrySet()) {

        }

        for (Map.Entry r : rubie.entrySet()) {

        }

        for (Map.Entry d : doubloon.entrySet()) {

        }

    }

}
