package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import CONSTANTS.CONSTANTS;

import java.util.Random;

public class Skeleton extends Mob implements Runnable {
	
	static int waitTime = 1000;
	int time = 0;
	long offset;

	public Skeleton(int x, int y, long threadOffset, Game game) throws IOException {
		super(x, y, game, ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/Skeleton.jpg").getFile())));
		this.offset = threadOffset;
		
	}
	
	@Override
	public void run(){
		try{
			Thread.sleep(offset);
			Random random = new Random();
			Player player = this.getGame().getPlayer();
			while(player.isAlive()){
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
				
				if ((moveCloserX!=0) && (moveCloserY!=0)) {
					int randomInt = random.nextInt(2); // 0 or 1
					if (randomInt == 0) {
						move(moveCloserX,0);
					} else {
						move(0,moveCloserY);
					}
				} else {
					move(moveCloserX,moveCloserY);
				}
				
				
				
				this.getGame().updateWindow();
				Thread.sleep(waitTime/2);
				
				//area attack
				this.attack(0, 1);
				this.attack(0, -1);
				this.attack(1, 0);
				this.attack(-1, 0);
				
				//System.out.print("time : "+time+"\n");
				Thread.sleep(waitTime/2);
				time+=waitTime;
			}
		}catch(Exception e){}; 
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
	
	private boolean obstacle(int mobPosX, int mobPosY, int xMove, int yMove) {
		boolean res = false;
		int newPosX = mobPosX+CONSTANTS.BLOCK_SIZE*xMove;
		int newPosY = mobPosY+CONSTANTS.BLOCK_SIZE*yMove;
		for (GameObject object:this.getGame().getGameObjects()) {
			if (object.getPosX() == newPosX && object.getPosY() == newPosY) {
				if (object.isObstacle()) {
					//System.out.println("obstacle for skeleton");
					res = true;
				}
			}
		}
		
		return res;
	}
	

}
