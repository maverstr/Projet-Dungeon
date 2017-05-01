package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Torch extends Item {
	
	private static final File spriteFileInventory = new File(GameObject.class.getResource("/resources/sprites/torch_sprite.png").getFile());
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/torch_sprite.png").getFile());

	public Torch( int x, int y, Game game) {
		super(Sprite.imageFromFile(spriteFileInventory), x, y, game, Sprite.makeSpriteList(spriteFile, 0, 0, 0));
	}

	@Override
	public void pickUp(Inventory inventory) {
		this.getGame().getGameObjects().remove(this);
		inventory.replacePassive(this);
		this.use();

	}
	
	@Override
	public void isPassive(){
		
	}
	
	@Override
	public void use(){
		CONSTANTS.CONSTANTS.setLINE_OF_SIGHT(7);
	}

}
