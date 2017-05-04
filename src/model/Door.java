package model;

import java.io.File;

public class Door extends Block implements IObstacle, IExit{
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/Door.jpg").getFile());

	public Door(int x, int y, Game game) {
		super(x, y, game, Sprite.makeSpriteList(spriteFile,0,0,0));
	}
	
	@Override
	public boolean isObstacle(){
		return false;
	}
	
	public boolean isExit(){
		return true;
	}

}
