package net.lglprison.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class Chat {

    static Logger log = Bukkit.getServer().getLogger();

    public static String prefix = "&f[&aCore&f] ";

    public static void send(Player p, String input) {
        p.sendMessage(Color(Chat.prefix + input));
    }
    public static String Color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
    public static void console(String input) {
        log.info(Color(prefix + " " + input));
    }
}
