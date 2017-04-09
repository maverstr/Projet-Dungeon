package model;

import java.awt.image.BufferedImage;

import CONSTANTS.CONSTANTS;

public abstract class Mob extends Character implements Runnable {
	
	Thread t;

	public Mob(int x, int y, Game game, BufferedImage sprite) {
		super(x, y, game, sprite);
		// TODO Auto-generated constructor stub
		t = new Thread(this);
		t.start();
	}
	
	public void attack(int xAttack, int yAttack) {
		// TODO Auto-generated method stub
		int newPosX = this.posX+CONSTANTS.BLOCK_SIZE*xAttack;
		int newPosY = this.posY+CONSTANTS.BLOCK_SIZE*yAttack;
		
		Player player = this.getGame().getPlayer();
		if (player.getPosX() == newPosX && player.getPosY() == newPosY) {
			player.wasHit();
		}
	}
	
	@Override
	public boolean isObstacle() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public abstract void attackPattern();
	
	public void wasHit() {
		die();
	}
	
	public void die() {
		System.out.println("mob die");
		this.getGame().getGameObjects().remove(this);
		t.interrupt();
	}
	
	public abstract void run();
	

}
