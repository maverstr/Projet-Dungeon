package model;

import java.io.File;

public class Zombie extends Mob {
	private static final long serialVersionUID = 42L;
	
	private static final int waitTime = 1000;
	long offset;
	
	private static final int maxHealth = 3;
	
	private static final File spriteFileU = new File(GameObject.class.getResource("/resources/sprites/Zombie_U.png").getFile());
	private static final File spriteFileR = new File(GameObject.class.getResource("/resources/sprites/Zombie_R.png").getFile());
	private static final File spriteFileD = new File(GameObject.class.getResource("/resources/sprites/Zombie_D.png").getFile());
	private static final File spriteFileL = new File(GameObject.class.getResource("/resources/sprites/Zombie_L.png").getFile());
	
	private static final File spriteFileVomit1U = new File(GameObject.class.getResource("/resources/sprites/Zombie_Vomit_1U.png").getFile());
	private static final File spriteFileVomit1R = new File(GameObject.class.getResource("/resources/sprites/Zombie_Vomit_1R.png").getFile());
	private static final File spriteFileVomit1D = new File(GameObject.class.getResource("/resources/sprites/Zombie_Vomit_1D.png").getFile());
	private static final File spriteFileVomit1L = new File(GameObject.class.getResource("/resources/sprites/Zombie_Vomit_1L.png").getFile());
	
	private static final File spriteFileVomit2U = new File(GameObject.class.getResource("/resources/sprites/Zombie_Vomit_2U.png").getFile());
	private static final File spriteFileVomit2R = new File(GameObject.class.getResource("/resources/sprites/Zombie_Vomit_2R.png").getFile());
	private static final File spriteFileVomit2D = new File(GameObject.class.getResource("/resources/sprites/Zombie_Vomit_2D.png").getFile());
	private static final File spriteFileVomit2L = new File(GameObject.class.getResource("/resources/sprites/Zombie_Vomit_2L.png").getFile());
	
	private Sprite attackSprite1;
	private Sprite attackSprite2;

	public Zombie(int x, int y, long threadOffset, Game game, boolean isBaptized) {
		super(x, y, game, Sprite.makeSpriteList(spriteFileU,0,-0.25,7),maxHealth,isBaptized);
		this.offset = threadOffset;
	}
	
	
	@Override
	public void run() {
		try{
			Thread.sleep(offset);
			Player player = this.getGame().getPlayer();
			while(player.isAlive()){
				if (game.getState() == Game.STATE.RUN) {
					int mobX = this.getPosX();
					int mobY = this.getPosY();
					int playerX = player.getPosX();
					int playerY = player.getPosY();
					
					Direction newDirection = setDirection(mobX,mobY,playerX,playerY);
					if (newDirection != Direction.None) {
						this.direction = newDirection;
						updateSpriteDirection(spriteFileU,spriteFileR,spriteFileD,spriteFileL);
					}
					this.getGame().updateWindow();
					Thread.sleep(waitTime/2);
					
					if (!this.isBaptized || !this.getGame().getPlayer().isBaptized) {
						attackPattern();
						this.getGame().updateWindow();
					}
					
					Thread.sleep(waitTime/2);
					this.spriteList.remove(attackSprite1); //remove the attack sprites
					this.spriteList.remove(attackSprite2);
				} else {
					Thread.sleep(100);
				}
				
			}
		}catch(Exception e){}; 
	}

	@Override
	public void attackPattern() {
		//vomi : 2 cases
		switch (direction) {
		case North:
			this.attack(0, -1);
			this.attack(0, -2);
			attackSprite1 = Sprite.makeSpriteFromFile(spriteFileVomit1U, 0, -1, 6);
			attackSprite2 = Sprite.makeSpriteFromFile(spriteFileVomit2U, 0, -2, 11);
			this.spriteList.add(attackSprite1);
			this.spriteList.add(attackSprite2);
			break;
		case East:
			this.attack(1, 0);
			this.attack(2, 0);
			attackSprite1 = Sprite.makeSpriteFromFile(spriteFileVomit1R, 0.75, -0.35, 11);
			attackSprite2 = Sprite.makeSpriteFromFile(spriteFileVomit2R, 1.75, -0.35, 11);
			this.spriteList.add(attackSprite1);
			this.spriteList.add(attackSprite2);
			break;
		case South:
			this.attack(0, 1);
			this.attack(0, 2);
			attackSprite1 = Sprite.makeSpriteFromFile(spriteFileVomit1D, 0, 0.2, 11);
			attackSprite2 = Sprite.makeSpriteFromFile(spriteFileVomit2D, 0, 1.2, 11);
			this.spriteList.add(attackSprite1);
			this.spriteList.add(attackSprite2);
			break;
		case West:
			this.attack(-1, 0);
			this.attack(-2, 0);
			attackSprite1 = Sprite.makeSpriteFromFile(spriteFileVomit1L, -0.75, -0.35, 11);
			attackSprite2 = Sprite.makeSpriteFromFile(spriteFileVomit2L, -1.75, -0.35, 11);
			this.spriteList.add(attackSprite1);
			this.spriteList.add(attackSprite2);
			break;
		default:
			break;
		}
	}
	

}
