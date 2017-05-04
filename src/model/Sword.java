package model;

import java.io.File;

public class Sword extends Weapon {
	private static final long serialVersionUID = 42L;
	
	private static final File spriteFileInventory = new File(GameObject.class.getResource("/resources/sprites/sword_sprite.png").getFile());
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/sword_sprite.png").getFile());
	
	public Sword(int x, int y, Game game) {
		super(2, Sprite.imageFromFile(spriteFileInventory), x, y, game, Sprite.makeSpriteList(spriteFile, 0, 0, 0));
	}

	@Override
	public void use(int xPlayer, int yPlayer, Direction direction) {
		int newPosX = xPlayer+xForDirection(direction);
		int newPosY = yPlayer+yForDirection(direction);
		
		this.attack(newPosX, newPosY);
	}
	
	
	
	
}
