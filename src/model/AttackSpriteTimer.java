package model;

public class AttackSpriteTimer extends Timer {

	public AttackSpriteTimer(int duration, Player player) {
		super(duration, player);
		
	}

	@Override
	public void delayedAction() {
		player.removeAttackSprite();
	}

}
