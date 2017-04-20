package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Consumable extends Item {
	public Consumable(BufferedImage inventoryImage, int x, int y, Game game, ArrayList<Sprite> spriteList){
		super(inventoryImage,x,y,game,spriteList);
	}
	
	public String getType() {
		return String.format("%s",this.getClass());
	}
	
	@Override
	public void pickUp(Inventory inventory) {
		this.getGame().getGameObjects().remove(this);
		inventory.addConsumable(this);
	}
	
}
