package model;

import java.io.File;
import java.io.IOException;

public class BlockMoveable extends Block {
	
	private static final File spriteFileF = new File(GameObject.class.getResource("/resources/sprites/Block_Moveable_Foreground.png").getFile());
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/Block_Moveable.png").getFile());

	public BlockMoveable(int x, int y, Game game) throws IOException {
		super(x, y, game, Sprite.makeSpriteList(spriteFile,0,0,0,spriteFileF,0,-0.5,1));
		
	}
	
	public void move(int xMove,int yMove){
		this.posX += xMove;
		this.posY += yMove;
	}
	
	public boolean isMoveable() {
		return true;
	}

}
