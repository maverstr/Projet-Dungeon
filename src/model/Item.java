package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


//public abstract class Item extends GameObject {
public abstract class Item extends GameObject {
	private BufferedImage inventoryImage;
	private int durability;
	
	public Item(BufferedImage inventoryImage, int x, int y, Game game, ArrayList<Sprite> spriteList) {
		super(x,y,game,spriteList);
		this.inventoryImage=inventoryImage;
		this.durability=1;
	}
	
	public abstract void pickUp(Inventory inventory);
	
	public BufferedImage getInventoryImage() {
		return this.inventoryImage;
	}
	
	public void setDurability(int d) 
	{
		this.durability = d;
	}
	
	public void used(){ //When an item is used, the item counter or the durability is reduced
		this.durability-=1;
	}
	
	public int getDurability() {
		return this.durability;
	}
	
	@Override 
	public boolean isObstacle() {
		return false;
	}
	
	@Override
	public boolean isPickable() {
		return true;
	}

}
