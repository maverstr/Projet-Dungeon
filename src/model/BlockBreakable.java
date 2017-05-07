package model;

import java.io.File;

public class BlockBreakable extends Block implements IBreakable {
	
	private static final File spriteFileF = new File(GameObject.class.getResource("/resources/sprites/Block_Breakable_Foreground.png").getFile());
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/Block_Breakable.png").getFile());

	public BlockBreakable(int x, int y, Game game) {
		super(x, y, game, Sprite.makeSpriteList(spriteFile,0,0,3,spriteFileF,0,-0.5,10));
		
	}
	
	@Override
	public boolean isBreakable() {
		return true;
	}
	
	public synchronized void toBreak() {
		this.getGame().removeGameObject(this);
	}


}
