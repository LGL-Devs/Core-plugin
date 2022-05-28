package net.lglprison.commands;

import net.lglprison.Main;
import net.lglprison.util.Chat;

public class command {
    public static void enable(Main plugin) {

        try {
            new discord(plugin);
            new economy(plugin);
        } finally {
            Chat.console("Commands Loaded");
        }
        //new rules(plugin);
    }
}
