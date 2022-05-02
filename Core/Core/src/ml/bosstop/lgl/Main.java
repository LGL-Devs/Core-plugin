package ml.bosstop.lgl;

import org.bukkit.plugin.java.JavaPlugin;

import ml.bosstop.lgl.util.Chat;

public class Main extends JavaPlugin {	

	public Main() {
    }
	
	
    @Override
    public void onEnable() {
    	
    	//Event.Listeners(this);
    	//comand.enable(this);
        
    	Chat.console("&fStarting Core");
    	Chat.console("&Core Active");
        
    }


	@Override
    public void onDisable() {
    	
		Chat.console("&Core Disabled");

    }
}