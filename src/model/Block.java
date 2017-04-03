package model;
//test
import java.awt.image.BufferedImage;

public abstract class Block extends GameObject{

	public Block(int x, int y, BufferedImage sprite) {
		super(x, y,sprite);
	}

	@Override
	public boolean isObstacle() {
		//Tous les blocks sont bloquants de base
		return true;
	}
	
	public boolean isMoveable() {
		//Les blocs ne peuvent pas être déplacés de base
		return false;
	}

}
