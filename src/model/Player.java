package model;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import CONSTANTS.CONSTANTS;

public class Player extends Character {
	
	
	private boolean alive = true;
	
	private static final int maxHealth = 50;
	private Inventory inventory;
	
	private static final File spriteFileU = new File(GameObject.class.getResource("/resources/sprites/Player_U.png").getFile());
	private static final File spriteFileR = new File(GameObject.class.getResource("/resources/sprites/Player_R.png").getFile());
	private static final File spriteFileD = new File(GameObject.class.getResource("/resources/sprites/Player_D.png").getFile());
	private static final File spriteFileL = new File(GameObject.class.getResource("/resources/sprites/Player_L.png").getFile());
	
	public Player(int x, int y, Game game) throws IOException {
		super(x, y, game, ImageIO.read(spriteFileU),maxHealth);
		
		this. inventory = new Inventory();
	//Add weapons and items to the Player at the beginning of the Game.
		this.inventory.addWeapon(new Sword());
		this.inventory.addWeapon(new Sword()); // adds a second sword -> prints an error message cause there cant be two same weapons
		this.inventory.addWeapon(new Pickaxe());
		this.inventory.addConsumable(new Potion(Potion.potionType.vie)); //Note the type of potion
		this.inventory.addConsumable(new Potion(Potion.potionType.vie));
		this.inventory.addConsumable(new Potion(Potion.potionType.mana));
		
		this.inventory.setWeaponIndex(0); //Select The Sword as the beginning weapon at start.

	}
	
	public Inventory getInventory() 
	{
		return this.inventory;
	}
	
	public int getMaxHealth(){
		return maxHealth;
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
	
	
	
	public void changeTool() { //When "A" is pressed, CYCLE trough the weapons Sword OR pickaxe 
		
		// TODO Not Cycle but Selection with KEYS
		
		if (this.inventory.getWeapon() instanceof Sword) { 
			this.inventory.setWeaponIndex(1);
			//System.out.println("pickaxe");
		} else if  (this.inventory.getWeapon() instanceof Pickaxe) {
			this.inventory.setWeaponIndex(0);
			//System.out.println("weapon");
		}
		
	}
	
	/*TODO: rename to doAction() */
	public void hit(int xHit,int yHit) { /* TODO: replace all x,y direction indication with Direction Enum */
		this.setMoveDirection(xHit, yHit);
		updateSpriteDirection(spriteFileU,spriteFileR,spriteFileD,spriteFileL);
		
		if (this.inventory.getWeapon() instanceof Sword) { //TODO: replace iso with a global use() redefined for each tool.
			this.attack(xHit, yHit); 
		} else if  (this.inventory.getWeapon() instanceof Pickaxe) {
			this.mine(xHit,yHit);
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
