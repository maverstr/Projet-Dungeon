package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Character {
	public Player(int x, int y) throws IOException {
		super(x, y,  ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/Hugues_Head.jpg").getFile())));
		}

	@Override
	public boolean isObstacle() {
		return false;
	}

}
