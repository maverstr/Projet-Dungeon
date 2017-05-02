package model;

import java.io.File;

public class BlockMoveable extends Block {
	
	private static final File spriteFileF = new File(GameObject.class.getResource("/resources/sprites/Block_Moveable_Foreground.png").getFile());
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/Block_Moveable.png").getFile());

	public BlockMoveable(int x, int y, Game game) {
		super(x, y, game, Sprite.makeSpriteList(spriteFile,0,0,3,spriteFileF,0,-0.5,10));
		
	}
	
	public void move(Direction direction){
		this.posX += xForDirection(direction);
		this.posY += yForDirection(direction);
	}
	
	@Override
	public boolean isMoveable() {
		return true;
	}

}
