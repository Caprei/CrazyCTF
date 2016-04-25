package me.caprei.crazyctf.team;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.ChatColor;

import me.caprei.crazyctf.game.GamePlayer;
import me.caprei.crazyctf.game.PlayerManager;

public class TeamManager {
	
	private static int redTeamCount = 0;
	private static int blueTeamCount = 0;
	
	private TeamManager(){}
	
	
	public static void assignTeams(){
		for(GamePlayer player:PlayerManager.getPlayerManager().getPlayers()){
			if(player.getTeam() == null){
				if(redTeamCount > blueTeamCount){
					player.setTeam(Team.BLUE);
				}else if (redTeamCount < blueTeamCount){
					player.setTeam(Team.RED);
				}else{
					if(ThreadLocalRandom.current().nextInt(1, 2) > 1){
						player.setTeam(Team.BLUE);
					}else{
						player.setTeam(Team.RED);
					}
				}
			}
		}
	}
	
	public static void assignTeam(GamePlayer player, Team team){
		int numOfPlayers = PlayerManager.getPlayerManager().getPlayers().size();
		if(team == Team.RED && redTeamCount <= Math.ceil(numOfPlayers/2)){
			player.setTeam(team);
			return;
		}else if(team == Team.BLUE && blueTeamCount <= Math.ceil(numOfPlayers/2)){
			player.setTeam(team);
			return;
		}
		player.getPlayer().sendMessage(ChatColor.RED + "CrazyCTF> Sorry, that team is full!");
	}


	public static int getRedTeamCount() {
		return redTeamCount;
	}


	public static int getBlueTeamCount() {
		return blueTeamCount;
	}


	public static void setRedTeamCount(int redTeamCount) {
		TeamManager.redTeamCount = redTeamCount;
	}


	public static void setBlueTeamCount(int blueTeamCount) {
		TeamManager.blueTeamCount = blueTeamCount;
	}
}
