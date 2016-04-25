package me.caprei.crazyctf.abilities;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.metadata.FixedMetadataValue;

import me.caprei.crazyctf.game.CrazyCTF;
import net.md_5.bungee.api.ChatColor;

public class SniperBow extends Ability{

	public SniperBow(){
		triggerItemNames.add("Bow [Sniper Mode]");
		abilityName = "Sniper Bow";
		secondsCooldown = 2.0;
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
			event.getProjectile().setMetadata("Sniper", new FixedMetadataValue(CrazyCTF.getInstance(), "Sniper"));
			cooldown = new AbilityCooldown(secondsCooldown);
		}
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
		if(event.getCause() == DamageCause.PROJECTILE && event.getDamager() instanceof Arrow && event.getEntity() instanceof LivingEntity){
			Arrow arrow = (Arrow) event.getDamager();
			if(arrow.hasMetadata("Sniper") && arrow.getShooter() instanceof Player){
				Player shooter = (Player) arrow.getShooter();
				int distance = (int) shooter.getLocation().distance(event.getEntity().getLocation());
				LivingEntity entity = (LivingEntity) event.getEntity();
				event.setCancelled(true);
				entity.damage(distance/3);
				if(distance > 80){
					Bukkit.broadcastMessage(ChatColor.RED + ChatColor.BOLD.toString() + shooter.getName() + " sniped " + entity.getName() + " from " + distance + " blocks away!!");
				}
			}
		}
	}
}
