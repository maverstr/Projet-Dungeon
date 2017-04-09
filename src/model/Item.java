package model;

import java.awt.image.BufferedImage;


//public abstract class Item extends GameObject {
public abstract class Item {
	protected BufferedImage sprite;
	
//	public Item(int x, int y, Game game, BufferedImage sprite) {
//		super(x, y, game, sprite);
//		// TODO Auto-generated constructor stub
//	}
	public Item(BufferedImage sprite) {
		this.sprite=sprite;
	}

//	@Override
//	public boolean isObstacle() {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
