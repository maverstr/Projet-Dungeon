package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import view.Map;


public class BlockBreakable extends Block implements Breakable {

	public BlockBreakable(int x, int y) throws IOException{
		super(x, y, ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/blockBreakable_sprite.png").getFile())));
		
	}
	
	public boolean isBreakable() {
		return true;
	}
	
	public void toBreak() {
		
	}


}
