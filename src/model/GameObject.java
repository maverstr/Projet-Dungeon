package model;

import java.awt.image.BufferedImage;

public abstract class GameObject {
	protected int posX;
	protected int posY;
	protected BufferedImage sprite;
	protected int direction = 0; //0=up,1=right,2=down,3=left
	private Game game;
	
	public GameObject(int x, int y, Game game, BufferedImage sprite) {
		this.posX = x;
		this.posY = y;
		this.sprite = sprite;
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
	
	public int getDirection() {
		return this.direction;
	}
	
	public BufferedImage getSprite() {
		return this.sprite;
	}
	
	public boolean isAtPosition(int x, int y){
		return this.posX == x && this.posY == y;
	}
	
	public Game getGame() {
		return this.game;
	}
	
	public abstract boolean isObstacle();
}

