package net.fyrezz.me.expbottler;

import java.io.File;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.fyrezz.me.expbottler.cmd.CmdGiveXpBottle;
import net.fyrezz.me.expbottler.cmd.CmdXpBottle;
import net.fyrezz.me.expbottler.listeners.LegacyPlayerListener;
import net.fyrezz.me.expbottler.listeners.PlayerListener;

public class P extends JavaPlugin {
	
	public static P p;
	
	public FileConfiguration config;
	public FileConfiguration lang;
	
	public ExperienceManager experienceManager;
	
	public String serverVersion;
	
	//Single plugin instance
	public P() {
		p = this;
	}
	
	@Override
	public void onEnable() {
		serverVersion = getServer().getVersion();
		
		//Load all default and custom configurations
		loadConfigs();
		
		/*
		 * Starting with 1.9, Player has 2 "hands". Different listeners from 1.9
		 */
		if (serverVersion.contains("1.8")) {
			registerLegacyListeners();
			getLogger().log(Level.INFO, "ExpBottler is using Legacy Listeners");
		} else {
			registerListeners();
		}
		
		//Register commands
		registerCommands();
		
		//Register managers
		registerManagers();
		
		getLogger().log(Level.INFO, "ExpBottler has been correctly initialized");
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public void loadConfigs() {
		saveResource("config.yml", false);
		saveResource("lang.yml", false);
		
		config = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "config.yml"));
		lang = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "lang.yml"));
	}
	
	public void registerLegacyListeners() {
		getServer().getPluginManager().registerEvents(new LegacyPlayerListener(), this);
	}
	
	public void registerListeners() {
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
	}
	
	public void registerManagers() {
		experienceManager = new ExperienceManager();
	}
	
	public void registerCommands() {
		getCommand("xpbottle").setExecutor(new CmdXpBottle());
		getCommand("givexpbottle").setExecutor(new CmdGiveXpBottle());
	}
	
}
