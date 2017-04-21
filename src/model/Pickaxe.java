package model;

import java.io.File;

public class Pickaxe extends Weapon {
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/pickaxe_sprite.png").getFile());

	public Pickaxe(int x, int y, Game game) {
		super(1, Sprite.imageFromFile(spriteFile), x, y, game, Sprite.makeSpriteList(spriteFile, 0, 0, 0));
	}
	
	public boolean breakBlockAbility() {
		return true;
	}
	

}
