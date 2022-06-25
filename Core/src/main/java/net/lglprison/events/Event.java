package net.lglprison.events;

import net.lglprison.Main;

public class Event {
    public static void listeners(Main plugin) {
        new playerJoin(plugin);
        new playerLeave(plugin);
        new blockBreak(plugin);
    }

}
