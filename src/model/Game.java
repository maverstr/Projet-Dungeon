package model;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Player;

import model.GameObject;
import view.Map;
//import java.awt.Window;
import view.Window;

import java.util.Random;

public class Game {
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private Window window;
	private Player player = new Player(0, 0, this);
	private static final boolean bossBool = false;

	public Game(Window window) throws IOException {
		this.window = window;
		window.setGameObjects(this.objects);
		objects.add(player);
		if (bossBool) {
			loadMap("map_2.txt");
		} else {
			loadMap("map_1.txt");
		}
		System.out.println(String.format("in game constructor, objects is : %s", objects));
		/* pass to window the objects it will need for viewing things */

		window.setPlayer(this.player);

		updateWindow();

	}

	public void removeGameObject(GameObject object) {
		objects.remove(object);
	}

	public ArrayList<GameObject> getGameObjects() {
		return this.objects;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void playerChangeTool() {
		player.changeTool();
		updateWindow();
	}

	public void movePlayer(int xMove, int yMove) {
		if (player.isAlive()) {
			player.tryToMove(xMove, yMove);
		}
	}

	public void playerUseTool(int xUseTool, int yUseTool) {
		if (player.isAlive()) {
			player.useTool(xUseTool, yUseTool);
			updateWindow();
		}
	}

	public void updateWindow() {
		/* No need to resend the objects and player every time, we pass it already once in our constructor and 
		 * it is passed by reference, so window will 'see' the modification in the objects / player

		 */
		// window.setGameObjects(objects);
		// window.setPlayer(this.player);

		/*
		 * We just tell window to redraw itself; window, in turn will ask map
		 * and playerState to redraw themself
		 */
		window.update(); /*
							 * TODO: could be replaced by an observer (window) /
							 * observable (this)
							 */
	}

	private void loadMap(String fileName) { // Read the MAP.TXT and load every
											// object in the GameObjects list
		try {
			int playerLine = 0;
			int playerColumn = 0;
			ArrayList<Integer> emptyCasesX = new ArrayList<>();
			ArrayList<Integer> emptyCasesY = new ArrayList<>();

			File file = new File(Map.class.getResource("/resources/map/" + fileName).getFile());
			String line = null;
			FileReader fileReader = new FileReader(file);

			BufferedReader bufferedReader = new BufferedReader(fileReader);
			int currentLine = 0;
			while ((line = bufferedReader.readLine()) != null) {
				// System.out.println(line); //PRINT THE MAP IN THE CONSOLE
				for (int column = 0; column < line.length(); column++) {
					char c = line.charAt(column);
					switch (c) {
					case '*':
						this.objects.add(new BlockNotBreakable(column,
								currentLine, this));
						break;
					case '$':
						this.objects.add(new BlockBreakable(column,
								currentLine, this));
						break;
					// Read position of the Player
					case 'P':
						playerLine = currentLine;
						playerColumn = column;
						break;
					case 'M':
						this.objects.add(new BlockMoveable(column,
								currentLine, this));
						break;
					case '/':
						emptyCasesX.add(column);
						emptyCasesY.add(currentLine);
						break;
					default:
						break;
					}
				}
				player.setPos(playerColumn, playerLine); // set
																										// position
																										// of
																										// the
																										// player
				currentLine++;
			}
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

	private void loadMobs(ArrayList<Integer> emptyCasesX, ArrayList<Integer> emptyCasesY, int maxMobs) {
		// System.out.println(a);
		// System.out.println(b);

		ArrayList<Integer> mobXArray = new ArrayList<>();
		ArrayList<Integer> mobYArray = new ArrayList<>();

		Random random = new Random();
		for (int i = 0; i < maxMobs; i++) {
			if (emptyCasesX.size() > 0) {
				int randomInt = random.nextInt(emptyCasesX.size());
				int mobX = emptyCasesX.remove(randomInt);
				int mobY = emptyCasesY.remove(randomInt);
				mobXArray.add(mobX);
				mobYArray.add(mobY);
			} else {
				System.out.println("Not enough free cases for " + maxMobs + " mobs");
				break;
			}
		}
		// System.out.println(mobXArray);
		// System.out.println(mobYArray);

		for (int i = 0; i < mobXArray.size(); i++) {
			int posX = mobXArray.get(i);
			int posY = mobYArray.get(i);
			int randomInt = random.nextInt(2);
			try {
				if (bossBool) {
					this.objects.add(new Boss(posX, posY, this));
				} else {
					if (randomInt == 0) {
						this.objects.add(new Zombie(posX, posY, i * 1000 / mobXArray.size(), this));
					} else {
						this.objects.add(new Skeleton(posX, posY, i * 1000 / mobXArray.size(), this));
					}
				}
			} catch (IOException ex) {
			}
		}
	}

}
