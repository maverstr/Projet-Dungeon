package model;

import java.io.File;
import java.util.ArrayList;

public abstract class Player extends Character {
	
	private boolean alive = true;
	private Inventory inventory;
	private int luck;
	protected int mana;
	protected int maxMana;
	
	private File spriteFileU;
	private File spriteFileR;
	private File spriteFileD;
	private File spriteFileL;
	
	
	public Player(int x, int y, Game game, ArrayList<Sprite> spriteList, ArrayList<File> fileList, int maxHealth, int maxMana, int luck) {
		super(x, y, game, spriteList, maxHealth,false);
		this.spriteFileU = fileList.get(0);
		this.spriteFileR = fileList.get(1);
		this.spriteFileD = fileList.get(2);
		this.spriteFileL = fileList.get(3);
		
		this.maxMana = maxMana;
		this.mana = maxMana;
		this.luck = luck;
		
		this.inventory = new Inventory();
		setInventory(inventory);
	}
	
	public abstract void setInventory(Inventory inventory);
	
	public Inventory getInventory() {
		return this.inventory;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public int getMaxMana() {
		return maxMana;
	}
	
	public int getMana() {
		return mana;
	}
	
	public void setLuck(int luck) {
		this.luck = luck;
	}

	public void addHealth(int healPoints) {
		health+=healPoints;
		if (health>maxHealth) {
			health = maxHealth;
		}
	}

	public void addMana(int manaPoints) {
		mana+=manaPoints;
		if (mana>maxMana) {
			mana = maxMana;

		}
	}


	@Override
	public boolean isObstacle() {
		return true;
	}
	
	public int getLuck() {
		return this.luck;
	}
	
	public synchronized void tryToMove(Direction direction) {
		boolean obstacle = false;
		
		int newPosX = posX+xForDirection(direction);
		int newPosY = posY+yForDirection(direction);
		
		int blockMoveableNewPosX = posX+2*xForDirection(direction);
		int blockMoveableNewPosY = posY+2*yForDirection(direction);
		ArrayList<GameObject> objects = this.getGame().getGameObjects();
		synchronized(objects){

			for (GameObject object:objects) {
				if (object.getPosX() == newPosX && object.getPosY() == newPosY) {
					if (object.isObstacle()) {
						obstacle = true;
						if (object.isMoveable()) {
							if (freeSpace(blockMoveableNewPosX,blockMoveableNewPosY)) {
								BlockMoveable blockMoveable = (BlockMoveable) object;
								move(direction);
								blockMoveable.move(direction);
								updateSpriteDirection(spriteFileU,spriteFileR,spriteFileD,spriteFileL);
							}
						}
					}
				}
			}
		}
		if (!obstacle) {
			move(direction);
			updateSpriteDirection(spriteFileU,spriteFileR,spriteFileD,spriteFileL);
		}
		
		this.getGame().updateWindow();
	}
	
	private synchronized boolean freeSpace(int x, int y) {
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
	
	
	public void useWeapon(Direction direction) {
		this.setMoveDirection(direction);
		updateSpriteDirection(spriteFileU,spriteFileR,spriteFileD,spriteFileL);
		this.inventory.getWeapon().use(posX, posY, direction);
	}
	
	public void selectItemAtIndex(int index) {
		int ws = inventory.weapons.size();
		if (index<ws) {
			this.inventory.setWeaponIndex(index);
		} else {
			if (index<inventory.getItemCount()) {
				Consumable consumable = inventory.consumables.get(index-ws);
				consumable.use(consumable);
				inventory.useConsumable(consumable);
			}
		}
	}
	
	public void changeSpell() {
		inventory.updateSpellIndex();
		this.getGame().updateWindow();
	}
	
	public void castSpell() {
		Spell spell = inventory.getSpell();
		int manaCost = spell.getManaCost();
		if (manaCost<=mana) {
			spell.castSpell(posX, posY, game, this.direction);
			mana-=manaCost;
		}
		this.getGame().updateWindow();
	}
	
	public synchronized void openChest() {
		ArrayList<GameObject> clone = (ArrayList<GameObject>) this.getGame().getGameObjects().clone();
		for (GameObject object:clone) {
			if (object.isOpenable()) {
				if (object.isAtPosition(posX, posY-1) || object.isAtPosition(posX-1, posY) ||object.isAtPosition(posX, posY+1) || object.isAtPosition(posX+1, posY)) {
					Chest chest = (Chest) object;
					chest.open();
					this.getGame().updateWindow();
				}
			}
			else if(object.isExit()){
				if (object.isAtPosition(posX, posY)) {
					game.changeMap();
				}
			}
		}
	}
	
	public synchronized void pickUpItem() { //Note : this method may also be called to enter the level exit door
		ArrayList<GameObject> clone = (ArrayList<GameObject>) this.getGame().getGameObjects().clone();
		for (GameObject object:clone) {
			if (object.isPickable()) {
				if (object.isAtPosition(posX, posY)) {
					Item item = (Item) object;
					item.pickUp(inventory);
					this.getGame().updateWindow();
				}
			}

		}
	}
	
	public boolean isAlive() {
		return this.alive;
	}
	
	public void die() {
		this.alive = false;
		this.getGame().getGameObjects().remove(this);
		game.setState(Game.STATE.OVER);
		this.getGame().updateWindow();
		System.out.println("GAME OVER-------GET REKT-------YOU MAD BRO??");
	}
	
	public abstract void specialAbility();
	
	
}
