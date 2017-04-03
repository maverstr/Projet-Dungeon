package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import CONSTANTS.CONSTANTS;

public class Player extends Character {
	public Player(int x, int y) throws IOException {
		super(x, y, ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/Hugues_Head.jpg").getFile())));
	}

	@Override
	public boolean isObstacle() {
		return false;
	}
	
	public void hit(int xHit,int yHit,ArrayList<GameObject> objects) {
		this.itemType = 2;
		if (this.itemType == 2) {
			this.mine(xHit,yHit,objects);
		}
	}
	
	public void attack(int xAttack,int yAttack) {
		
	}
	
	public void mine(int xMine,int yMine,ArrayList<GameObject> objects) {
		int newPosX = this.posX+CONSTANTS.BLOCK_SIZE*xMine;
		int newPosY = this.posY+CONSTANTS.BLOCK_SIZE*yMine;
		
		for (GameObject object:objects) {
			if (object.getPosX() == newPosX && object.getPosY() == newPosY) {
				if (object instanceof BlockBreakable) {
					System.out.println("break");
					BlockBreakable block = (BlockBreakable) object;
					block.toBreak();
					objects.remove(object);
					break;
				}
			}
		}
	}
	
	
}
