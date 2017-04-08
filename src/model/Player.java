package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import CONSTANTS.CONSTANTS;

public class Player extends Character {
	public Player(int x, int y, Game game) throws IOException {
		super(x, y, game, ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/Hugues_Head.jpg").getFile())));
	}

	@Override
	public boolean isObstacle() {
		return true;
	}
	
	public void hit(int xHit,int yHit) {
		this.itemType = 1;
		
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
		die();
	}
	
	public void die() {
		this.getGame().getGameObjects().remove(this);
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
