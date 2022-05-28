package net.lglprison.integrations;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.lglprison.Main;
import net.lglprison.economy.currencyManager;
import org.bukkit.entity.Player;

import static net.lglprison.events.blockBreak.blockbroken;

public class PAPI extends PlaceholderExpansion {
    private Main plugin;

    public PAPI(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier() {
        return "core";
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {

        if (p == null) {
            return "";
        }

        if (identifier.equals("blocks")) {

            int blocks = blockbroken.get(p.getUniqueId());

            return Integer.toString(blocks);
        }

        if (identifier.equals("silvers")) {

            int silvers = currencyManager.getPlayerCurrency(p, currencyManager.currency.silvers.toString());

            return Integer.toString(silvers);
        }

        if (identifier.equals("rubies")) {

            int rubies = currencyManager.getPlayerCurrency(p, currencyManager.currency.rubies.toString());

            return Integer.toString(rubies);
        }

        if (identifier.equals("doubloons")) {

            int doubloons = currencyManager.getPlayerCurrency(p, currencyManager.currency.doubloons.toString());

            return Integer.toString(doubloons);
        }

        return null;
    }

}
