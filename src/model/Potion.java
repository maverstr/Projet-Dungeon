package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Potion extends Consumable {

	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/sword_sprite.png").getFile());
	private String name;
	public Potion(String name) throws IOException {
		super(ImageIO.read(spriteFile));
		this.name=name;
		// TODO Auto-generated constructor stub
	}

}
