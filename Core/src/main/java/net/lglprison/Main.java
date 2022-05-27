package net.lglprison;

import net.lglprison.commands.command;
import net.lglprison.discord.*;
import net.lglprison.events.Event;
import net.lglprison.integrations.PAPI;
import net.lglprison.integrations.dependants;
import net.lglprison.mongo.Database;
import net.lglprison.util.Chat;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.io.File;

public class Main extends JavaPlugin {

    public static API api;
    public static YamlConfiguration config = YamlConfiguration.loadConfiguration(new File("plugins/Core/config.yml"));
    public static PAPI placeholderapiExpansion;
    public Main() {}
    @Override
    public void onEnable() {

        saveDefaultConfig();

        try {
            Bot.enable();
        } catch (LoginException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Database.connect();
        Event.listeners(this);
        new dependants(this).register();
        command.enable(this);

        Chat.console("&fStarting Core");
        Chat.console("&fCore Active");

        this.getConfig().options().copyDefaults();

    }


    @Override
    public void onDisable() {

        Bot.shutdown();
        Database.disable();
        Chat.console("&fCore Disabled");

    }

}
