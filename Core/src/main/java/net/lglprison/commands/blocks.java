package net.lglprison.commands;

import net.lglprison.Main;
import net.lglprison.util.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class blocks implements CommandExecutor {

    private Main plugin;

    public blocks(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("discord").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Chat.Color(Chat.prefix + "&cOnly players Can execute this command"));
            return true;
        }

        Player p = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("discord")) {

            if(args.length == 0) {

                Chat.send(p,
                        "&f==============================\n"
                                + "&aCore Blocks &cHelp\n"
                                + "&a/blocks <USERID>: &6shows the blocks you have mined\n"
                                + "&f=============================="
                );

                return true;

            } else if(args.length >= 1) {

            }
            return true;
        }

        return false;
    }
}
