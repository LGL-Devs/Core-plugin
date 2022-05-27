package net.lglprison.integrations;

import net.lglprison.Main;

public class dependants {

    //    PowerRanks.log.info("PlaceholderAPI found!");
    //    PowerRanks.placeholderapiExpansion = new PowerRanksExpansion(this);
    //    PowerRanks.placeholderapiExpansion.register();

    private static Main plugin;

    public dependants(Main plugin) {
        this.plugin = plugin;

        Main.placeholderapiExpansion = new PAPI(plugin);
    }

    public static void register() {

        Main.placeholderapiExpansion.register();
    }
}
