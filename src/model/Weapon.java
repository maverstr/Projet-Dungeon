package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Weapon extends Item {
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/Weapon.png").getFile());
	public Weapon() throws IOException {
		super(ImageIO.read(spriteFile));
	}

}
