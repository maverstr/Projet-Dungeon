package model;

import java.awt.image.BufferedImage;

public abstract class Mob extends Character {

	public Mob(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
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

}
