package view;
import model.GameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.io.*;
import java.util.ArrayList;
import java.lang.Object;

import javax.swing.JPanel;

public class Map extends JPanel {
	private ArrayList<GameObject> objects;
	
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
		
//		for(GameObject object : this.objects){
//			int x = object.getPosX();
//			int y = object.getPosY();
//			Sprite sprite = object.getSprite();			
//			
//			if(color == 0){
//				g.setColor(Color.DARK_GRAY);
//			}else if(color == 1){
//				g.setColor(Color.GRAY);
//			}else if(color == 2){
//				g.setColor(Color.BLUE);
//			}else if(color == 3){
//				g.setColor(Color.GREEN);
//			}else if(color == 4){
//				g.setColor(Color.RED);
//			}else if(color == 5){
//				g.setColor(Color.ORANGE);
//			}

//			g.fillRect(x*50, y*50, 48, 48);
//			g.setColor(Color.BLACK);
//			g.drawRect(x*50, y*50, 48, 48); 
//		}
	}
	
	public void setObjects(ArrayList<GameObject> objects){
		this.objects = objects;
	}
	
	public void redraw(){
		this.repaint();
	}
	
	public void loadMap(String fileName){		//Lit la map et remplit la liste des objets
		try{
			String current = new java.io.File( "." ).getCanonicalPath(); //permet de savoir dans quel répertoire le compilateur 
	        System.out.println("Current dir:"+current);						//se trouve actuellement
			File file = new File(Map.class.getResource("/resources/map/"+fileName).getFile());
			String line = null;
			FileReader fileReader = new FileReader(file);
			
            BufferedReader bufferedReader = 
                    new BufferedReader(fileReader);

                while((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                    for (int column = 0; column < line.length(); column++){
                    	char c = line.charAt(column);
                        switch (c) {
                        case '*' : this.objects.add(new BlockUnbreakable(column, line));
                                break;
                        case '$' : this.objects.add(new BlockBreakable(column, line));
                                break;
                        case 'P' : this.player.setPosition(column, line);
                                break;
                        default:
                                break;
                              
                        }
                    }
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
              
        	}
	}
}





