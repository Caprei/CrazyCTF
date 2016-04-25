package me.caprei.crazyctf.ability;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.caprei.crazyctf.utils.ChatUtils;

public abstract class Ability implements Listener{
	
	protected Set<String> triggerItemNames = new HashSet<String>();
	protected String abilityName;
	protected double secondsCooldown;
	protected AbilityCooldown cooldown;
	
	public boolean validCast(Player caster){
		if(cooldown != null){
			if(!cooldown.isComplete()){
				caster.sendMessage(ChatColor.BLUE + "CrazyCTF> You cannot use " + ChatUtils.ABILITY_COLOURS + abilityName + " for " +
			    ChatUtils.ABILITY_COLOURS + cooldown.secondsLeft() + ChatColor.BLUE + " seconds.");
				return false;
			}
		}
		return true;
	}
	
	public abstract void castAbility(Player caster);
}
