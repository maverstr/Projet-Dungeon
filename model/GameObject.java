package model;

import view.Sprite;

public abstract class GameObject {
	protected int posX;
	protected int posY;
	protected Sprite sprite;
	
	public GameObject(int x, int y, Sprite sprite){
		this.posX = x;
		this.posY = y;
		this.sprite = sprite;
		
	}
	public int getPosX(){
		return this.posX;
	}
	
	public int getPosY(){
		return this.posY;
	}
	
	public Sprite getSprite(){
		return this.sprite;
	}
	
	public boolean isAtPosition(int x, int y){
		return this.posX == x && this.posY == y;
	}
	
	public abstract boolean isObstacle();
}
