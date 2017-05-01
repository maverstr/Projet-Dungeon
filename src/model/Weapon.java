package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public abstract class Weapon extends Item {
	
	private int damage;
	private static ArrayList<MediaPlayer> swordPlayerArray = new ArrayList<MediaPlayer>(); //for looping 3 sword players (if only 1=>attack sound stops first
	private static int swordPlayerIndex = 0;																		 //if new each time : lag
	private static final File swordFile = new File(GameObject.class.getResource("/resources/audio/Sword_Sound.m4a").getFile());
	private static final Media swordMedia = new Media(swordFile.toURI().toString());
	
	public Weapon(int damage, BufferedImage inventoryImage, int x, int y, Game game, ArrayList<Sprite> spriteList) {
		super(inventoryImage,x,y,game,spriteList);
		this.damage = damage;
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	public abstract void use(int xPlayer, int yPlayer, int xUseWeapon, int yUseWeapon);
	
	@Override
	public synchronized void pickUp(Inventory inventory) {
		this.getGame().getGameObjects().remove(this);
		inventory.addWeapon(this);
	}
	
	public synchronized void attack(int xAttack,int yAttack) {
		for (GameObject object:this.getGame().getGameObjects()) {
			if (object.getPosX() == xAttack && object.getPosY() == yAttack) {
				if (object.isAttackable()) {
					Mob mob = (Mob) object;
					mob.wasHit(this.damage);
					playSwordSound();
					break;
				}
			}
		}
	}
	
	private void playSwordSound() {
		MediaPlayer swordPlayer = swordPlayerArray.get(swordPlayerIndex);
		swordPlayer.stop();
		swordPlayer.play();
		swordPlayerIndex++;
		if (swordPlayerIndex>2) {
			swordPlayerIndex = 0;
		}
	}
	
	public static void setSwordMediaPlayer() {
		for (int i=0;i<3;i++) {
			MediaPlayer player = new MediaPlayer(swordMedia);
			player.setVolume(0.1);
			swordPlayerArray.add(player);
		}
	}
	
}
