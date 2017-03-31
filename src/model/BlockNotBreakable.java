package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class BlockNotBreakable extends Block {
	//private static final Sprite BlockNotBreakableSprite = null;

	
	public BlockNotBreakable(int x, int y) throws IOException {
		super(x, y, ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/Haelti_Head.jpg").getFile())));
		
	}


}
