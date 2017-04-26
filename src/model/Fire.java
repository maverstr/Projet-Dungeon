package model;
import java.io.File;
import java.util.ArrayList;

public class Fire extends Spell {
	
	private static final File spriteFileInventory = new File(GameObject.class.getResource("/resources/sprites/Fire.png").getFile());
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/Fire.png").getFile());
	
	private static final int waitTime = 1000;
	private static final int liveTime = 2000;
	private static final int damage = 2;
	private static final int manaCost = 1;

	public Fire(int x, int y, Game game, boolean inventory) {
		super(Sprite.imageFromFile(spriteFileInventory), x, y, game, inventory, Sprite.makeSpriteList(spriteFile,0,0,0), waitTime, liveTime, damage, manaCost);
	}

	@Override
	public void castSpell(int x, int y, Game game, Direction playerDirection) {
		ArrayList<GameObject> objects = this.getGame().getGameObjects();
		int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
		switch (playerDirection) {
		case North:
			x1 = -1;
			x2 = 1;
			y1 = -2;
			y2 = -1;
			break;
		case East:
			x1 = 1;
			x2 = 2;
			y1 = -1;
			y2 = 1;
			break;
		case South:
			x1 = -1;
			x2 = 1;
			y1 = 1;
			y2 = 2;
			break;
		case West:
			x1 = -2;
			x2 = -1;
			y1 = -1;
			y2 = 1;
			break;
		default:
			break;
		}
		for (int i = x1; i<=x2; i++) {
			for (int j = y1; j<=y2; j++) {
				objects.add(new Fire(x+i,y+j,game,false));
			}
		}
	}
	
	
}
