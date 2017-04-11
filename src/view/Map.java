package view;
import model.GameObject;
import CONSTANTS.CONSTANTS;

import java.awt.Dimension;
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
	private static final long serialVersionUID = 6724459904147376476L; // SERIALIZATION
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private BufferedImage backSprite;
	
	public Map() throws IOException{
		this.setPreferredSize(new Dimension(CONSTANTS.MAP_WIDTH, CONSTANTS.MAP_HEIGHT));
		this.setFocusable(true);
		this.requestFocusInWindow();
		backSprite = ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/Back.png").getFile()));
	}
	
//	public void paint(Graphics g) { 
	public void paintComponent(Graphics g) { //Note : DO NOT override paint(g) 
		super.paintComponent(g);
		for(int i = 0; i< CONSTANTS.MAP_BLOCK_WIDTH; i++){						
			for(int j = 0; j<CONSTANTS.MAP_BLOCK_HEIGTH; j++){
				int x = i;
				int y = j;
				g.drawImage (backSprite, x*CONSTANTS.BLOCK_SIZE, y*CONSTANTS.BLOCK_SIZE, CONSTANTS.BLOCK_SIZE, CONSTANTS.BLOCK_SIZE, null); 
			}// Paint a background sprite on the map
		}
			/* TODO : need to work on a 'deepcopy' in order to prevent concurrent to objects from here and from Game,
			 * otherwise we get a ConcurrentModificationException. */
			ArrayList<GameObject> clone = (ArrayList<GameObject>) objects.clone();
			for(GameObject object : clone){ //Paint the sprite of the object at the right place
				int x = object.getPosX();
				int y = object.getPosY();
				g.drawImage (object.getSprite(), x, y, CONSTANTS.BLOCK_SIZE, CONSTANTS.BLOCK_SIZE, null);
			}
		
	}
	
	public void setObjects(ArrayList<GameObject> objects){
		this.objects = objects;
		System.out.println(String.format("in map SetObjects, objects is : %s",objects));
	}
	
	public void redraw(){
		this.repaint();
	}
	
	
}





