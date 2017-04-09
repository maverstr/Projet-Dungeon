package model;

import java.io.File;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Zombie extends Mob {
	
	private static final int waitTime = 1000;
	long offset;
	
	private static final int maxHealth = 3;
	
	private static final File spriteFileU = new File(GameObject.class.getResource("/resources/sprites/Zombie_U.png").getFile());
	private static final File spriteFileR = new File(GameObject.class.getResource("/resources/sprites/Zombie_R.png").getFile());
	private static final File spriteFileD = new File(GameObject.class.getResource("/resources/sprites/Zombie_D.png").getFile());
	private static final File spriteFileL = new File(GameObject.class.getResource("/resources/sprites/Zombie_L.png").getFile());

	public Zombie(int x, int y, long threadOffset, Game game) throws IOException {
		super(x, y, game, ImageIO.read(spriteFileU),maxHealth);
		this.offset = threadOffset;
		
	}
	
	
	@Override
	public void run() {
		try{
			Thread.sleep(offset);
			Player player = this.getGame().getPlayer();
			while(player.isAlive()){
				int mobX = this.getPosX();
				int mobY = this.getPosY();
				int playerX = player.getPosX();
				int playerY = player.getPosY();
				
				int newDirection = setDirection(mobX,mobY,playerX,playerY);
				if (newDirection != -1) {
					this.direction = newDirection;
					System.out.println(newDirection);
					updateSpriteDirection(spriteFileU,spriteFileR,spriteFileD,spriteFileL);
				}
				this.getGame().updateWindow();
				Thread.sleep(waitTime/2);
				
				attackPattern();
				Thread.sleep(waitTime/2);
			}
		}catch(Exception e){}; 
	}
	
	@Override
	public void attackPattern() {
		//vomi : 2 cases
		switch (direction) {
			case 0:this.attack(0, -1);
				this.attack(0, -2);
				break;
			case 1:this.attack(1, 0);
				this.attack(2, 0);
				break;
			case 2:this.attack(0, 1);
				this.attack(0, 2);
				break;
			case 3:this.attack(-1, 0);
				this.attack(-2, 0);
				break;
		}
	}
	
	
	
	private int setDirection(int mobX,int mobY,int playerX,int playerY) {
		int res = -1;
		if ((mobX == playerX) && (mobY != playerY)) {
			if (mobY<playerY) {
				res = 2;
			}else{
				res = 0;
			}
		}
		if ((mobY == playerY) && (mobX != playerX)) {
			if (mobX<playerX) {
				res = 1;
			}else{
				res = 3;
			}
		}
		return res;
	}

}
