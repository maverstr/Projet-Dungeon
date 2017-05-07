package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Skeleton extends Mob implements Runnable {
	private static final long serialVersionUID = 42L;
	
	static final int waitTime = 1000;
	long offset;
	
	private static final int maxHealth = 2;
	
	private static final File spriteFileU = new File(GameObject.class.getResource("/resources/sprites/Skeleton_U.png").getFile());
	private static final File spriteFileR = new File(GameObject.class.getResource("/resources/sprites/Skeleton_R.png").getFile());
	private static final File spriteFileD = new File(GameObject.class.getResource("/resources/sprites/Skeleton_D.png").getFile());
	private static final File spriteFileL = new File(GameObject.class.getResource("/resources/sprites/Skeleton_L.png").getFile());
	
	private static final File spriteFileBoneU = new File(GameObject.class.getResource("/resources/sprites/BoneU.png").getFile());
	private static final File spriteFileBoneR = new File(GameObject.class.getResource("/resources/sprites/BoneR.png").getFile());
	private static final File spriteFileBoneD = new File(GameObject.class.getResource("/resources/sprites/BoneD.png").getFile());
	private static final File spriteFileBoneL = new File(GameObject.class.getResource("/resources/sprites/BoneL.png").getFile());
	
	private Sprite attackSprite;

	public Skeleton(int x, int y, long threadOffset, Game game, boolean isBaptized) {
		super(x, y, game, Sprite.makeSpriteList(spriteFileU,0,-0.25,7),maxHealth,isBaptized);
		this.offset = threadOffset;
		
	}
	
	@Override
	public void run(){
		try{
			Thread.sleep(offset);
			Random random = new Random();
			Player player = this.getGame().getPlayer();
			while(player.isAlive()){
				if (game.getState() == Game.STATE.RUN) {
					int playerX = player.getPosX();
					int playerY = player.getPosY();
					
					movePattern(player,random);
					updateSpriteDirection(spriteFileU,spriteFileR,spriteFileD,spriteFileL);
					
					this.getGame().updateWindow();
					Thread.sleep(waitTime/2);
					
					int newMobX = this.getPosX();
					int newMobY = this.getPosY();
					
					Direction newDirection = setDirection(newMobX,newMobY,playerX,playerY);
					if (newDirection != Direction.None) {
						setDirection(newDirection);
						updateSpriteDirection(spriteFileU,spriteFileR,spriteFileD,spriteFileL);
					}
					
					if (!this.isBaptized || !this.getGame().getPlayer().isBaptized) {
						attackPattern();
						this.getGame().updateWindow();
					}
					Thread.sleep(waitTime/2);
					this.getSpriteList().remove(attackSprite); //remove the attack sprites
				} else {
					Thread.sleep(100);
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}; 
	}
	
	@Override
	public void attackPattern() {
		ArrayList<Sprite> spriteList = this.getSpriteList();
		switch (getDirection()) {
		case North:
			this.attack(0, -1);
			attackSprite = Sprite.makeSpriteFromFile(spriteFileBoneU, 0, -0.75, 6);
			spriteList.add(attackSprite);
			break;
		case East:
			this.attack(1, 0);
			attackSprite = Sprite.makeSpriteFromFile(spriteFileBoneR, 0.75, -0.25, 11);
			spriteList.add(attackSprite);
			break;
		case South:
			this.attack(0, 1);
			attackSprite = Sprite.makeSpriteFromFile(spriteFileBoneD, 0, 0.25, 8);
			spriteList.add(attackSprite);
			break;
		case West:
			this.attack(-1, 0);
			attackSprite = Sprite.makeSpriteFromFile(spriteFileBoneL, -0.75, -0.25, 11);
			spriteList.add(attackSprite);
			break;
		default:
			break;
		}
	}

	@Override
	public void removeAttackSprites() {
		this.getSpriteList().remove(attackSprite);
	}
	

}
