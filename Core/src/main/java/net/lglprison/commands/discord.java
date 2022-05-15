package net.lglprison.commands;

import net.dv8tion.jda.api.entities.User;
import net.lglprison.Main;
import net.lglprison.discord.discordUser;
import net.lglprison.mongo.Storage;
import net.lglprison.util.Chat;
import org.bson.Document;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.lglprison.Main.config;
import static net.lglprison.discord.Bot.jda;

public class discord implements CommandExecutor {

    private Main plugin;

    public discord(Main plugin){
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

                if(p.hasPermission("core.admin")) {
                    p.sendMessage(Chat.Color(
                            "&f==============================\n"
                                    + "&aCore Discord &cHelp\n"
                                    + "&a/discord connect <USERID>: &6Links your discord account to your minecraft account\n"
                                    + "&a/discord find <USERID>: &6Finds a Discord User and MC Stats\n"
                                    + "&f=============================="
                    ));
                    return true;
                }

                p.sendMessage(Chat.Color(
                        "&f==============================\n"
                                + "&aCore Discord &cHelp\n"
                                + "&a/discord connect <USERID>: &6Links your discord account to your minecraft account\n"
                                + "&f=============================="
                ));

                return true;

            } else if(args.length >= 1) {

                if(args[0].equalsIgnoreCase("connect")) {

                    User user = jda.getUserById(args[1]);

                    if(user != null) {

                        Document doc = Storage.getPlayer(args[1]);

                        if(doc != null) {
                            p.sendMessage(Chat.Color(Chat.prefix + "&7Your discord account has already been linked to another MC Account"));
                        } else {

                            Storage.setPlayer(p.getUniqueId(), Storage.data.discord, args[1]);

                            discordUser.AccountLinked(args[1], p);

                            p.sendMessage(Chat.Color(Chat.prefix + "&7Your account has been found and linked to your Minecraft Account"));
                            p.sendMessage(Chat.Color(Chat.prefix + "&7Discord User: " + user.getName()));

                        }

                    } else {
                        p.sendMessage(Chat.Color(Chat.prefix + "&7Could not find your Discord Account. Make sure you have joined " + config.getString("discordServerInvite")));
                    }

                    return true;

                } else if(args[0].equalsIgnoreCase("find")) {

                    if(p.hasPermission("core.admin")) {
                        User user = jda.getUserById(args[1]);
                        Document doc = Storage.getPlayer(args[1]);

                        if(user != null) {

                            p.sendMessage(Chat.Color(Chat.prefix + "&fDiscord User: " + user.getName() + user.getDiscriminator()));
                            p.sendMessage(Chat.Color(Chat.prefix + "&fDiscord ID: " + user.getId()));
                            if(doc != null) {
                                p.sendMessage(Chat.Color(Chat.prefix + "&fPrison Account: "));
                                p.sendMessage(Chat.Color(Chat.prefix + "&fSlivers: " + doc.getInteger("silvers").toString()));
                                p.sendMessage(Chat.Color(Chat.prefix + "&fDoubloons: " + doc.getInteger("doubloons").toString()));
                                p.sendMessage(Chat.Color(Chat.prefix + "&fRubies: " + doc.getInteger("rubies").toString()));
                                p.sendMessage(Chat.Color(Chat.prefix + "&fBlocks: " + doc.getInteger("blocks").toString()));
                            }

                        } else {
                            p.sendMessage(Chat.Color(Chat.prefix + "&7Could not find your Discord User"));
                        }
                    } else {
                        p.sendMessage(Chat.Color(Chat.prefix + "&7You don't have the right permission to use this command."));
                    }

                    return true;

                } else {

                    p.sendMessage(Chat.Color(Chat.prefix + "&7Could not find that Player. Use /discord for help commands"));

                }
            }
            return true;
        }
        return false;
    }
}
