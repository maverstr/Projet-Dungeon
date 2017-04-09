package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class BlockNotBreakable extends Block {
	//private static final Sprite BlockNotBreakableSprite = null;

	
	public BlockNotBreakable(int x, int y, Game game) throws IOException {
		super(x, y, game, ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/Block_Unbreakable.png").getFile())));
		
	}


}
