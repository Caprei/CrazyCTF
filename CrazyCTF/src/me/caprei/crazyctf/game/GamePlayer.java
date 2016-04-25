package me.caprei.crazyctf.game;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.caprei.crazyctf.classes.ClassManager;
import me.caprei.crazyctf.classes.ClassType;
import me.caprei.crazyctf.team.Team;
import me.caprei.crazyctf.team.TeamManager;

public class GamePlayer {
	
	private Player player;
	private int kills;
	private int deaths;
	private int captures;
	private Team team;
	private ClassType classType = ClassType.SOLDIER;
	
	private int totalKills;
	private int totalDeaths;
	private int totalCaptures;
	
	public GamePlayer(Player player, int kills, int deaths, int captures, int totalKills, int totalDeaths, int totalCaptures) {
		this.player = player;
		this.kills = kills;
		this.deaths = deaths;
		this.captures = captures;
		this.totalKills = totalKills;
		this.totalDeaths = totalDeaths;
		this.totalCaptures = totalCaptures;
	}
	public int getKills() {
		return kills;
	}
	public int getDeaths() {
		return deaths;
	}
	public int getCaptures() {
		return captures;
	}
	public Team getTeam() {
		return team;
	}
	public ClassType getKit() {
		return classType;
	}
	public int getTotalKills() {
		return totalKills;
	}
	public int getTotalDeaths() {
		return totalDeaths;
	}
	public int getTotalCaptures() {
		return totalCaptures;
	}
	public void setKills(int kills) {
		this.kills = kills;
	}
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	public void setCaptures(int captures) {
		this.captures = captures;
	}
	public void setTeam(Team team) {
		if(team == Team.BLUE){
			this.getPlayer().getInventory().setHelmet(new ItemStack(Material.WOOL, (byte) 11));
			TeamManager.setBlueTeamCount(TeamManager.getBlueTeamCount() + 1);
		}else{
			this.getPlayer().getInventory().setHelmet(new ItemStack(Material.WOOL, (byte) 14));
			TeamManager.setRedTeamCount(TeamManager.getRedTeamCount() + 1);
		}
		this.team = team;
	}
	public void setKit(ClassType classType) {
		this.classType = classType;
		ClassManager.assignKit(this);
	}
	public void setTotalKills(int totalKills) {
		this.totalKills = totalKills;
	}
	public void setTotalDeaths(int totalDeaths) {
		this.totalDeaths = totalDeaths;
	}
	public void setTotalCaptures(int totalCaptures) {
		this.totalCaptures = totalCaptures;
	}
	public Player getPlayer() {
		return player;
	}
}
