package model;

import java.awt.image.BufferedImage;

public abstract class Character extends GameObject{

	int Direction = 1;
	int Health = 5;
	int itemType = 0; //1->weapon, 2->pickaxe
	//Weapon weapon = new Weapon();
	BufferedImage sprite;
	
	public Character(int x, int y, Game game, BufferedImage sprite){
		super(x,y,game,sprite);
		
	}
	public int getHealth(){
		return this.Health;
	}
//	public Weapon getWeapon(){
//		return this.weapon;
//	}
	public void move(int xMove,int yMove){
		this.posX += CONSTANTS.CONSTANTS.BLOCK_SIZE*xMove;
		this.posY += CONSTANTS.CONSTANTS.BLOCK_SIZE*yMove;
	}
	
	public abstract void attack(int xAttack,int yAttack);
	
	public abstract void wasHit();
	
	public abstract void die();
	
}
