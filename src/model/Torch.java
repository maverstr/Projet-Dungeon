package model;

import java.io.File;


public class Torch extends Item implements IPassive{
	private static final long serialVersionUID = 42L;

	
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
	public boolean isPassive(){
		return true;
		
	}
	
	@Override
	public void use(){
		CONSTANTS.CONSTANTS.setLINE_OF_SIGHT(7);
		this.getGame().setLineOfSight(7);
	}

}
