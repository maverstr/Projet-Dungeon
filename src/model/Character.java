package model;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public abstract class Character extends GameObject{
	
	protected int health;
	
	int itemType = 0; //1->weapon, 2->pickaxe
	//Weapon weapon = new Weapon();
	
	public Character(int x, int y, Game game, BufferedImage sprite, int health){
		super(x,y,game,sprite);
		this.health = health;
	}
	public int getHealth(){
		return this.health;
	}
//	public Weapon getWeapon(){
//		return this.weapon;
//	}
	public void move(int xMove,int yMove){
		this.posX += CONSTANTS.CONSTANTS.BLOCK_SIZE*xMove;
		this.posY += CONSTANTS.CONSTANTS.BLOCK_SIZE*yMove;
		setMoveDirection(xMove,yMove);
	}
	
	public void setMoveDirection(int xMove, int yMove) {
		if (yMove<0) {
			this.direction = 0;
		}
		if (xMove>0) {
			this.direction = 1;
		}
		if (yMove>0) {
			this.direction = 2;
		}
		if (xMove<0) {
			this.direction = 3;
		}
	}
	
	public abstract void attack(int xAttack,int yAttack);
	
	public abstract void wasHit();
	
	public abstract void die();
	
	public void updateSpriteDirection(File up,File right,File down,File left) {
		try {
			switch (this.direction) {
			case 0:this.sprite = ImageIO.read(up);
				break;
			case 1:this.sprite = ImageIO.read(right);
				break;
			case 2:this.sprite = ImageIO.read(down);
				break;
			case 3:this.sprite = ImageIO.read(left);
				break;
			}
		} catch (Exception e) {}
		
	}
	
}
