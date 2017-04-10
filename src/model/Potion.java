package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

// Potion Consumable Class

public class Potion extends Consumable {

public enum potionType { //ALL the Potions Types available with their index in regards to the ArrayList below
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

// Attributes for Potion begin here:

	potionType type;
	private static final File[] spriteFile = { //List of sprites according to the potion Type and its index
			new File(GameObject.class.getResource("/resources/sprites/potion_heal_sprite.png").getFile()),
			new File(GameObject.class.getResource("/resources/sprites/potion_mana_sprite.png").getFile()),
			
	};
	public Potion(potionType type) throws IOException {
		super(ImageIO.read(spriteFile[type.getIndex()]));
		this.type = type;
	}
	
	
	
	@Override
	public String getType() { //Allows comparison between items
		return String.format("%s",this.type.name()); //Need to format cause type.name is PotionType not String
														//Is overriding super getType cause of the special enum for potion types
	}

}
