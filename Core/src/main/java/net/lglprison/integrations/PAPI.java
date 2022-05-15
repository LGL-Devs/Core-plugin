package net.lglprison.integrations;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.lglprison.Main;
import net.lglprison.mongo.Database;
import org.bukkit.entity.Player;

public class PAPI extends PlaceholderExpansion {

    private Main plugin;

    public PAPI(Main plugin) {
        this.plugin = plugin;
    }

    public boolean persist() {
        return true;
    }

    public boolean canRegister() {
        return true;
    }

    public String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    public String getIdentifier() {
        return "core";
    }

    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    public String onPlaceholderRequest(Player p, String identifier) {

        if (p == null) {
            return "";
        }

        if (identifier.equals("blocks")) {

            int blocks = Database.blockbroken.get(p.getUniqueId());

            return Integer.toString(blocks);
        }

        return null;
    }

}
