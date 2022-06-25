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

public class economy implements CommandExecutor {

    private Main plugin;

    public economy(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("economy").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

        if(!(sender instanceof Player)){
            // Chat.send(sender, "&cOnly players Can execute this command");

            if(cmd.getName().equalsIgnoreCase("economy")) {

                if(args.length == 0) {
                    Chat.send(sender,
                            "&f==============================\n"
                                    + "&aCore Economy &cHelp\n"
                                    + "&a/econ add <USERNAME> <amount> <currency>: &6Adds amount to player\n"
                                    + "&a/econ remove <USERNAME> <amount> <currency>: &6Removes amount from player\n"
                                    + "&a/econ set <USERNAME> <amount> <currency>: &6sets players balance to amount\n"
                                    + "&f=============================="
                    );
                    return true;
                } else {
                    if(args[0].equalsIgnoreCase("add")) {

                        if(args[1] == null && args[2] == null && args[3] == null) {
                            Chat.send(sender,"&a/econ add <USERNAME> <amount> <currency>: &6Adds amount to player");
                            return true;
                        }

                        Player target = Bukkit.getPlayer(args[1]);

                        if(target != null) {
                            currencyManager.addCurrencyToPlayer(target, args[3], Integer.parseInt(args[2]));

                            Chat.send(sender,"&aAdded " + args[2] + " " + args[3] + " to " + args[1]);
                        } else {
                            Chat.send(sender,"&4Couldn't find player " + args[1] + " on the Server.");
                        }

                        return true;
                    } else if(args[0].equalsIgnoreCase("remove")) {

                        if(args[1] == null && args[2] == null && args[3] == null) {
                            Chat.send(sender,"&a/econ remove <USERNAME> <amount> <currency>: &6Removes amount from player");
                            return true;
                        }

                        Player target = Bukkit.getPlayer(args[1]);

                        if(target != null) {
                            currencyManager.removeCurrencyFromPlayer(target, args[3], Integer.parseInt(args[2]));

                            Chat.send(sender,"&cRemoved " + args[2] + " " + args[3] + " to " + args[1]);
                        } else {
                            Chat.send(sender,"&4Couldn't find player " + args[1] + " on the Server.");
                        }

                        return true;
                    } else if(args[0].equalsIgnoreCase("set")) {

                        if(args[1] == null && args[2] == null && args[3] == null) {
                            Chat.send(sender,"&a/econ set <USERNAME> <amount> <currency>: &6sets players balance to amount");
                            return true;
                        }

                        Player target = Bukkit.getPlayer(args[1]);

                        if(target != null) {
                            currencyManager.setPlayerCurrency(target, args[3], Integer.parseInt(args[2]));

                            Chat.send(sender,"&aSet " + args[2] + " " + args[3] + " to " + args[1]);
                        } else {
                            Chat.send(sender,"&4Couldn't find player " + args[1] + " on the Server.");
                        }

                        return true;
                    }
                }
            }
        }

        Player p = (Player) sender;

        if(!p.hasPermission("core.admin")) {
            Chat.send(p,"&cYou don't have the permissions to use this command");
            return true;
        }

        if(cmd.getName().equalsIgnoreCase("economy")) {

            if(args.length == 0) {
                Chat.send(p,
                        "&f==============================\n"
                                + "&aCore Economy &cHelp\n"
                                + "&a/econ add <USERNAME> <amount> <currency>: &6Adds amount to player\n"
                                + "&a/econ remove <USERNAME> <amount> <currency>: &6Removes amount from player\n"
                                + "&a/econ set <USERNAME> <amount> <currency>: &6sets players balance to amount\n"
                                + "&f=============================="
                );
                return true;
            } else {
                if(args[0].equalsIgnoreCase("add")) {

                    if(args[1] == null && args[2] == null && args[3] == null) {
                        Chat.send(p,"&a/econ add <USERNAME> <amount> <currency>: &6Adds amount to player");
                        return true;
                    }

                    Player target = Bukkit.getPlayer(args[1]);

                    if(target != null) {
                        currencyManager.addCurrencyToPlayer(target, args[3], Integer.parseInt(args[2]));

                        Chat.send(p,"&aAdded " + args[2] + " " + args[3] + " to " + args[1]);
                    } else {
                        Chat.send(p,"&4Couldn't find player " + args[1] + " on the Server.");
                    }

                    return true;
                } else if(args[0].equalsIgnoreCase("remove")) {

                    if(args[1] == null && args[2] == null && args[3] == null) {
                        Chat.send(p,"&a/econ remove <USERNAME> <amount> <currency>: &6Removes amount from player");
                        return true;
                    }

                    Player target = Bukkit.getPlayer(args[1]);

                    if(target != null) {
                        currencyManager.removeCurrencyFromPlayer(target, args[3], Integer.parseInt(args[2]));

                        Chat.send(p,"&cRemoved " + args[2] + " " + args[3] + " to " + args[1]);
                    } else {
                        Chat.send(p,"&4Couldn't find player " + args[1] + " on the Server.");
                    }

                    return true;
                } else if(args[0].equalsIgnoreCase("set")) {

                    if(args[1] == null && args[2] == null && args[3] == null) {
                        Chat.send(p,"&a/econ set <USERNAME> <amount> <currency>: &6sets players balance to amount");
                        return true;
                    }

                    Player target = Bukkit.getPlayer(args[1]);

                    if(target != null) {
                        currencyManager.setPlayerCurrency(target, args[3], Integer.parseInt(args[2]));

                        Chat.send(p,"&aSet " + args[2] + " " + args[3] + " to " + args[1]);
                    } else {
                        Chat.send(p,"&4Couldn't find player " + args[1] + " on the Server.");
                    }

                    return true;
                }
            }
        }

        return false;
    }
}
