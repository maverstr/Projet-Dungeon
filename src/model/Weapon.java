package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Weapon extends Item {
	
	private int damage;
	
	public Weapon(int damage, BufferedImage inventoryImage, int x, int y, Game game, ArrayList<Sprite> spriteList) {
		super(inventoryImage,x,y,game,spriteList);
		this.damage = damage;
	}
	
	public int getDamage() {
		return this.damage;
	}

	public abstract boolean breakBlockAbility();
	
	@Override
	public void pickUp(Inventory inventory) {
		this.getGame().getGameObjects().remove(this);
		inventory.addWeapon(this);
	}
	
}
