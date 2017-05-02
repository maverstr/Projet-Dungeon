package model;

import java.util.ArrayList;
import java.awt.image.BufferedImage;

public abstract class Spell extends Item implements Runnable {

	Thread t;
	private int waitTime;
	private int liveTime;
	private int damage;
	private int manaCost;
	protected boolean inventory;
	protected int time = 0;
	
	static Object lock = new Object();
	
	public Spell(BufferedImage inventoryImage, int x, int y, Game game, boolean inventory, ArrayList<Sprite> spriteList, int waitTime, int liveTime, int damage, int manaCost) {
		super(inventoryImage, x, y, game, spriteList);
		this.waitTime = waitTime;
		this.liveTime = liveTime;
		this.damage = damage;
		this.inventory = inventory;
		this.manaCost = manaCost;
		t = new Thread(this);
		t.start();
	}
	
	@Override
	public boolean isObstacle() {
		return false;
	}
	
	public void run(){
		
		try{
			while (!inventory) {
				if (game.getState() == Game.STATE.RUN) {
					
					attackMobs();
					
					if (time>liveTime) {
						this.disappear();
						this.getGame().updateWindow();
					}
					Thread.sleep(waitTime);
					time+=waitTime;
				}
			}
		}catch(Exception e){}; 
	}
	
	public synchronized void attackMobs() {
		
		ArrayList<GameObject> clone = (ArrayList<GameObject>) this.getGame().getGameObjects().clone();
		for (GameObject object:clone) {
			if (object.isAttackable()) {
				if (object.isAtPosition(posX, posY)) {
					Mob mob = (Mob) object;
					mob.wasHit(this.damage);
				}
			}
		}
	}
	
	public synchronized void disappear() {
		synchronized (lock) {
			if (!this.getGame().getGameObjects().remove(this)) {
				System.out.println(this);
				System.out.println(this.getGame().getGameObjects());
			}
			t.interrupt();
		}
	}
	
	@Override
	public void pickUp(Inventory inventory) {
		
	}
	
	@Override
	public boolean isPickable() {
		return true;
	}
	
	public abstract void castSpell(int x, int y, Game game, Direction direction);
	
	public int getManaCost() {
		return this.manaCost;
	}

}
