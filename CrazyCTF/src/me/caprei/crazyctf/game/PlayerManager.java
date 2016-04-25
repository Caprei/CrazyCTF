package me.caprei.crazyctf.game;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;

public class PlayerManager {
	
	private static PlayerManager playerManager = new PlayerManager();
	private Set<GamePlayer> players = new HashSet<>();
	
	private PlayerManager(){}
	
	public static PlayerManager getPlayerManager(){
		return playerManager;
	}
		
	public void addPlayer(GamePlayer player){
		players.add(player);
	}
	
	public void removePlayer(Player player){
		for(GamePlayer gamePlayer:players){
			if(gamePlayer.getPlayer().equals(player)){
				players.remove(gamePlayer);
			}
		}
	}
	
	public void removePlayer(GamePlayer player){
		players.remove(player);
	}
	
	public Set<GamePlayer> getPlayers(){
		return players;
	}
	
	public GamePlayer getGamePlayer(Player player){
		for(GamePlayer gamePlayer:players){
			if(gamePlayer.getPlayer().equals(player)){
				return gamePlayer;
			}
		}
		return null;
	}
}
