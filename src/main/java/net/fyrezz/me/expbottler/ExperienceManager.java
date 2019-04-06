package net.fyrezz.me.expbottler;

import org.bukkit.entity.Player;

public class ExperienceManager {
	
	public void createBottle(Player player, int experience) {

    	int originalLevel = player.getLevel();
    	int originalExp = Math.round(getExpToLevel(originalLevel) + getExpForProgressToNextLevel(player.getExp(), originalLevel));
    	
        //Prevent EXP dupe
        player.setLevel(0);
        player.setTotalExperience(0);
        
        //Calculate player's new experience. -1 is always subtracted to compensate possible calculation errors.
        int newExp = originalExp - experience;
        int newLevel = getLevelFromExp(newExp);
        int experienceToCurrentLevel = Math.round(getExpToLevel(newLevel));
        
        //Set player's new experience
        player.setTotalExperience(newExp);
        player.setLevel((int) newLevel);
        player.setExp(((newExp - experienceToCurrentLevel) / (float) player.getExpToLevel()));
	}
	
	public void redeemBottle(Player player, int experience) {

    	int originalLevel = player.getLevel();
    	int originalExp = Math.round(getExpToLevel(originalLevel) + getExpForProgressToNextLevel(player.getExp(), originalLevel));
    	
        //Prevent EXP dupe
        player.setLevel(0);
        player.setTotalExperience(0);

        //Calculate player's new experience. -1 is always subtracted to compensate possible calculation errors.
        int newExp = originalExp + experience - 1 - P.p.config.getInt("redeemtax");
        int newLevel = getLevelFromExp(newExp);
        int experienceToCurrentLevel = Math.round(getExpToLevel(newLevel));

        //Set player's new experience
        player.setTotalExperience(newExp);
        player.setLevel((int) newLevel);
        player.setExp(((newExp - experienceToCurrentLevel) / (float) player.getExpToLevel()));
	}
	
    public float getExpToLevel(int level) {
    	
        if (level >= 31) {
            return 4.5f * level * level - 162.5f * level + 2220f;
        } else if (level >= 16) {
            return 2.5f * level * level - 40.5f * level + 360f;
        } else {
            return level * level + 6f * level;
        }
    }
    
    public float getExpForProgressToNextLevel(float progress, int originalLevel) {

        if (originalLevel >= 31) {
            return progress * (9 * originalLevel - 158);
        } else if (originalLevel >= 16) {
            return progress * (5 * originalLevel - 38);
        } else {
            return progress * (2 * originalLevel + 7);
        }
    }

    public static int getLevelFromExp(int exp) {
    	
        if (exp >= 1507) {
            return (int) ((Math.sqrt(72 * exp - 54215) + 325) / 18);
        } else if (exp >= 352) {
            return (int) ((Math.sqrt(40 * exp - 7839) + 81) / 10);
        } else {
            return (int) (Math.sqrt(exp + 9) - 3);
        }
    }
    
}
