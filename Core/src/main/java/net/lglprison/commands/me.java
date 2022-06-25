package net.lglprison.commands;

import net.lglprison.Main;
import net.lglprison.economy.currencyManager;
import net.lglprison.util.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class me implements CommandExecutor {
    private Main plugin;

    public me(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("me").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

        if(!(sender instanceof Player)){
            Chat.send(sender, "&cOnly players Can execute this command");
            return true;
        }

        Player p = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("me")) {
            if(args.length == 0) {
                Chat.send(p, "&fPrison Account: ");
                Chat.send(p, "&fSlivers: " + currencyManager.getPlayerCurrency(p, currencyManager.currency.silvers.toString()));
                Chat.send(p, "&fDoubloons: " + currencyManager.getPlayerCurrency(p, currencyManager.currency.doubloons.toString()));
                Chat.send(p, "&fRubies: " + currencyManager.getPlayerCurrency(p, currencyManager.currency.rubies.toString()));
                return true;
            }
        }

        return false;
    }
}
