package net.lglprison;

import net.lglprison.util.Chat;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public Main() {
    }


    @Override
    public void onEnable() {

        //Event.Listeners(this);
        //command.enable(this);

        Chat.console("&fStarting Core");
        Chat.console("&Core Active");

    }


    @Override
    public void onDisable() {

        Chat.console("&Core Disabled");

    }
}
