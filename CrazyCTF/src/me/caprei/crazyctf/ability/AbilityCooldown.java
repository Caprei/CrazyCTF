package me.caprei.crazyctf.ability;

public class AbilityCooldown {

	long beginningTime;
	long milisecondsTime;
	long endingTime;
	
	public AbilityCooldown(double seconds){
		beginningTime = System.currentTimeMillis();
		this.milisecondsTime = (long) (seconds * 1000);
		endingTime = (long) (beginningTime + (seconds * 1000));
	}
	
	public double secondsLeft(){
		long milisecondsLeft = endingTime - (milisecondsTime + beginningTime);
		return ((milisecondsLeft/1000)/60);
	}
	
	public boolean isComplete(){
		if(secondsLeft() > 0){
			return false;
		}else{
			return true;
		}
	}
}
