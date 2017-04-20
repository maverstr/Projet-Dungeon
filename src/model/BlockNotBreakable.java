package model;

import java.io.File;

public class BlockNotBreakable extends Block {
	
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/Block_Unbreakable.png").getFile());
	
	public BlockNotBreakable(int x, int y, Game game) {
		super(x, y, game, Sprite.makeSpriteList(spriteFile,0,0,0));
		
	}


}
