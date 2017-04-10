package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pickaxe extends Weapon {
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/pickaxe_sprite.png").getFile());

	public Pickaxe() throws IOException {
		super(ImageIO.read(spriteFile));

	}

}
