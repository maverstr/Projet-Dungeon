package model;

import java.io.File;

public class Sword extends Weapon {
	private static final File spriteFileInventory = new File(GameObject.class.getResource("/resources/sprites/sword_sprite.png").getFile());
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/sword_sprite.png").getFile());
	
	public Sword(int x, int y, Game game) {
		super(2, Sprite.imageFromFile(spriteFileInventory), x, y, game, Sprite.makeSpriteList(spriteFile, 0, 0, 0));
	}

	@Override
	public void use(int xPlayer, int yPlayer, int xUseWeapon, int yUseWeapon) {
		int newPosX = xPlayer+xUseWeapon;
		int newPosY = yPlayer+yUseWeapon;
		
		this.attack(newPosX, newPosY);
	}
	
	
	
	
}
