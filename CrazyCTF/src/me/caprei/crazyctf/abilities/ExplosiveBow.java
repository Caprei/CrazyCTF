package me.caprei.crazyctf.abilities;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;

import me.caprei.crazyctf.game.CrazyCTF;
import net.md_5.bungee.api.ChatColor;

public class ExplosiveBow extends Ability{
	
	public ExplosiveBow(){
		triggerItemNames.add("Bow [Explosive Mode]");
		abilityName = "Explosive Arrow";
		secondsCooldown = 7.0;
		Bukkit.getPluginManager().registerEvents(this, CrazyCTF.getInstance());
	}

	@Override
	public void castAbility(Player caster) {}
	
	@EventHandler
	public void onEntityShootBow(EntityShootBowEvent event){
		if(event.getEntity() instanceof Player && triggerItemNames.contains(ChatColor.stripColor(event.getBow().getItemMeta().getDisplayName()))){
			if(!super.validCast((Player) event.getEntity())){
				return;
			}
			event.getProjectile().setMetadata("Explosive", new FixedMetadataValue(CrazyCTF.getInstance(), "Explosive"));
			cooldown = new AbilityCooldown(secondsCooldown);
		}
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event){
		if(event.getEntityType() == EntityType.ARROW){
			Arrow arrow = (Arrow) event.getEntity();
			if(arrow.hasMetadata("Explosive")){
				arrow.getLocation().getWorld().createExplosion(arrow.getLocation().getX(), arrow.getLocation().getY(), arrow.getLocation().getZ(), 2.5f, true, false);
				arrow.remove();
			}
		}
	}
}
