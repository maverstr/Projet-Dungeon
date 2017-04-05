package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BlockMoveable extends Block {

	public BlockMoveable(int x, int y) throws IOException {
		super(x, y, ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/Block_Moveable.png").getFile())));
		
	}
	
	public void move(int xMove,int yMove){
		this.posX += CONSTANTS.CONSTANTS.BLOCK_SIZE*xMove;
		this.posY += CONSTANTS.CONSTANTS.BLOCK_SIZE*yMove;
	}
	
	public boolean isMoveable() {
		return true;
	}

}
