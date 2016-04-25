package me.caprei.crazyctf.ability;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import me.caprei.crazyctf.game.CrazyCTF;

public class CannonShot extends Ability{
	
	public CannonShot(){
		triggerItemNames.add("Cannon");
		abilityName = "Cannon Shot";
		secondsCooldown = 7.0;
		Bukkit.getPluginManager().registerEvents(this, CrazyCTF.getInstance());
	}
	
	@Override
	public void castAbility(Player caster){
		if(!super.validCast(caster)){
			return;
		}
		Location casterLocation = caster.getLocation();
		Vector direction = casterLocation.getDirection();
		ThreadLocalRandom random = ThreadLocalRandom.current();
		Arrow[] arrows = new Arrow[6];
		for(int x = 0; x < 6; x++){
			arrows[x] = caster.getLocation().getWorld().spawnArrow(caster.getLocation(), 
					direction.add(new Vector(random.nextDouble(direction.getX() - 5.0, direction.getX() + 3.0), 
							random.nextDouble(direction.getY() - 3.0, direction.getY() + 3.0), 
							random.nextDouble(direction.getZ() - 3.0, direction.getZ() + 3.0))), 
					10.0f, 10.0f);
			arrows[x].setKnockbackStrength(4);
			arrows[x].setVelocity(arrows[x].getVelocity().multiply(100));
			arrows[x].setBounce(false);
			arrows[x].setTicksLived(50);
			arrows[x].setShooter(caster);
			arrows[x].setMetadata("Cannon Shot", new FixedMetadataValue(CrazyCTF.getInstance(), "Cannon Shot"));
		}
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
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event){
		if(event.getEntityType() == EntityType.ARROW){
			Arrow arrow = (Arrow) event.getEntity();
			if(arrow.hasMetadata("Cannon Shot")){
				arrow.getLocation().getWorld().createExplosion(arrow.getLocation().getX(), arrow.getLocation().getY(), arrow.getLocation().getZ(), 1.0f, false, false);
				arrow.remove();
			}
		}
	}
}
