package model;

import java.awt.image.BufferedImage;


public abstract class Character extends GameObject{
	int posX;
	int posY;
	int Direction = 1;
	int Health = 5;
	//Weapon weapon = new Weapon();
	BufferedImage sprite;
	
	public Character(int x, int y , BufferedImage sprite){
		super(x,y,sprite);
		
	}
	public int getHealth(){
		return this.Health;
	}
//	public Weapon getWeapon(){
//		return this.weapon;
//	}
	public void move(){
		
	}
	public void attack(){
		
	}
	public void die(){
		
	}
	
}
