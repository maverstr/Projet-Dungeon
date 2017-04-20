package model;

import java.io.File;

public class Chest extends Block {
	
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/Chest.png").getFile());

	public Chest(int x, int y, Game game) {
		super(x, y, game, Sprite.makeSpriteList(spriteFile,0,0,0));
		
	}

}
