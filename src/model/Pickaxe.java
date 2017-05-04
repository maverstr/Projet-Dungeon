package model;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Pickaxe extends Weapon {
	private static final long serialVersionUID = 42L;
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/pickaxe_sprite.png").getFile());

	private static final File pickaxeFile = new File(GameObject.class.getResource("/resources/audio/Pickaxe_Sound.wav").getFile());
	private static final Media pickaxeMedia = new Media(pickaxeFile.toURI().toString());
	private static final transient MediaPlayer pickaxePlayer = new MediaPlayer(pickaxeMedia);

	public Pickaxe(int x, int y, Game game) {
		super(1, Sprite.imageFromFile(spriteFile), x, y, game, Sprite.makeSpriteList(spriteFile, 0, 0, 0));
	}

	@Override
	public void use(int xPlayer, int yPlayer, Direction useDirection) {
		int newPosX = xPlayer+xForDirection(useDirection);
		int newPosY = yPlayer+yForDirection(useDirection);
		
		this.attack(newPosX, newPosY);
		this.mine(newPosX, newPosY);
	}
	
	public synchronized void mine(int xMine,int yMine) {
		for (GameObject object:this.getGame().getGameObjects()) {
			if (object.getPosX() == xMine && object.getPosY() == yMine) {
				if (object instanceof BlockBreakable) {
					BlockBreakable block = (BlockBreakable) object;
					block.toBreak();
					pickaxePlayer.stop();
					pickaxePlayer.play();
					break;
				}
			}
		}
	}
	
	

}
