package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import CONSTANTS.CONSTANTS;
import model.Player;

import model.GameObject;
import view.Map;
//import java.awt.Window;
import view.Window;

public class Game {
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private Window window;
	private Player player= new Player(0, 0);
	
	public Game(Window window) throws IOException{
		this.window = window;
		objects.add(player);
		loadMap("map_1.txt");

		window.setGameObjects(objects);
	}
	
	public void movePlayer(int xMove,int yMove) {
		
		
		player.move(xMove, yMove);
		window.setGameObjects(objects);
		//window.update();
	}
	
	public ArrayList<GameObject> getGameObjects() {
		return this.objects;
	}
	
	public void loadMap(String fileName){		//Lit la map et remplit la liste des objets
		try{

			int playerLine = 0;
			int playerColumn = 0;
//			String current = new java.io.File( "." ).getCanonicalPath(); //permet de savoir dans quel r�pertoire le compilateur 
//	        System.out.println("Current dir:"+current);						//se trouve actuellement
			File file = new File(Map.class.getResource("/resources/map/"+fileName).getFile());
			String line = null;
			FileReader fileReader = new FileReader(file);
			
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int currentLine = 0;
                while((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                    for (int column = 0; column < line.length(); column++){
                    	char c = line.charAt(column);
                        switch (c) {
                        case '*' : this.objects.add(new BlockNotBreakable(column*CONSTANTS.BLOCK_SIZE, currentLine*CONSTANTS.BLOCK_SIZE));
                                break;
                        case '$' : this.objects.add(new BlockBreakable(column*CONSTANTS.BLOCK_SIZE, currentLine*CONSTANTS.BLOCK_SIZE));
                                break;
                        		// Creating one Player at position P		
                        case 'P' : playerLine = currentLine;
                        		playerColumn = column;
                                break;
                        case '/' : break;
                        default:
                                break;
                              
                        }
                    }
                    player.setPos(playerColumn*CONSTANTS.BLOCK_SIZE, playerLine*CONSTANTS.BLOCK_SIZE); //set position of the player
                    currentLine++;

                }
               bufferedReader.close();
//               System.out.println(player.getPosX());
//               System.out.println(objects.get(0).getPosX());
			}
		catch(FileNotFoundException e){
            System.out.println(
                    "Unable to open file '" + 
                    fileName + "'"+e);      
			}
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
              
        	} System.out.println(objects);
	}
	

}
