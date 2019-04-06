package net.fyrezz.me.expbottler;

import java.io.File;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.fyrezz.me.expbottler.cmd.CmdGiveXpBottle;
import net.fyrezz.me.expbottler.cmd.CmdXpBottle;
import net.fyrezz.me.expbottler.listeners.PlayerListener;

public class P extends JavaPlugin {
	
	public static P p;
	
	private FileConfiguration lang;
	
	//Single plugin instance
	public P() {
		p = p;
	}
	
	@Override
	public void onEnable() {
		//Load all default and custom configurations
		loadConfigs();

		//Register event listeners
		registerListeners();
		
		//Register commands
		registerCommands();
		
		getLogger().log(Level.INFO, "ExpBottler has been correctly initialized");
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public void loadConfigs() {
		//config.yml
		saveDefaultConfig();
		
		//lang.yml
		saveResource("lang.yml", false);
		lang = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "lang.yml"));
	}
	
	public void registerListeners() {
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
	}
	
	public void registerCommands() {
		getCommand("xpbottle").setExecutor(new CmdXpBottle());
		getCommand("givexpbottle").setExecutor(new CmdGiveXpBottle());
	}
	
	public FileConfiguration getLang() {
		if (lang == null) {
			saveResource("lang.yml", false);
		}
		return lang;
	}
	
}
