package me.caprei.crazyctf.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;

import me.caprei.crazyctf.team.Team;

public class CTFPlayerEvents implements Listener{
	
	BukkitTask countdown;
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event){
		Player player = event.getPlayer();
		GamePlayer gamePlayer = new GamePlayer(player, 0, 0, 0, 0, 0, 0); //TODO INSERT LOGIC FOR GETTING FROM CONFIG
		PlayerManager.getPlayerManager().addPlayer(gamePlayer);
		if(GameStateManager.getManager().validStart()){
			countdown = GameStateManager.getManager().beginCountdown();
		}
	}
	
	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event){
		PlayerManager.getPlayerManager().removePlayer(event.getPlayer());
		if(GameStateManager.getManager().getCurrentState() == GameState.COUNTDOWN){
			if(!GameStateManager.getManager().validStart()){
				countdown.cancel();
				Bukkit.broadcastMessage(ChatColor.RED + "Countdown cancelled! Not enough players.");
			}
		}
	}
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event){
		GamePlayer player = PlayerManager.getPlayerManager().getGamePlayer(event.getPlayer());
		if(player.getKit().getClassInstance().getWearableItems().contains(event.getItemDrop()) || 
				player.getKit().getClassInstance().getInventoryItems().contains(event.getItemDrop())){
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.RED + "CrazyCTF> You can't drop your items!");
		}
	}
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent event){
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		if(event.getFrom() == event.getTo()){
			return;
		}
		Player player = event.getPlayer();
		Location location = player.getLocation();
        if(location.distanceSquared(GameStateManager.getManager().getCurrentMap().getBlueFlag()) > 1.5 ||
		   location.distanceSquared(GameStateManager.getManager().getCurrentMap().getRedFlag()) > 1.5){
		   return;
	    }
        GamePlayer gamePlayer = PlayerManager.getPlayerManager().getGamePlayer(player);
        
        if(location.distanceSquared(GameStateManager.getManager().getCurrentMap().getBlueFlag()) <= 1.5){
        	if(gamePlayer.getTeam() == Team.RED){
        		//TODO GIVE THEM THE FLAG
        	}
        }
	}
}
