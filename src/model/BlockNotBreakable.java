package model;

import java.io.File;

public class BlockNotBreakable extends Block {
	
	private static final long serialVersionUID = 42L;
	
	private static final File spriteFileF = new File(GameObject.class.getResource("/resources/sprites/Block_Unbreakable_Foreground.png").getFile());
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/Block_Unbreakable.png").getFile());
	
	public BlockNotBreakable(int x, int y, Game game) {
		super(x, y, game, Sprite.makeSpriteList(spriteFile,0,0,3,spriteFileF,0,-0.5,10));
	}

}
