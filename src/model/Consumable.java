package model;

import java.awt.image.BufferedImage;

public class Consumable extends Item {
	public Consumable(BufferedImage sprite){
		super(sprite);
	}
	
	public String getType() {
		return String.format("%s",this.getClass());
	}
}
