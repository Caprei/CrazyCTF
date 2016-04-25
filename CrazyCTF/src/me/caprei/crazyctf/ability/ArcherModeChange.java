package me.caprei.crazyctf.ability;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.caprei.crazyctf.game.CrazyCTF;

public class ArcherModeChange extends Ability {

	private static Map<String, ArcherMode> archerModes = new HashMap<>();
	
	public ArcherModeChange(){
		triggerItemNames.add("Bow [Sniper Mode]");
		triggerItemNames.add("Bow [Machine Gun Mode]");
		triggerItemNames.add("Bow [Explosive Mode]");
		abilityName = "Archer Mode Change";
		secondsCooldown = 1.0;
		Bukkit.getPluginManager().registerEvents(this, CrazyCTF.getInstance());
	}
		
	@Override
	public void castAbility(Player caster) {}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		if(!super.validCast(event.getPlayer())){
			return;
		}
		String itemName = ChatColor.stripColor(event.getPlayer().getItemInHand().getItemMeta().getDisplayName());
		if(triggerItemNames.contains(itemName)){
		    if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK){
		    	switch(ArcherModeChange.archerModes.get(event.getPlayer())){
		    	case SNIPER:
		    		ArcherModeChange.archerModes.put(event.getPlayer().getName(), ArcherMode.MACHINE_GUN);
		    		break;
		    	case MACHINE_GUN:
		    		ArcherModeChange.archerModes.put(event.getPlayer().getName(), ArcherMode.EXPLOSIVE);
		    		break;
		    	case EXPLOSIVE:
		    		ArcherModeChange.archerModes.put(event.getPlayer().getName(), ArcherMode.SNIPER);
		    		break;
		    	}
		    }
		    event.getPlayer().sendMessage(ChatColor.GREEN + "CrazyCTF> You changed to " + ArcherModeChange.archerModes.get(event.getPlayer()).toString() + " mode.");
		}
		cooldown = new AbilityCooldown(secondsCooldown);
	}
}
