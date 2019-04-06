package net.fyrezz.me.expbottler;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class CustomConfig {
	
	private String fileName;/
	private File file;
	private FileConfiguration config;
	
	public CustomConfig(String fileName) {
		this.fileName = fileName;
		this.file = new File(P.p.getDataFolder(), fileName);
		this.config = YamlConfiguration.loadConfiguration(file);
	}
	
	public FileConfiguration getConfig() {
		if (config == null) {
			reloadConfig();
		}
		return config;
	}
	
	public void saveDefaultConfig() {
		if (!file.exists()) {
			P.p.saveResource(fileName, false);
		}
	}
	
	public void reloadConfig() {
		config = YamlConfiguration.loadConfiguration(file);
	}

}
