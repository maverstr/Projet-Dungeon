package model;

import java.io.File;

public class Scepter extends Weapon {
	private static final long serialVersionUID = 42L;
	
	private static final File spriteFileInventory = new File(GameObject.class.getResource("/resources/sprites/Scepter.png").getFile());
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/Scepter.png").getFile());

	public Scepter(int x, int y, Game game) {
		super(0, Sprite.imageFromFile(spriteFileInventory), x, y, game, Sprite.makeSpriteList(spriteFile, 0, 0, 0));
	}

	@Override
	public void use(int xPlayer, int yPlayer, Direction direction) {
		this.getGame().getPlayer().castSpell();
	}

	@Override
	public Sprite getAttackSprite(Direction direction) {
		return null;
	}
	
	@Override
	public void drop(int x, int y) {
		this.getGame().getGameObjects().add(new Scepter(x,y,this.getGame()));
	}
}
