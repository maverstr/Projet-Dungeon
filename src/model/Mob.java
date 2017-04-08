package model;

import java.awt.image.BufferedImage;

public abstract class Mob extends Character {

	public Mob(int x, int y, Game game, BufferedImage sprite) {
		super(x, y, game, sprite);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void attack(int xAttack, int yAttack) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean isObstacle() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void wasHit() {
		die();
	}
	
	public void die() {
		this.getGame().getGameObjects().remove(this);
	}
	

}
