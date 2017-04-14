package model;

import java.awt.image.BufferedImage;

public abstract class Mob extends Character implements Runnable {
	
	Thread t;

	public Mob(int x, int y, Game game, BufferedImage sprite, int health) {
		super(x, y, game, sprite,health);
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
