package model;

import java.io.File;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Sword extends Weapon {
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/sword_sprite.png").getFile());
	
	public Sword() throws IOException {
		super(ImageIO.read(spriteFile),2);
	}
	
	public boolean breakBlockAbility() {
		return false;
	}
	
	
	
}
