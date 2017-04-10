package model;

import java.awt.image.BufferedImage;


//public abstract class Item extends GameObject {
public abstract class Item {
	protected BufferedImage sprite;
	protected int durability;
	
	public Item(BufferedImage sprite) {
		this.sprite=sprite;
		this.durability=1;
	}
	
	
	
	public BufferedImage getSprite() {
		return this.sprite;
	}
	
	public void setDurability(int d) 
	{
		this.durability = d;
	}
	
	public int getDurability() {
		return this.durability;
	}


}
