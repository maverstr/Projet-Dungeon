package model;

import java.awt.image.BufferedImage;

public abstract class Weapon extends Item {
	
	private int damage;
	
	public Weapon(BufferedImage sprite,int damage){
		super(sprite);
		this.damage = damage;
	}
	
	public int getDamage() {
		return this.damage;
	}

	public abstract boolean breakBlockAbility();
	
}
