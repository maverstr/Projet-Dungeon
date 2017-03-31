package model;

import java.awt.image.BufferedImage;


public abstract class Item extends GameObject {

	public Item(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isObstacle() {
		// TODO Auto-generated method stub
		return false;
	}

}
