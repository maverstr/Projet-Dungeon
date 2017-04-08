package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Zombie extends Mob {

	public Zombie(int x, int y) throws IOException {
		super(x, y, ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/Zombie.png").getFile())));
		
	}
	

}
