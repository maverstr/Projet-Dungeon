package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BlockMoveable extends Block {
	
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/Block_Moveable.png").getFile());

	public BlockMoveable(int x, int y, Game game) throws IOException {
		super(x, y, game, ImageIO.read(spriteFile));
		
	}
	
	public void move(int xMove,int yMove){
		this.posX += xMove;
		this.posY += yMove;
	}
	
	public boolean isMoveable() {
		return true;
	}

}
