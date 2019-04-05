package net.fyrezz.me.expbottler;

import org.bukkit.plugin.java.JavaPlugin;

public class P extends JavaPlugin {
	
	public static P p;
	
	private CustomConfig lang;
	
	public P() {
		this.p = p;
	}
	
	@Override
	public void onEnable() {
		loadConfigs();
		
		registerListeners();
		
		registerManagers();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public void loadConfigs() {
		
		//config.yml
		saveDefaultConfig();
		
		//lang.yml
		lang = new CustomConfig("lang.yml");
		lang.saveDefaultConfig();
	}
	
}
