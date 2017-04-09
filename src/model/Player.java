package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JProgressBar;

import CONSTANTS.CONSTANTS;
import view.PlayerState;

public class Player extends Character {
	
	JProgressBar healthBar = PlayerState.healthBar;
	
	private boolean alive = true;
	
	private static final int maxHealth = 5;
	
	private static final File spriteFileU = new File(GameObject.class.getResource("/resources/sprites/Player_U.png").getFile());
	private static final File spriteFileR = new File(GameObject.class.getResource("/resources/sprites/Player_R.png").getFile());
	private static final File spriteFileD = new File(GameObject.class.getResource("/resources/sprites/Player_D.png").getFile());
	private static final File spriteFileL = new File(GameObject.class.getResource("/resources/sprites/Player_L.png").getFile());
	
	public Player(int x, int y, Game game) throws IOException {
		super(x, y, game, ImageIO.read(spriteFileU),maxHealth);
		this.itemType = 1;
	}

	@Override
	public boolean isObstacle() {
		return true;
	}
	
	public void tryToMove(int xMove, int yMove) {
		boolean obstacle = false;
		
		int newPosX = posX+CONSTANTS.BLOCK_SIZE*xMove;
		int newPosY = posY+CONSTANTS.BLOCK_SIZE*yMove;
		
		int blockMoveableNewPosX = posX+2*CONSTANTS.BLOCK_SIZE*xMove;
		int blockMoveableNewPosY = posY+2*CONSTANTS.BLOCK_SIZE*yMove;
		
		for (GameObject object:this.getGame().getGameObjects()) {
			if (object.getPosX() == newPosX && object.getPosY() == newPosY) {
				if (object.isObstacle()) {
					System.out.println("obstacle");
					obstacle = true;
					if (object instanceof Block) {
						Block block = (Block) object;
						if (block.isMoveable()) {
							System.out.println("moveable");
							if (freeSpace(blockMoveableNewPosX,blockMoveableNewPosY)) {
								System.out.println("freespace");
								BlockMoveable blockMoveable = (BlockMoveable) block;
								move(xMove, yMove);
								blockMoveable.move(xMove, yMove);
								updateSpriteDirection(spriteFileU,spriteFileR,spriteFileD,spriteFileL);
							}
						}
					}
				}
			}
		}
		if (!obstacle) {
			move(xMove, yMove);
			updateSpriteDirection(spriteFileU,spriteFileR,spriteFileD,spriteFileL);
		}
		
		this.getGame().updateWindow();
	}
	
	private boolean freeSpace(int x, int y) {
		boolean res = true;
		for (GameObject object:this.getGame().getGameObjects()) {
			if (object.getPosX() == x && object.getPosY() == y) {
				if (object.isObstacle()) {
					res = false;
					break;
				}
			}
		}
		return res;
	}
	
	public void changeTool() {
		if (this.itemType == 1) {
			this.itemType = 2;
			System.out.println("pickaxe");
		} else {
			this.itemType = 1;
			System.out.println("weapon");
		}
	}
	
	public void hit(int xHit,int yHit) {
		this.setMoveDirection(xHit, yHit);
		updateSpriteDirection(spriteFileU,spriteFileR,spriteFileD,spriteFileL);
		switch (this.itemType) {
			case 1: this.attack(xHit, yHit);
				break;
			case 2: this.mine(xHit,yHit);
				break;
		}
	}
	
	public void attack(int xAttack,int yAttack) {
		int newPosX = this.posX+CONSTANTS.BLOCK_SIZE*xAttack;
		int newPosY = this.posY+CONSTANTS.BLOCK_SIZE*yAttack;
		
		for (GameObject object:this.getGame().getGameObjects()) {
			if (object.getPosX() == newPosX && object.getPosY() == newPosY) {
				if (object instanceof Mob) {
					System.out.println("mob attacked");
					Mob mob = (Mob) object;
					mob.wasHit();
					break;
				}
			}
		}
	}
	
	public void wasHit() {
		this.health--;
		System.out.println("HEALTH : " + this.health + "\n");
		PlayerState.barUpdate(this.health);
		if (this.health<=0) {
			die();
		}
	}
	
	public boolean isAlive() {
		return this.alive;
	}
	
	public void die() {
		this.alive = false;
		this.getGame().getGameObjects().remove(this);
		this.getGame().updateWindow();
		System.out.println("GAME OVER-------GET REKT-------YOU MAD BRO??");
	}
	
	
	public void mine(int xMine,int yMine) {
		int newPosX = this.posX+CONSTANTS.BLOCK_SIZE*xMine;
		int newPosY = this.posY+CONSTANTS.BLOCK_SIZE*yMine;
		
		for (GameObject object:this.getGame().getGameObjects()) {
			if (object.getPosX() == newPosX && object.getPosY() == newPosY) {
				if (object instanceof BlockBreakable) {
					System.out.println("break");
					BlockBreakable block = (BlockBreakable) object;
					block.toBreak();
					break;
				}
			}
		}
	}
	
	
}
