package net.lglprison.integrations;

import net.lglprison.Main;

public class register {

    //    PowerRanks.log.info("PlaceholderAPI found!");
    //    PowerRanks.placeholderapiExpansion = new PowerRanksExpansion(this);
    //    PowerRanks.placeholderapiExpansion.register();

    private static Main plugin;

    public register(Main plugin) {
        this.plugin = plugin;

        Main.placeholderapiExpansion = new PAPI(plugin);
    }

    public static void enable() {

        //Main.placeholderapiExpansion.register();
    }
}
