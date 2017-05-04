package model;

import java.io.File;
import java.util.ArrayList;

public class Heal extends Spell {
	private static final long serialVersionUID = 42L;

	private static final File spriteFileInventory = new File(GameObject.class.getResource("/resources/sprites/Heal.png").getFile());
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/Heal.png").getFile());
	
	private static final int waitTime = 600;
	private static final int liveTime = 500;
	private static final int damage = 0;
	private static final int manaCost = 10;

	public Heal(int x, int y, Game game, boolean inventory) {
		super(Sprite.imageFromFile(spriteFileInventory), x, y, game, inventory, Sprite.makeSpriteList(spriteFile,0,-1,11), waitTime, liveTime, damage, manaCost);
	}
	
	@Override
	public void run() {
		try{
			while (!inventory) {
				if (game.getState() == Game.STATE.RUN) {
					
					this.getGame().getPlayer().addHealth(1000);
					
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
	
	@Override
	public synchronized void castSpell(int x, int y, Game game, Direction playerDirection) {
		ArrayList<GameObject> objects = this.getGame().getGameObjects();
		objects.add(new Heal(x,y,game,false));
	}
	
}
