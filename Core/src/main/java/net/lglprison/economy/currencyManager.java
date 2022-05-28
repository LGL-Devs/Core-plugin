package net.lglprison.economy;

import net.lglprison.Main;
import net.lglprison.mongo.Storage;
import net.lglprison.util.Chat;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class currencyManager implements Listener {
    private static Main plugin;
    public enum currency { silvers, doubloons, rubies }
    public static HashMap<UUID, Integer> silver = new HashMap<UUID, Integer>();
    public static HashMap<UUID, Integer> rubie = new HashMap<UUID, Integer>();
    public static HashMap<UUID, Integer> doubloon = new HashMap<UUID, Integer>();

    public currencyManager(Main plugin) {
        currencyManager.plugin = plugin;
        Bukkit.getPluginManager().registerEvents( this, plugin);
    }

    public static void addCurrencyToPlayer(Player p, String c, int amount) {
        if(Objects.equals(c, "silvers")) {
            silver.put(p.getUniqueId(), silver.get(p.getUniqueId()) + amount);
        }
        if(Objects.equals(c, "rubies")) {
            rubie.put(p.getUniqueId(), rubie.get(p.getUniqueId()) + amount);
        }
        if(Objects.equals(c, "doubloons")) {
            doubloon.put(p.getUniqueId(), doubloon.get(p.getUniqueId()) + amount);
        }
    }

    public static void removeCurrencyFromPlayer(Player p, String c, int amount) {
        if(Objects.equals(c, "silvers")) {
            silver.put(p.getUniqueId(), silver.get(p.getUniqueId()) - amount);
        }
        if(Objects.equals(c, "rubies")) {
            rubie.put(p.getUniqueId(), rubie.get(p.getUniqueId()) - amount);
        }
        if(Objects.equals(c, "doubloons")) {
            doubloon.put(p.getUniqueId(), doubloon.get(p.getUniqueId()) - amount);
        }
    }

    public static void setPlayerCurrency(Player p, String c, int amount) {
        if(Objects.equals(c, "silvers")) {
            silver.put(p.getUniqueId(), amount);
        }
        if(Objects.equals(c, "rubies")) {
            rubie.put(p.getUniqueId(), amount);
        }
        if(Objects.equals(c, "doubloons")) {
            doubloon.put(p.getUniqueId(), amount);
        }
    }

    public static int getPlayerCurrency(Player p, String c) {
        if(Objects.equals(c, "silvers")) {
            return silver.get(p.getUniqueId());
        }
        if(Objects.equals(c, "rubies")) {
            return rubie.get(p.getUniqueId());
        }
        if(Objects.equals(c, "doubloons")) {
            return doubloon.get(p.getUniqueId());
        }
        return 0;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {

        Player p = event.getPlayer();

        if(Storage.findPlayer(p.getUniqueId())) {
            Document doc = Storage.getPlayer(p.getUniqueId());
            if(doc != null) {
                setPlayerCurrency(p, currency.silvers.toString(), doc.getInteger("silvers"));
                setPlayerCurrency(p, currency.rubies.toString(), doc.getInteger("rubies"));
                setPlayerCurrency(p, currency.doubloons.toString(), doc.getInteger("doubloons"));
            } else {
                setPlayerCurrency(p, currency.silvers.toString(), 0);
                setPlayerCurrency(p, currency.rubies.toString(), 0);
                setPlayerCurrency(p, currency.doubloons.toString(), 0);
            }
        } else {
            setPlayerCurrency(p, currency.silvers.toString(), 0);
            setPlayerCurrency(p, currency.rubies.toString(), 0);
            setPlayerCurrency(p, currency.doubloons.toString(), 0);
        }
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        // GET VALUE FROM HASHMAP AND SAVE TO DATABASE
        int silvers = getPlayerCurrency(p,currency.silvers.toString());
        int rubies = getPlayerCurrency(p,currency.rubies.toString());
        int doubloons = getPlayerCurrency(p,currency.doubloons.toString());
        Storage.setPlayer(p.getUniqueId(), Storage.choices.silvers, silvers);
        Storage.setPlayer(p.getUniqueId(), Storage.choices.rubies, rubies);
        Storage.setPlayer(p.getUniqueId(), Storage.choices.doubloons, doubloons);
        Chat.console(p.getName() + " Data Saved");
    }

    public static void disable() {

        try {
            for (UUID s : silver.keySet()) {
                int deposit = silver.get(s);
                Storage.setPlayer(s, Storage.choices.silvers, deposit);
            }

            for (UUID r : rubie.keySet()) {
                int deposit = rubie.get(r);
                Storage.setPlayer(r, Storage.choices.rubies, deposit);
            }

            for (UUID d : doubloon.keySet()) {
                int deposit = doubloon.get(d);
                Storage.setPlayer(d, Storage.choices.doubloons, deposit);
            }
        } finally {
            Chat.console("Economy Saved");
        }

    }

}
