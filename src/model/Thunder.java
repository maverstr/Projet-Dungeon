package model;

import java.io.File;
import java.util.ArrayList;

public class Thunder extends Spell {
	private static final long serialVersionUID = 42L;

	private static final File spriteFileInventory = new File(GameObject.class.getResource("/resources/sprites/Thunder.png").getFile());
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/Thunder.png").getFile());
	private static final int waitTime = 200;
	private static final int liveTime = 800;
	private static final int damage = 3;
	private static final int manaCost = 2;
	
	public Thunder(int x, int y, Game game, boolean inventory) {
		super(Sprite.imageFromFile(spriteFileInventory), x, y, game, inventory, Sprite.makeSpriteList(spriteFile,0,0,11), waitTime, liveTime, damage, manaCost);
	}

	@Override
	public synchronized void castSpell(int x, int y, Game game, Direction playerDirection) {
		ArrayList<GameObject> objects = this.getGame().getGameObjects();
		switch (playerDirection) {
		case North:
			objects.add(new Thunder(x,y-1,game,false));
			objects.add(new Thunder(x,y-2,game,false));
			objects.add(new Thunder(x,y-3,game,false));
			break;
		case East:
			objects.add(new Thunder(x+1,y,game,false));
			objects.add(new Thunder(x+2,y,game,false));
			objects.add(new Thunder(x+3,y,game,false));
			break;
		case South:
			objects.add(new Thunder(x,y+1,game,false));
			objects.add(new Thunder(x,y+2,game,false));
			objects.add(new Thunder(x,y+3,game,false));
			break;
		case West:
			objects.add(new Thunder(x-1,y,game,false));
			objects.add(new Thunder(x-2,y,game,false));
			objects.add(new Thunder(x-3,y,game,false));
			break;
		default:
			break;
		}
	}
	
}
