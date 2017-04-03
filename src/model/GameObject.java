package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class GameObject {
	protected int posX;
	protected int posY;
	protected BufferedImage sprite;
	
	
	
	public GameObject(int x, int y, BufferedImage sprite){
		this.posX = x;
		this.posY = y;
		this.sprite = sprite;
		
	}
	
	public void setPos(int posX,int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getPosX(){
		return this.posX;
	}
	
	public int getPosY(){
		return this.posY;
	}
	
	public BufferedImage getSprite(){
		return this.sprite;
	}
	
	public boolean isAtPosition(int x, int y){
		return this.posX == x && this.posY == y;
	}
	
	public abstract boolean isObstacle();
}

