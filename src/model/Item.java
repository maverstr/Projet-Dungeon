package model;

import java.awt.image.BufferedImage;


//public abstract class Item extends GameObject {
public abstract class Item {
	protected BufferedImage sprite;
	protected int durability;
	
//	public Item(int x, int y, Game game, BufferedImage sprite) {
//		super(x, y, game, sprite);
//		// TODO Auto-generated constructor stub
//	}
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

//	@Override
//	public boolean isObstacle() {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
