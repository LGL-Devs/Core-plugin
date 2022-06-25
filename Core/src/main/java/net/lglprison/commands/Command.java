package net.lglprison.commands;

import net.lglprison.Main;
import net.lglprison.util.Chat;

public class Command {
    public static void enable(Main plugin) {

        try {
            new discord(plugin);
            new pay(plugin);
            new economy(plugin);
            new me(plugin);
        } finally {
            Chat.console("Commands Loaded");
        }
        //new rules(plugin);
    }
}
