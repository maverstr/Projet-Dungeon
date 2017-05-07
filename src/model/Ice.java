package model;

import java.io.File;
import java.util.ArrayList;

public class Ice extends Spell {
	private static final long serialVersionUID = 42L;
	
	private static final File spriteFileInventory = new File(GameObject.class.getResource("/resources/sprites/Ice.png").getFile());
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/Ice.png").getFile());
	
	private static final int waitTime = 1000;
	private static final int liveTime = 3000;
	private static final int damage = 1;
	private static final int manaCost = 1;

	public Ice(int x, int y, Game game, boolean inventory) {
		super(Sprite.imageFromFile(spriteFileInventory), x, y, game, inventory, Sprite.makeSpriteList(spriteFile,0,0,11), waitTime, liveTime, damage, manaCost);
	}

	@Override
	public synchronized void castSpell(int x, int y, Game game, Direction playerDirection) {
		ArrayList<GameObject> objects = this.getGame().getGameObjects();
		switch (playerDirection) {
		case North:
		case East:
		case South:
		case West:
			objects.add(new Ice(x-1,y-1,game,false));
			objects.add(new Ice(x,y-1,game,false));
			objects.add(new Ice(x+1,y-1,game,false));
			objects.add(new Ice(x+1,y,game,false));
			objects.add(new Ice(x-1,y,game,false));
			objects.add(new Ice(x-1,y+1,game,false));
			objects.add(new Ice(x,y+1,game,false));
			objects.add(new Ice(x+1,y+1,game,false));
			break;
		default:
			break;
		}
	}

}
