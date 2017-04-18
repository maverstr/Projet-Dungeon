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
		//System.out.println("mob die");
		this.getGame().getGameObjects().remove(this);
		t.interrupt();
	}
	
	public abstract void run();
	

}
