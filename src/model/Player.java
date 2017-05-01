package model;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;



public abstract class Player extends Character {
	
	private static final File swordFile = new File(GameObject.class.getResource("/resources/audio/Sword_Sound.m4a").getFile());
	private static final File pickaxeFile = new File(GameObject.class.getResource("/resources/audio/Pickaxe_Sound.wav").getFile());
	
	private static final Media swordMedia = new Media(swordFile.toURI().toString());
	private static final Media pickaxeMedia = new Media(pickaxeFile.toURI().toString());
	
	private ArrayList<MediaPlayer> swordPlayerArray = new ArrayList<MediaPlayer>(); //for looping 3 sword players (if only 1=>attack sound stops first
	private int swordPlayerIndex = 0;																			 //if new each time : lag
	
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
	
		setSwordMediaPlayer();
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
	
	public synchronized void tryToMove(int xMove, int yMove) {
		boolean obstacle = false;
		
		int newPosX = posX+xMove;
		int newPosY = posY+yMove;
		
		int blockMoveableNewPosX = posX+2*xMove;
		int blockMoveableNewPosY = posY+2*yMove;
		ArrayList<GameObject> objects = this.getGame().getGameObjects();
		synchronized(objects){

			for (GameObject object:objects) {
				if (object.getPosX() == newPosX && object.getPosY() == newPosY) {
					if (object.isObstacle()) {
						obstacle = true;
						if (object.isMoveable()) {
							if (freeSpace(blockMoveableNewPosX,blockMoveableNewPosY)) {
								BlockMoveable blockMoveable = (BlockMoveable) object;
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
	
	
	public void useWeapon(int xUseWeapon,int yUseWeapon) { /* TODO: replace all x,y direction indication with Direction Enum */
		this.setMoveDirection(xUseWeapon, yUseWeapon);
		updateSpriteDirection(spriteFileU,spriteFileR,spriteFileD,spriteFileL);
		this.attack(xUseWeapon, yUseWeapon);
		
		if (this.inventory.getWeapon().breakBlockAbility()) {
			this.mine(xUseWeapon,yUseWeapon);
		}
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
	
	public synchronized void attack(int xAttack,int yAttack) {
		int newPosX = this.posX+xAttack;
		int newPosY = this.posY+yAttack;
		
		for (GameObject object:this.getGame().getGameObjects()) {
			if (object.getPosX() == newPosX && object.getPosY() == newPosY) {
				if (object.isAttackable()) {
					//System.out.println("mob attacked");
					Mob mob = (Mob) object;
					mob.wasHit(inventory.getWeapon().getDamage());
					playSwordSound();
					break;
				}
			}
		}
	}
	
	private void playSwordSound() {
		MediaPlayer swordPlayer = swordPlayerArray.get(swordPlayerIndex);
		swordPlayer.stop();
		swordPlayer.play();
		swordPlayerIndex++;
		if (swordPlayerIndex>2) {
			swordPlayerIndex = 0;
		}
	}
	
	public synchronized void mine(int xMine,int yMine) {
		int newPosX = this.posX+xMine;
		int newPosY = this.posY+yMine;
		
		for (GameObject object:this.getGame().getGameObjects()) {
			if (object.getPosX() == newPosX && object.getPosY() == newPosY) {
				if (object instanceof BlockBreakable) {
					BlockBreakable block = (BlockBreakable) object;
					block.toBreak();
					MediaPlayer pickaxePlayer = new MediaPlayer(pickaxeMedia);
					pickaxePlayer.play();
					break;
				}
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
	
	private void setSwordMediaPlayer() {
		for (int i=0;i<3;i++) {
			MediaPlayer player = new MediaPlayer(swordMedia);
			player.setVolume(0.1);
			swordPlayerArray.add(player);
		}
	}
	
	public abstract void specialAbility();
	
	
}
