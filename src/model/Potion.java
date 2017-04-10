package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Potion extends Consumable {

public enum potionType {
	vie(0),
	mana(1);
	private int index;

	private potionType(int index) {
		this.index = index;
	}

	public int getIndex() {
		return this.index;
	}
}


	potionType type;
	private static final File[] spriteFile = {
			new File(GameObject.class.getResource("/resources/sprites/potion_heal_sprite.png").getFile()),
			new File(GameObject.class.getResource("/resources/sprites/potion_mana_sprite.png").getFile()),
			
	};
	public Potion(potionType type) throws IOException {
		super(ImageIO.read(spriteFile[type.getIndex()]));
		//this.name=name;
		this.type = type;
		// TODO Auto-generated constructor stub
	}
	
	
	
	@Override
	public String getType() {
		return String.format("%s",this.type.name());
	}

}
