package model;

import java.util.ArrayList;

public abstract class Mob extends Character implements Runnable {
	
	Thread t;

	public Mob(int x, int y, Game game, ArrayList<Sprite> spriteList, int health, boolean isBaptized) {
		super(x, y, game, spriteList,health,isBaptized);
		t = new Thread(this);
		t.start();
	}
	
	public void attack(int xAttack, int yAttack) {
		int newPosX = this.posX+xAttack;
		int newPosY = this.posY+yAttack;
		
		Player player = this.getGame().getPlayer();
		if (player.getPosX() == newPosX && player.getPosY() == newPosY) {
			player.wasHit(1);
		}
	}
	
	public abstract void attackPattern();
	
	@Override
	public synchronized void die() {
		if (!this.getGame().getGameObjects().remove(this)) {
			System.out.println(this); //TODO : ces putains de lasers
			this.getSpriteList().get(0).setOffset(0.2, 0.2);
		}
		loot();
		t.interrupt();
	}
	
	public void loot() {
		this.getGame().loot(this.posX, this.posY, 13,false);
	}
	
	public abstract void run();
	
	@Override
	public boolean isAttackable() {
		return true;
	}
	
	public Direction setDirection(int mobX,int mobY,int playerX,int playerY) {
		Direction direction = Direction.None;
		if ((mobX == playerX) && (mobY != playerY)) {
			if (mobY<playerY) {
				direction = Direction.South;
			}else{
				direction = Direction.North;
			}
		}
		if ((mobY == playerY) && (mobX != playerX)) {
			if (mobX<playerX) {
				direction = Direction.East;
			}else{
				direction = Direction.West;
			}
		}
		return direction;
	}
	

}
