package model;

import java.util.ArrayList;

public abstract class GameObject {
	protected int posX;
	protected int posY;
	protected ArrayList<Sprite> spriteList;
	protected Direction direction = Direction.North; //0=up,1=right,2=down,3=left
	protected Game game;
	
	public GameObject(int x, int y, Game game, ArrayList<Sprite> spriteList) {
		this.posX = x;
		this.posY = y;
		this.spriteList = spriteList;
		this.game = game;
	}
	
	public void setPos(int posX,int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getPosX() {
		return this.posX;
	}
	
	public int getPosY() {
		return this.posY;
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public ArrayList<Sprite> getSpriteList() {
		return this.spriteList;
	}
	
	public boolean isAtPosition(int x, int y){
		return this.posX == x && this.posY == y;
	}
	
	public Game getGame() {
		return this.game;
	}
	
	public boolean isMoveable() {
		return false;
	}
	
	public boolean isPickable() {
		return false;
	}
	
	public boolean isOpenable() {
		return false;
	}
	
	public boolean isAttackable() {
		return false;
	}
	
	public abstract boolean isObstacle();
}

