package model;

import java.util.ArrayList;
import java.util.Random;


public abstract class Mob extends Character implements Runnable, IAttackable {
	private static final long serialVersionUID = 42L;

	static Object lock = new Object();
	
	private transient Thread t;

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
	
	public void relaunch() {
		t = new Thread(this);
		t.start();
		removeAttackSprites();
	}
	
	public abstract void removeAttackSprites();
	
	@Override
	public void die() {
		synchronized(lock) {
			this.getGame().getGameObjects().remove(this);
			loot();
			t.interrupt();
		}
	}
	
	public void loot() {
		this.getGame().loot(this.posX, this.posY, 3);
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
	
	public void movePattern(Player player,Random random) {
		int mobX = this.getPosX();
		int mobY = this.getPosY();
		int playerX = player.getPosX();
		int playerY = player.getPosY();
		
		int moveCloserX = moveCloser(mobX,playerX);
		if (obstacle(mobX,mobY,moveCloserX,0)) {
			moveCloserX = 0;
		}
		int moveCloserY = moveCloser(mobY,playerY);
		if (obstacle(mobX,mobY,0,moveCloserY)) {
			moveCloserY = 0;
		}
		
		Direction directionX = directionFor(moveCloserX,0);
		Direction directionY = directionFor(0,moveCloserY);
		if ((moveCloserX!=0) && (moveCloserY!=0)) {
			int randomInt = random.nextInt(2); // 0 or 1
			if (randomInt == 0) {
				move(directionX);
			} else {
				move(directionY);
			}
		} else {
			if (directionX!=Direction.None) {
				move(directionX);
			}
			if (directionY!=Direction.None) {
				move(directionY);
			}
		}
	}
	
	private boolean obstacle(int mobPosX, int mobPosY, int xMove, int yMove) {
		boolean res = false;
		int newPosX = mobPosX+xMove;
		int newPosY = mobPosY+yMove;
		for (GameObject object:this.getGame().getGameObjects()) {
			if (object.getPosX() == newPosX && object.getPosY() == newPosY) {
				if (object.isObstacle()) {
					res = true;
				}
			}
		}
		return res;
	}
	
	private int moveCloser(int mobPos,int playerPos) {
		int res = 0;
		if (mobPos<playerPos) {
			res = 1;
		}
		if (mobPos>playerPos) {
			res = -1;
		}
		return res;
	}
	

}
