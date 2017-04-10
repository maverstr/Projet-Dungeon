package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Weapon extends Item {
	public Weapon(BufferedImage sprite){
		super(sprite);
	}

}
