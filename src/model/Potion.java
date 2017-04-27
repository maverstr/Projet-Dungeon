package model;

import java.io.File;

import javax.imageio.ImageIO;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;

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
	private static final File[] spriteFileInventory = { //List of sprites (inventory) according to the potion Type and its index
			new File(GameObject.class.getResource("/resources/sprites/potion_heal_sprite.png").getFile()),
			new File(GameObject.class.getResource("/resources/sprites/potion_mana_sprite.png").getFile()),
	};
	private static final File[] spriteFile = { //List of sprites (map) according to the potion Type and its index
			new File(GameObject.class.getResource("/resources/sprites/potion_heal_sprite.png").getFile()),
			new File(GameObject.class.getResource("/resources/sprites/potion_mana_sprite.png").getFile()),
	};
	
	private static final File beerSoundFile = new File(GameObject.class.getResource("/resources/audio/beer_Sound.mp3").getFile());
	private static final Media beerSoundMedia = new Media(beerSoundFile.toURI().toString());
	MediaPlayer beerSoundPlayer = new MediaPlayer(beerSoundMedia);
	
	public Potion(potionType type, int x, int y, Game game) {
		super(Sprite.imageFromFile(spriteFileInventory[type.getIndex()]), x, y, game, Sprite.makeSpriteList(spriteFile[type.getIndex()], 0, 0, 0));
		this.type = type;
	}
	
	
	
	@Override
	public String getType() { //Allows comparison between items
		return String.format("%s",this.type.name()); //Need to format cause type.name is PotionType not String
														//Is overriding super getType cause of the special enum for potion types
	}
	
	public void use(Consumable c){
		Potion p = (Potion) c; //Downcasting
		beerSoundPlayer.play();
		if(p.type == potionType.vie){
			game.getPlayer().addHealth(10);
		}
		else if(p.type == potionType.mana){
			game.getPlayer().addMana(5);

		}
	}
	

}
