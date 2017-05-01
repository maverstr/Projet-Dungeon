package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Torch extends Item {
	
	private static final File spriteFileInventory = new File(GameObject.class.getResource("/resources/sprites/sword_sprite.png").getFile());
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/sword_sprite.png").getFile());

	public Torch( int x, int y, Game game) {
		super(Sprite.imageFromFile(spriteFileInventory), x, y, game, Sprite.makeSpriteList(spriteFile, 0, 0, 0));
	}

	@Override
	public void pickUp(Inventory inventory) {

	}
	
	public void isPassive(){
		
	}
	
	public void use(){
		CONSTANTS.CONSTANTS.setLINE_OF_SIGHT(8);
	}

}
