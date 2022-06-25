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

public class pay implements CommandExecutor {

    private Main plugin;

    public pay(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("pay").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

        if(!(sender instanceof Player)){
            Chat.send(sender, "&cOnly players Can execute this command");
            return true;
        }

        Player p = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("pay")) {

            if(args.length == 0) {

                Chat.send(p,
                        "&f==============================\n"
                                + "&aCore Pay &cHelp\n"
                                + "&a/pay <USERNAME> <amount> <silvers/doubloons>: &6Pays a player\n"
                                + "&f=============================="
                );

            }  else {

                String tPlayer = args[0];
                String pAmount = args[1];
                String cType = args[2];

                if(tPlayer != null && pAmount != null && cType != null) {

                    if(!Objects.equals(cType, "silvers") && !Objects.equals(cType, "doubloons")) {
                        Chat.send(p,"&4 You can only pay people with Silvers and Doubloons\n" +
                                "&a/pay <USERNAME> <amount> <silvers/doubloons>: &6Pays a player"
                        );
                        return true;
                    }

                    if(currencyManager.getPlayerCurrency(p, cType) >= Integer.parseInt(pAmount)) {
                        Player target = Bukkit.getPlayer(tPlayer);

                        if(target != null) {
                            currencyManager.addCurrencyToPlayer(target, cType, Integer.parseInt(pAmount));
                            currencyManager.removeCurrencyFromPlayer(p, cType, Integer.parseInt(pAmount));

                            Chat.send(p,"&aYou have paid " + tPlayer + " " + pAmount + " " + cType);
                            Chat.send(target, "&a" + p.getName() + " has paid you " + pAmount + " " + cType);
                        } else {
                            Chat.send(p,"&4Couldn't find player " + tPlayer + " on the Server.");
                        }
                    } else {
                        Chat.send(p,"&4You don't have enough to pay " + tPlayer + " " + pAmount + " " + cType);
                    }

                } else {
                    Chat.send(p,"&a/pay <USERNAME> <amount> <silvers/doubloons>: &6Pays amount to player");
                }

            }
            return true;
        }

        return false;
    }

}


