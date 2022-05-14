package net.lglprison.events;

import net.lglprison.Main;

public class Event {

    public static void listeners(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(new playerJoin(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new playerLeave(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new blockBreak(plugin), plugin);
    }

}
