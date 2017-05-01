package model;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class PotionVie extends Consumable {

	private static final File spriteFileInventory = new File(GameObject.class.getResource("/resources/sprites/potion_heal_sprite.png").getFile());
	private static final File spriteFile = new File(GameObject.class.getResource("/resources/sprites/potion_heal_sprite.png").getFile());
	
	
	private static final File beerSoundFile = new File(GameObject.class.getResource("/resources/audio/beer_Sound.mp3").getFile());
	private static final Media beerSoundMedia = new Media(beerSoundFile.toURI().toString());
	MediaPlayer beerSoundPlayer = new MediaPlayer(beerSoundMedia);
	
	public PotionVie(int x, int y, Game game) {
		super(Sprite.imageFromFile(spriteFileInventory), x, y, game, Sprite.makeSpriteList(spriteFile, 0, 0, 0));
	}
	
	
	
	@Override
	public String getType() { //Allows comparison between items
		return "potion_vie";
	}
	
	public void use(Consumable c){
		PotionVie p = (PotionVie) c; //Downcasting
		beerSoundPlayer.stop();
		beerSoundPlayer.play();
		game.getPlayer().addHealth(10);

	}
	

}