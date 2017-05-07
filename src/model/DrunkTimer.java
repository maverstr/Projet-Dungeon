package model;

public class DrunkTimer extends Timer {

	
	public DrunkTimer(int duration, Player player) {
		super(duration, player);
		
	}

	@Override
	public void delayedAction() {
		getPlayer().setDrunk(false);
	}

}
