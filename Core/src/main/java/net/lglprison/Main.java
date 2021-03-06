package net.lglprison;

import net.lglprison.commands.Command;
import net.lglprison.discord.*;
import net.lglprison.economy.currencyManager;
import net.lglprison.events.Event;
import net.lglprison.integrations.PAPI;
import net.lglprison.mongo.Database;
import net.lglprison.util.Chat;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.io.File;

public class Main extends JavaPlugin {

    public static YamlConfiguration config = YamlConfiguration.loadConfiguration(new File("plugins/Core/config.yml"));
    public static PAPI placeholderapiExpansion;
    public Main() {}
    @Override
    public void onEnable() {

        saveDefaultConfig();

        if(config == null) {
            config = (YamlConfiguration) this.getConfig();
        }

        try {
            Bot.enable();
        } catch (LoginException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        Database.connect();
        Event.listeners(this);
        new PAPI(this).register();
        new currencyManager(this);
        Command.enable(this);

        Chat.console("&fStarting Core");
        Chat.console("&fCore Active");

        this.getConfig().options().copyDefaults();

    }


    @Override
    public void onDisable() {

        try {
            Bot.shutdown();
            currencyManager.disable();
        } finally {
            Database.disable();
            Chat.console("&fCore Disabled");
        }

    }

    public static API getAPI() {
        API api = new API();
        return api;
    }

}
