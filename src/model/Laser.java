package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Laser extends GameObject {

	public Laser(int x, int y, Game game, File file) throws IOException {
		super(x, y, game, ImageIO.read(file));
	}
	
	

	@Override
	public boolean isObstacle() {
		return false;
	}

}
