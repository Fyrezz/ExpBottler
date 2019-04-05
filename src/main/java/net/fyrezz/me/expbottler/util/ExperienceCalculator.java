package net.fyrezz.me.expbottler.util;

import java.math.BigDecimal;

import org.bukkit.entity.Player;

public class ExperienceCalculator {
	
  public static int getTotalExperience(Player player) {
    int experience = 0;
    int level = player.getLevel();
    if ((level >= 0) && (level <= 15)) {
      experience = (int)Math.ceil(Math.pow(level, 2.0D) + 6 * level);
      int requiredExperience = 2 * level + 7;
      double currentExp = Double.parseDouble(Float.toString(player.getExp()));
      experience = (int)(experience + Math.ceil(currentExp * requiredExperience));
      return experience;
    }
    
    if ((level > 15) && (level <= 30)) {
      experience = (int)Math.ceil(2.5D * Math.pow(level, 2.0D) - 40.5D * level + 360.0D);
      int requiredExperience = 5 * level - 38;
      double currentExp = Double.parseDouble(Float.toString(player.getExp()));
      experience = (int)(experience + Math.ceil(currentExp * requiredExperience));
      return experience;
    }
    
    experience = (int)Math.ceil(4.5D * Math.pow(level, 2.0D) - 162.5D * level + 2220.0D);
    int requiredExperience = 9 * level - 158;
    double currentExp = Double.parseDouble(Float.toString(player.getExp()));
    experience = (int)(experience + Math.ceil(currentExp * requiredExperience));
    return experience;
  }
  
  public static void setTotalExperience(Player player, int xp) {
	  
    if ((xp >= 0) && (xp < 351)) {
      int a = 1;int b = 6;int c = -xp;
      int level = (int)(-b + Math.sqrt(Math.pow(b, 2.0D) - 4 * a * c)) / (2 * a);
      int xpForLevel = (int)(Math.pow(level, 2.0D) + 6 * level);
      int remainder = xp - xpForLevel;
      int experienceNeeded = 2 * level + 7;
      float experience = remainder / experienceNeeded;
      experience = round(experience, 2);
      
      player.setLevel(level);
      player.setExp(experience);
    } else if ((xp >= 352) && (xp < 1507)) {
      double a = 2.5D;double b = -40.5D;int c = -xp + 360;
      double dLevel = (-b + Math.sqrt(Math.pow(b, 2.0D) - 4.0D * a * c)) / (2.0D * a);
      int level = (int)Math.floor(dLevel);
      int xpForLevel = (int)(2.5D * Math.pow(level, 2.0D) - 40.5D * level + 360.0D);
      int remainder = xp - xpForLevel;
      int experienceNeeded = 5 * level - 38;
      float experience = remainder / experienceNeeded;
      experience = round(experience, 2);
      
      player.setLevel(level);
      player.setExp(experience);
    } else {
      double a = 4.5D;double b = -162.5D;int c = -xp + 2220;
      double dLevel = (-b + Math.sqrt(Math.pow(b, 2.0D) - 4.0D * a * c)) / (2.0D * a);
      int level = (int)Math.floor(dLevel);
      int xpForLevel = (int)(4.5D * Math.pow(level, 2.0D) - 162.5D * level + 2220.0D);
      int remainder = xp - xpForLevel;
      int experienceNeeded = 9 * level - 158;
      float experience = remainder / experienceNeeded;
      experience = round(experience, 2);
      
      player.setLevel(level);
      player.setExp(experience);
    }
  }
  
  private static float round(float d, int decimalPlace) {
    BigDecimal bd = new BigDecimal(Float.toString(d));
    bd = bd.setScale(decimalPlace, 5);
    return bd.floatValue();
  }
}
