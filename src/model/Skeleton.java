package model;

import java.io.File;

import java.util.Random;

public class Skeleton extends Mob implements Runnable {
	
	static final int waitTime = 1000;
	long offset;
	
	private static final int maxHealth = 2;
	
	private static final File spriteFileU = new File(GameObject.class.getResource("/resources/sprites/Skeleton_U.png").getFile());
	private static final File spriteFileR = new File(GameObject.class.getResource("/resources/sprites/Skeleton_R.png").getFile());
	private static final File spriteFileD = new File(GameObject.class.getResource("/resources/sprites/Skeleton_D.png").getFile());
	private static final File spriteFileL = new File(GameObject.class.getResource("/resources/sprites/Skeleton_L.png").getFile());

	public Skeleton(int x, int y, long threadOffset, Game game) {
		super(x, y, game, Sprite.makeSpriteList(spriteFileU,0,0,0),maxHealth);
		this.offset = threadOffset;
		
	}
	
	@Override
	public void run(){
		try{
			Thread.sleep(offset);
			Random random = new Random();
			Player player = this.getGame().getPlayer();
			while(player.isAlive()){
				if (game.state == Game.STATE.RUN) {
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
					updateSpriteDirection(spriteFileU,spriteFileR,spriteFileD,spriteFileL);
					
					this.getGame().updateWindow();
					Thread.sleep(waitTime/2);
					
					attackPattern();
					
					
				}
				Thread.sleep(waitTime/2);
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
	
	private synchronized boolean obstacle(int mobPosX, int mobPosY, int xMove, int yMove) {
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
	
	@Override
	public void attackPattern() {
		//area attack
		this.attack(0, 1);
		this.attack(0, -1);
		this.attack(1, 0);
		this.attack(-1, 0);
	}
	

}
