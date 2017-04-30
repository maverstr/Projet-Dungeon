package model;

import java.io.File;

import java.util.ArrayList;

public abstract class Character extends GameObject{
	
	protected int maxHealth;
	protected int health;
	protected boolean isBaptized;
	protected int penneSpriteIndex;
	
	//int itemType = 0; //1->weapon, 2->pickaxe
	//Weapon weapon = new Weapon();
	
	public Character(int x, int y, Game game, ArrayList<Sprite> spriteList, int maxHealth, boolean isBaptized){
		super(x,y,game,spriteList);
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		if (isBaptized) {
			putPenne();
		}
	}
	
	@Override
	public boolean isObstacle() {
		return true;
	}
	
	public int getHealth(){
		return this.health;
	}
	


	public void move(int xMove,int yMove){
		this.posX += xMove;
		this.posY += yMove;
		setMoveDirection(xMove,yMove);
	}
	
	public void setMoveDirection(int xMove, int yMove) {
		if (yMove<0) {
			this.direction = Direction.North;
		}
		if (xMove>0) {
			this.direction = Direction.East;
		}
		if (yMove>0) {
			this.direction = Direction.South;
		}
		if (xMove<0) {
			this.direction = Direction.West;
		}
	}
	
	public abstract void attack(int xAttack,int yAttack);
	
	public void wasHit(int damage) {
		this.health-=damage;
		if (this.health<=0) {
			die();
		}
	}
	
	public abstract void die();
	
	public void updateSpriteDirection(File up,File right,File down,File left) {
		Sprite sprite = spriteList.get(0);
		try {
			switch (this.direction) {
			case North:sprite.setImageFromFile(up);
				break;
			case East:sprite.setImageFromFile(right);
				break;
			case South:sprite.setImageFromFile(down);
				break;
			case West:sprite.setImageFromFile(left);
				break;
			default:
				break;
			}
		} catch (Exception e) {e.printStackTrace();}
		if (this.isBaptized) {
			updatePenneDirection();
		}
	}
	
	public void updatePenneDirection() {
		try {
			Sprite sprite = spriteList.get(penneSpriteIndex);
			switch (this.direction) {
			case North:sprite.setImageFromFile(Penne.getFileUp());
				break;
			case East:sprite.setImageFromFile(Penne.getFileRight());
				break;
			case South:sprite.setImageFromFile(Penne.getFileDown());
				break;
			case West:sprite.setImageFromFile(Penne.getFileLeft());
				break;
			default:
				break;
			}
		} catch (Exception e) {}
	}
	
	public void putPenne() {
		if (!this.isBaptized) {
			this.isBaptized = true;
			this.spriteList.add(Sprite.makeSpriteFromFile(Penne.getFileRight(), 0, -0.45, 2));
			this.penneSpriteIndex = spriteList.size()-1;
			updatePenneDirection();
		}
	}
	
	
}
