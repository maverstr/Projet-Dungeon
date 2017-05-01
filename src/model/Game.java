package model;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Player;

import view.*;

import model.GameObject;
import java.util.Random;

import CONSTANTS.CONSTANTS;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Game implements RedrawObservable {
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private ArrayList<RedrawObserver> listRedrawObservers = new ArrayList<RedrawObserver>();
	private Window window;
	
	private int map_counter = 1;

	private Player player;
	
	private static final File musicFile = new File(GameObject.class.getResource("/resources/audio/Chant_CP.m4a").getFile());
	private static final Media musicMedia = new Media(musicFile.toURI().toString());
	private static final MediaPlayer musicPlayer = new MediaPlayer(musicMedia);
	private static final File uneMineFile = new File(GameObject.class.getResource("/resources/audio/Une_Mine.wav").getFile());
	private static final Media uneMineMedia = new Media(uneMineFile.toURI().toString());
	private static final MediaPlayer uneMinePlayer = new MediaPlayer(uneMineMedia);


	private static boolean bossBool = false;
	Random random = new Random();
	public enum STATE{ //The 2 states for the game
		MENU,
		CLASS,
		RUN,
		OVER,
		PAUSE,
		STORY,
		
	};
	
	private STATE state = STATE.MENU; //Set the initial state to titlescreen
	
	
	
	
	
	public Game(Window window) throws IOException {
		this.window = window;
		window.setGameObjects(objects);
		updateWindow();
		setMusicPlayer();
		Weapon.setSwordMediaPlayer();
	}
	
	public void setState(STATE state) {
		this.state = state;
	}
	
	public STATE getState(){
		return this.state;
		}
	
	public Window getWindow(){
		return this.window;
	}


	
	public synchronized void ChooseClass(int c){
		switch(c){
		case 1:
			player = new CP(0, 0, this);
			Penne.initSprites(0);
			break;
		case 2 :
			player = new CM(0, 0, this);
			Penne.initSprites(1);
			break;
		case 3:
			player = new CS(0, 0, this);
			Penne.initSprites(2);
			break;
		}
		objects.add(player); //The 1st object of the list is the player in order to handle its position in the list
		this.gameStart();

	}
	
	public void gameStart(){//Launch the game when NewGame from titleScreen is selected
		if (state != STATE.RUN){
			if (bossBool) {
				loadMap("map_boss.txt");
			} else {
				loadMap("map_1.txt");
			}
			this.setState(STATE.RUN);
			window.setPlayer(this.player);
			musicPlayer.play();
			updateWindow();
		}
	}



	public synchronized void removeGameObject(GameObject object) {
		objects.remove(object);
	}

	public synchronized ArrayList<GameObject> getGameObjects() {
		return this.objects;
	}
	
	public void setMusicPlayer() {
		musicPlayer.setVolume(0.7);
		musicPlayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				musicPlayer.seek(Duration.ZERO);
			}
		});
	}

	public Player getPlayer() {
		return this.player;
	}

	public void movePlayer(int xMove, int yMove) {
		if (player.isAlive()) {
			player.tryToMove(xMove, yMove);
		}
	}

	public void playerUseWeapon(int xUseWeapon, int yUseWeapon) {
		if (player.isAlive()) {
			player.useWeapon(xUseWeapon, yUseWeapon);
			updateWindow();
		}
	}

	public void itemAtIndex(int index) { //use item in inventory
		player.selectItemAtIndex(index);
		updateWindow();
	}
	
	public void playerPickUpItem() { //pick up item on the ground
		if (player.isAlive()) {
			player.pickUpItem();
		}
	}
	
	public void playerOpenChest() {
		if (player.isAlive()) {
			player.openChest();
		}
	}
	
	public void playerCastSpell() {
		player.castSpell();
	}
	
	public void playerChangeSpell() {
		player.changeSpell();
	}

	public void updateWindow() {

		if(state == STATE.RUN){
			notifyRedrawObserver();
		}
		else if(state == STATE.MENU) {
			window.redrawMenu(); 
			}
		else if(state == STATE.CLASS){
			window.redrawClass();
		}
		else if(state == STATE.OVER){
			window.redrawGameOver();
		}
		else if(state == STATE.STORY){
			window.redrawStory();
		}
	}
	
	public synchronized void changeMap(){
		synchronized (objects) {
			String map_name = "";
			int map_number_random;
			ArrayList<GameObject> clone = (ArrayList<GameObject>) objects.clone(); //Clone() allows to create a DEEPCOPY of the list to get the variables without actually blocking the real list
			for(GameObject object: clone){
				if (object.isAttackable()) {
					Mob mob = (Mob) object;
					mob.die();
				}
			}
			this.objects.subList(1, this.objects.size()).clear();//Suppress the previous map (except the player in index 1).			
			if(this.map_counter >3){
				bossBool = true;
				loadMap("map_boss.txt");
				uneMinePlayer.play();
			}
			else{
				map_number_random = random.nextInt(3)+1;
				map_name = String.format("map_%d.txt", map_number_random);
				loadMap(map_name);
			}
		}
		this.map_counter +=1;
		this.updateWindow();
		player.getInventory().setWeaponIndex(0); //Select The Sword as the beginning weapon at start.
	}

	private synchronized void loadMap(String fileName) { // Read the MAP.TXT and load every object in the GameObjects list
		try {
			int playerLine = 0;
			int playerColumn = 0;
			ArrayList<Integer> emptyCasesX = new ArrayList<>();
			ArrayList<Integer> emptyCasesY = new ArrayList<>();

			File file = new File(Map.class.getResource("/resources/map/" + fileName).getFile());
			String line = null;
			
			
			String map_block_width = ""; //To define the dimension of the map from arguments in the map file
			String map_block_height = "";	
			
			String darkness = "";
			
			FileReader fileReader = new FileReader(file);

			BufferedReader bufferedReader = new BufferedReader(fileReader);
			int currentLine = -10; // the -10 is due to parameters in the beginning of map files (10 lines) which must NOT be taken into account for positioning
			while ((line = bufferedReader.readLine()) != null) {
				for (int column = 0; column < line.length(); column++) {
					char c = line.charAt(column);
					if(currentLine == -10){ //Reads the 1st line of the map.txt file as the width of the map
						map_block_width = map_block_width + c;
					}
					else if (currentLine == -9){
						map_block_height = map_block_height +c;
					}
					
					else if(currentLine == -2){
						darkness = darkness + c;
					}
					switch (c) {
					case '*':
						this.objects.add(new BlockNotBreakable(column, currentLine, this));
						break;
					case '$':
						this.objects.add(new BlockBreakable(column, currentLine, this));
						break;
					// Read position of the Player
					case 'P':
						playerLine = currentLine;
						playerColumn = column;
						break;
					case 'M':
						this.objects.add(new BlockMoveable(column, currentLine, this));
						break;
					case 'C':
						this.objects.add(new Chest(column, currentLine, this));
						break;
					case 'D':
						this.objects.add(new Door(column, currentLine, this));
						break;
					case '/':
						emptyCasesX.add(column);
						emptyCasesY.add(currentLine);
						break;
					default:
						break;
					}
				}
				player.setPos(playerColumn, playerLine); // set position of the
															// player
				currentLine++;
			}
			try{
				CONSTANTS.setMAP_BLOCK_WIDTH(Integer.valueOf(map_block_width)); //Defines the dimension of the map from arguments in the map file
				CONSTANTS.setMAP_BLOCK_HEIGHT( Integer.valueOf(map_block_height));
			}catch(NumberFormatException e){
				System.out.println("Les arguments de taille de map ne sont pas valides.");
			    e.printStackTrace();
			}
			CONSTANTS.updateBLOCK_SIZE();
			
			if(darkness.equals("DARKNESS")){
				CONSTANTS.setDARKNESS_MODIFIER(true);
			}
			else{CONSTANTS.setDARKNESS_MODIFIER(false);}

			
			bufferedReader.close();
			if (bossBool) {
				loadMobs(emptyCasesX, emptyCasesY, 1);
			} else {
				loadMobs(emptyCasesX, emptyCasesY, 4);
			}

		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file '" + fileName + "'" + e);
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
	}

	private synchronized void loadMobs(ArrayList<Integer> emptyCasesX, ArrayList<Integer> emptyCasesY, int maxMobs) {

		ArrayList<Integer> mobXArray = new ArrayList<>();
		ArrayList<Integer> mobYArray = new ArrayList<>();

		
		for (int i = 0; i < maxMobs; i++) {
			if (emptyCasesX.size() > 0) {
				int randomInt = random.nextInt(emptyCasesX.size()); //int between 0 (inclusive) and size (exclusive)
				int mobX = emptyCasesX.remove(randomInt);
				int mobY = emptyCasesY.remove(randomInt);
				mobXArray.add(mobX);
				mobYArray.add(mobY);
			} else {
				System.out.println("Not enough free cases for " + maxMobs + " mobs");
				break;
			}
		}

		for (int i = 0; i < mobXArray.size(); i++) {
			int posX = mobXArray.get(i);
			int posY = mobYArray.get(i);
			boolean randomIsZombie = random.nextBoolean();
			boolean randomBaptized = random.nextBoolean();
			try {
				if (bossBool) {
					Boss boss = new Boss(posX, posY, this);
					this.objects.add(boss);
					window.setBoss(boss);
				} else {
					if (randomIsZombie) {
						this.objects.add(new Zombie(posX, posY, i * 1000 / mobXArray.size(), this, randomBaptized));
					} else {
						this.objects.add(new Skeleton(posX, posY, i * 1000 / mobXArray.size(), this, randomBaptized));
					}
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	
	public synchronized void loot(int x, int y, int lootLevel, boolean alwaysLoot) {
		int randomInt = random.nextInt(lootLevel)-10; //int between -10 (inclusive) and lootLevel-10 (exclusive)
		if (alwaysLoot) {
			randomInt+=10;
		}
		int randomIntLimited = Math.min(randomInt, 4);
		Item item = null;
		switch (randomIntLimited) {
		case 0:
			item = new PotionVie(x, y, this);
			break;
		case 1:
			item = new PotionMana(x, y, this);
			break;
		case 2:
			item = new Penne(x, y, this, Penne.getFileRight());
			break;
		case 3:
			item = new Scepter(x,y,this);
			break;
		case 4:
			item = new Pickaxe(x,y,this);
			break;
		case 5:
			item = new Torch(x,y,this);
			break;
		}
		if (item!=null) {
			this.getGameObjects().add(item);
		}
	}
	

	@Override
	public void addRedrawObserver(RedrawObserver obs) {
		this.listRedrawObservers.add(obs);

	}

	@Override
	public void removeRedrawObserver(RedrawObserver obs) {
		this.listRedrawObservers.remove(obs);

	}

	@Override
	public void notifyRedrawObserver() {
		for (RedrawObserver ob : listRedrawObservers) {
			ob.redraw(this);
		}
	}

}
