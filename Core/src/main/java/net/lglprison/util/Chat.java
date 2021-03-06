package net.lglprison.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

import static net.lglprison.Main.config;

public class Chat {
    static Logger log = Bukkit.getServer().getLogger();
    public static String prefix = config.getString("prefix");
    public static void send(Player p, String input) {
        p.sendMessage(Color(prefix + input));
    }
    public static void send(CommandSender sender, String input) {
        sender.sendMessage(Color(prefix + input));
    }
    public static String Color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
    public static void console(String input) {
        log.info(Color(prefix + " " + input));
    }
}
