package net.lglprison.commands;

import net.lglprison.Main;
import net.lglprison.economy.currencyManager;
import net.lglprison.util.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class economy implements CommandExecutor {

    private Main plugin;

    public economy(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("economy").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Chat.Color(Chat.prefix + "&cOnly players Can execute this command"));
            return true;
        }

        Player p = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("economy")) {

            if(args.length == 0) {

                if(p.hasPermission("core.admin")) {
                    Chat.send(p,
                            "&f==============================\n"
                                    + "&aCore Economy &cHelp\n"
                                    + "&a/econ me: &6Shows your balance\n"
                                    + "&a/econ pay <USERNAME> <amount> <silvers/doubloons>: &6Pays a player\n"
                                    + "&a/econ add <USERNAME> <amount> <currency>: &6Adds amount to player\n"
                                    + "&a/econ remove <USERNAME> <amount> <currency>: &6Removes amount from player\n"
                                    + "&a/econ set <USERNAME> <amount> <currency>: &6sets players balance to amount\n"
                                    + "&f=============================="
                    );
                    return true;
                }

                Chat.send(p,
                        "&f==============================\n"
                                + "&aCore Economy &cHelp\n"
                                + "&a/econ me: &6Shows your balance\n"
                                + "&a/econ pay <USERNAME> <amount> <silvers/doubloons>: &6Pays a player\n"
                                + "&f=============================="
                );

                return true;

            }  else if(args.length >= 1) {

                if(args[0].equalsIgnoreCase("me")) {

                    Chat.send(p, "&fPrison Account: ");
                    Chat.send(p, "&fSlivers: " + currencyManager.getPlayerCurrency(p, currencyManager.currency.silvers.toString()));
                    Chat.send(p, "&fDoubloons: " + currencyManager.getPlayerCurrency(p, currencyManager.currency.doubloons.toString()));
                    Chat.send(p, "&fRubies: " + currencyManager.getPlayerCurrency(p, currencyManager.currency.rubies.toString()));

                    return true;

                } if(args[0].equalsIgnoreCase("pay")) {

                    if(args[1] == null && args[2] == null && args[3] == null) {
                        Chat.send(p,"&a/econ add <USERNAME> <amount> <currency>: &6Adds amount to player");
                    } else {

                        if(!Objects.equals(args[3], "silvers") && !Objects.equals(args[3], "doubloons")) {
                            Chat.send(p,"&4 You can only pay people with Silvers and Doubloons");
                            return true;
                        }

                        if(currencyManager.getPlayerCurrency(p, args[3]) >= Integer.parseInt(args[2])) {
                            Player target = Bukkit.getPlayer(args[1]);

                            currencyManager.addCurrencyToPlayer(target, args[3], Integer.parseInt(args[2]));
                            currencyManager.removeCurrencyFromPlayer(p, args[3], Integer.parseInt(args[2]));

                            Chat.send(p,"&aYou have paid " + args[1] + " " + args[2] + " " + args[3]);
                        } else {
                            Chat.send(p,"&4You don't have enough to pay " + args[1] + " " + args[2] + " " + args[3]);
                        }
                    }
                    return true;

                } else if(args[0].equalsIgnoreCase("add")){

                    if(p.hasPermission("core.admin")) {

                        if(args[1] == null && args[2] == null && args[3] == null) {
                            Chat.send(p,"&a/econ add <USERNAME> <amount> <currency>: &6Removes amount from player");
                            return true;
                        }

                        Player target = Bukkit.getPlayer(args[1]);

                        currencyManager.addCurrencyToPlayer(target, args[3], Integer.parseInt(args[2]));

                        Chat.send(p,"&aAdded " + args[2] + " " + args[3] + " to " + args[1]);

                    } else {
                        Chat.send(p,"&cYou don't have the permissions to use this command");
                    }
                    return true;

                } else if(args[0].equalsIgnoreCase("remove")) {

                    if(p.hasPermission("core.admin")) {

                        if(args[1] == null && args[2] == null && args[3] == null) {
                            Chat.send(p,"&a/econ remove <USERNAME> <amount> <currency>: &6Removes amount from player");
                            return true;
                        }

                        Player target = Bukkit.getPlayer(args[1]);

                        currencyManager.removeCurrencyFromPlayer(target, args[3], Integer.parseInt(args[2]));

                        Chat.send(p,"&cRemoved " + args[2] + " " + args[3] + " to " + args[1]);

                    } else {
                        Chat.send(p,"&cYou don't have the permissions to use this command");
                    }
                    return true;

                } else if(args[0].equalsIgnoreCase("set")) {

                    if(p.hasPermission("core.admin")) {

                        if(args[1] == null && args[2] == null && args[3] == null) {
                            Chat.send(p,"&a/econ add <USERNAME> <amount> <currency>: &6Removes amount from player");
                            return true;
                        }

                        Player target = Bukkit.getPlayer(args[1]);

                        currencyManager.setPlayerCurrency(target, args[3], Integer.parseInt(args[2]));

                        Chat.send(p,"&aAdded " + args[2] + " " + args[3] + " to " + args[1]);

                    } else {
                        Chat.send(p,"&cYou don't have the permissions to use this command");
                    }
                    return true;

                }
            }
        }

        return false;
    }

}


