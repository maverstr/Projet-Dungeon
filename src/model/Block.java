package model;

import java.util.ArrayList;

public abstract class Block extends GameObject implements IObstacle, IMoveable, IOpenable, IExit  {
	
	private static final long serialVersionUID = 42L;

	public Block(int x, int y, Game game, ArrayList<Sprite> spriteList) {
		super(x, y, game, spriteList);
	}

	@Override
	public boolean isObstacle() {
		//Tous les blocks sont bloquants de base
		return true;
	}
	
	public boolean isMoveable() {
		//Les blocs ne peuvent pas être déplacés de base
		return false;
	}
	
	public boolean isOpenable(){
		return false;
	}
	
	public boolean isExit(){
		return false;
	}
	

}
