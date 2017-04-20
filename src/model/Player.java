package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



public abstract class Player extends Character {
	
	
	private boolean alive = true;
	
	private static final int maxHealth = 50;
	private Inventory inventory;
	
	private File spriteFileU;
	private File spriteFileR;
	private File spriteFileD;
	private File spriteFileL;
	private File spriteFilePU;
	private File spriteFilePR;
	private File spriteFilePD;
	private File spriteFilePL;
	
	
	public Player(int x, int y, Game game, ArrayList<Sprite> spriteList, ArrayList<File> fileList) {
		super(x, y, game, spriteList,maxHealth);
		this.spriteFileU = fileList.get(0);
		this.spriteFileR = fileList.get(1);
		this.spriteFileD = fileList.get(2);
		this.spriteFileL = fileList.get(3);
		this.spriteFilePU = fileList.get(4);
		this.spriteFilePR = fileList.get(5);
		this.spriteFilePD = fileList.get(6);
		this.spriteFilePL = fileList.get(7);
		
		this. inventory = new Inventory();
	//Add weapons and items to the Player at the beginning of the Game.
		try {
			this.inventory.addWeapon(new Sword());
			this.inventory.addWeapon(new Sword()); // adds a second sword -> prints an error message cause there cant be two same weapons
			this.inventory.addWeapon(new Pickaxe());
			this.inventory.addConsumable(new Potion(Potion.potionType.vie)); //Note the type of potion
			this.inventory.addConsumable(new Potion(Potion.potionType.vie));
			this.inventory.addConsumable(new Potion(Potion.potionType.mana));
		}catch (IOException e){}
		
		
		this.inventory.setWeaponIndex(0); //Select The Sword as the beginning weapon at start.

	}
	
	public Inventory getInventory() {
		return this.inventory;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}


	@Override
	public boolean isObstacle() {
		return true;
	}
	
	public void tryToMove(int xMove, int yMove) {
		boolean obstacle = false;
		
		int newPosX = posX+xMove;
		int newPosY = posY+yMove;
		
		int blockMoveableNewPosX = posX+2*xMove;
		int blockMoveableNewPosY = posY+2*yMove;
		
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
	
	
	/*
	public void changeTool() { //When "A" is pressed, CYCLE trough the weapons Sword OR pickaxe 
		
		// TODO Not Cycle but Selection with KEYS
		
		if (this.inventory.getWeapon() instanceof Sword) { 
			this.inventory.setWeaponIndex(1);
			//System.out.println("pickaxe");
		} else if  (this.inventory.getWeapon() instanceof Pickaxe) {
			this.inventory.setWeaponIndex(0);
			//System.out.println("weapon");
		}
	}*/
	
	public void useWeapon(int xUseWeapon,int yUseWeapon) { /* TODO: replace all x,y direction indication with Direction Enum */
		this.setMoveDirection(xUseWeapon, yUseWeapon);
		updateSpriteDirection(spriteFileU,spriteFileR,spriteFileD,spriteFileL);
		this.attack(xUseWeapon, yUseWeapon);
		
		if (this.inventory.getWeapon().breakBlockAbility()) {
			this.mine(xUseWeapon,yUseWeapon);
		}
	}
	
	public void checkItemAtIndex(int index) {
		int ws = inventory.weapons.size();
		if (index<ws) {
			this.inventory.setWeaponIndex(index);
		} else {
			if (index<inventory.getItemCount()) {
				Consumable consumable = inventory.consumables.get(index-ws);
				System.out.println(consumable);
				//TODO consumable.use()
			}
		}
	}
	
	public void attack(int xAttack,int yAttack) {
		int newPosX = this.posX+xAttack;
		int newPosY = this.posY+yAttack;
		
		for (GameObject object:this.getGame().getGameObjects()) {
			if (object.getPosX() == newPosX && object.getPosY() == newPosY) {
				if (object instanceof Mob) {
					System.out.println("mob attacked");
					Mob mob = (Mob) object;
					mob.wasHit(inventory.getWeapon().getDamage());
					break;
				}
			}
		}
		pickUpPenne();
	}
	
	public void pickUpPenne() {
		try {
			this.spriteList.set(1, Sprite.makeSpriteFromFile(spriteFilePU, 0, -0.45, 2));
		} catch (Exception e) {
			this.spriteList.add(Sprite.makeSpriteFromFile(spriteFilePU, 0, -0.45, 2));
		}
		updatePenneDirection();
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
		int newPosX = this.posX+xMine;
		int newPosY = this.posY+yMine;
		
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
	
	@Override
	public void updateSpriteDirection(File up,File right,File down,File left) {
		super.updateSpriteDirection(up, right, down, left);
		updatePenneDirection();
	}
	
	public void updatePenneDirection() {
		try {
			Sprite sprite = spriteList.get(1);
			switch (this.direction) {
			case North:sprite.setImageFromFile(spriteFilePU);
				break;
			case East:sprite.setImageFromFile(spriteFilePR);
				break;
			case South:sprite.setImageFromFile(spriteFilePD);
				break;
			case West:sprite.setImageFromFile(spriteFilePL);
				break;
			default:
				break;
			}
		} catch (Exception e) {}
	}
	
	
}
