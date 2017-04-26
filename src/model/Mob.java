package model;

import java.util.ArrayList;

public abstract class Mob extends Character implements Runnable {
	
	Thread t;

	public Mob(int x, int y, Game game, ArrayList<Sprite> spriteList, int health) {
		super(x, y, game, spriteList,health);
		t = new Thread(this);
		t.start();
	}
	
	public void attack(int xAttack, int yAttack) {
		int newPosX = this.posX+xAttack;
		int newPosY = this.posY+yAttack;
		
		Player player = this.getGame().getPlayer();
		if (player.getPosX() == newPosX && player.getPosY() == newPosY) {
			player.wasHit(1);
		}
	}
	
	public abstract void attackPattern();
	
	
	public void die() {
		if (!this.getGame().getGameObjects().remove(this)) {
			System.out.println(this); //TODO : ces putains de lasers
			this.getSpriteList().get(0).setOffset(0.2, 0.2);
			System.out.println(this.getGame().getGameObjects());
		}
		loot();
		t.interrupt();
	}
	
	public void loot() {
		this.getGame().loot(this.posX, this.posY, 13,false);
	}
	
	public abstract void run();
	
	@Override
	public boolean isAttackable() {
		return true;
	}
	

}
