package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class BlockBreakable extends Block implements Breakable {

	public BlockBreakable(int x, int y, Game game) throws IOException{
		super(x, y, game, ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/Block_Breakable.png").getFile())));
		
	}
	
	public boolean isBreakable() {
		return true;
	}
	
	public void toBreak() {
		this.getGame().removeGameObject(this);
	}


}
