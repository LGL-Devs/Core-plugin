package net.lglprison;

import net.lglprison.discord.*;
import net.lglprison.mongo.Database;
import net.lglprison.util.Chat;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.io.File;

public class Main extends JavaPlugin {
    private static File data = new File("plugins/Core/config.yml");
    public static YamlConfiguration config = YamlConfiguration.loadConfiguration(data);

    public Main() {

    }

    @Override
    public void onEnable() {

        saveDefaultConfig();

        try {
            bot.enable();
        } catch (LoginException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Database.connect();
        //command.enable(this);

        Chat.console("&fStarting Core");
        Chat.console("&fCore Active");

        this.getConfig().options().copyDefaults();

    }


    @Override
    public void onDisable() {

        bot.disable();
        Database.disable();
;       Chat.console("&fCore Disabled");

    }
}
