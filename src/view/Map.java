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
	/**
	 * 
	 */
	private static final long serialVersionUID = 6724459904147376476L; //POUR LA SERIALIZATION
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public Map(){
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	public void paint(Graphics g) { 
		for(int i = 0; i< CONSTANTS.MAP_WIDTH; i++){						
			for(int j = 0; j<CONSTANTS.MAP_HEIGTH; j++){
				int x = i;
				int y = j;
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(x*CONSTANTS.BLOCK_SIZE, y*CONSTANTS.BLOCK_SIZE, CONSTANTS.BLOCK_SIZE -2, CONSTANTS.BLOCK_SIZE -2); 
				g.setColor(Color.BLACK);
				g.drawRect(x*CONSTANTS.BLOCK_SIZE, y*CONSTANTS.BLOCK_SIZE, CONSTANTS.BLOCK_SIZE -2 ,CONSTANTS.BLOCK_SIZE -2); 
			}
		}
		
		for(GameObject object : this.objects){
			int x = object.getPosX();
			int y = object.getPosY();
			//Sprite sprite = object.getSprite();			
			
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





