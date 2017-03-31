package view;
import model.BlockBreakable;
import model.BlockNotBreakable;
import model.GameObject;
import model.Game;
import model.Player;
import CONSTANTS.CONSTANTS;

import java.awt.Color;
import java.awt.Graphics;
import java.io.*;
import java.util.ArrayList;
import java.lang.Object;

import javax.swing.JPanel;

public class Map extends JPanel {
	private ArrayList<GameObject> objects = new ArrayList<GameObject> ();
	
	public Map(){
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	public void paint(Graphics g) { 
		for(int i = 0; i<20; i++){						
			for(int j = 0; j<20; j++){
				int x = i;
				int y = j;
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(x*50, y*50, 48, 48); 
				g.setColor(Color.BLACK);
				g.drawRect(x*50, y*50, 48, 48); 
			}
		}
		
		for(GameObject object : this.objects){
			int x = object.getPosX();
			int y = object.getPosY();
			//Sprite sprite = object.getSprite();			
			
			g.drawImage (object.getsprite(), x, y, 50, 50, null); //Affiche le sprite de l'objet au bon endroit
			//System.out.println(g.drawImage (object.getsprite(), x, y, null)); //Return true si 
																			//l'image est bien loadée
			
		}
	}
	
	public void setObjects(ArrayList<GameObject> objects){
		this.objects = objects;
	}
	
	public void redraw(){
		this.repaint();
	}
	
	public void loadMap(String fileName){		//Lit la map et remplit la liste des objets
		try{
			String current = new java.io.File( "." ).getCanonicalPath(); //permet de savoir dans quel r�pertoire le compilateur 
	        System.out.println("Current dir:"+current);						//se trouve actuellement
			File file = new File(Map.class.getResource("/resources/map/"+fileName).getFile());
			String line = null;
			FileReader fileReader = new FileReader(file);
			
            BufferedReader bufferedReader = 
                    new BufferedReader(fileReader);
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
                        		// Creating one Player at position (1,1)		
                        case 'P' : objects.add(new Player(column*CONSTANTS.BLOCK_SIZE, currentLine*CONSTANTS.BLOCK_SIZE));
                                break;
                        default:
                                break;
                              
                        }
                    } currentLine++;
                }
               bufferedReader.close(); 
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





