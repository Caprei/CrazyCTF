package me.caprei.crazyctf.abilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.caprei.crazyctf.game.CrazyCTF;

public class MachineGunBow extends Ability{

	public MachineGunBow(){
		triggerItemNames.add("Bow [Machine Gun Mode]");
		abilityName = "Machine Gun";
		secondsCooldown = 0.5;
		Bukkit.getPluginManager().registerEvents(this, CrazyCTF.getInstance());
	}
	
	@Override
	public void castAbility(Player caster) {
		if(!super.validCast(caster)){
			return;
		}
		
		caster.getLocation().getWorld().spawnArrow(caster.getLocation(), caster.getLocation().getDirection(), 5.0f, 5.0f);
		cooldown = new AbilityCooldown(secondsCooldown);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		String itemName = ChatColor.stripColor(event.getPlayer().getItemInHand().getItemMeta().getDisplayName());
		if(triggerItemNames.contains(itemName)){
		    if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
		    	castAbility(event.getPlayer());
		    }
		}
	}
}
