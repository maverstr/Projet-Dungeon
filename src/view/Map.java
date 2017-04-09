package view;
import model.GameObject;
import CONSTANTS.CONSTANTS;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Map extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6724459904147376476L; //POUR LA SERIALIZATION
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private BufferedImage backSprite;
	
	public Map() throws IOException{
		this.setFocusable(true);
		this.requestFocusInWindow();
		backSprite = ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/Back.png").getFile()));
	}
	
	public void paint(Graphics g) { 
		for(int i = 0; i< CONSTANTS.MAP_BLOCK_WIDTH; i++){						
			for(int j = 0; j<CONSTANTS.MAP_BLOCK_HEIGTH; j++){
				int x = i;
				int y = j;
				g.drawImage (backSprite, x*CONSTANTS.BLOCK_SIZE, y*CONSTANTS.BLOCK_SIZE, CONSTANTS.BLOCK_SIZE, CONSTANTS.BLOCK_SIZE, null); 
			}// Paint a background sprite on the map
		}
		
		for(GameObject object : this.objects){
			int x = object.getPosX();
			int y = object.getPosY();
			
			g.drawImage (object.getSprite(), x, y, CONSTANTS.BLOCK_SIZE, CONSTANTS.BLOCK_SIZE, null); //Affiche le sprite de l'objet au bon endroit
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
	
	
}





