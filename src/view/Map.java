package view;
import model.GameObject;
import model.Sprite;
import CONSTANTS.CONSTANTS;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

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
		this.setEnabled(true);
		this.setRequestFocusEnabled(true);
		//this.requestFocusInWindow();
		backSprite = ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/Back.png").getFile()));
	}
	
	public void paintComponent(Graphics g) { //Note : DO NOT override paint(g) 
		super.paintComponent(g);
		for(int i = 0; i< CONSTANTS.MAP_BLOCK_WIDTH; i++){						
			for(int j = 0; j<CONSTANTS.MAP_BLOCK_HEIGHT; j++){
				int x = i;
				int y = j;
				g.drawImage(backSprite, x*CONSTANTS.BLOCK_SIZE, y*CONSTANTS.BLOCK_SIZE, CONSTANTS.BLOCK_SIZE, CONSTANTS.BLOCK_SIZE, null); 
			}// Paint a background sprite on the map
		}

		ArrayList<GameObject> clone = (ArrayList<GameObject>) objects.clone(); //Clone() allows to create a DEEPCOPY of the list to get the variables without actually blocking the real list
		//System.out.println("size : " + clone.size());
		ArrayList<Sprite> totalSpriteList = new ArrayList<Sprite>();
		for(GameObject object : clone) { //Paint the sprite of the object at the right place
			int x = object.getPosX();
			int y = object.getPosY();
			ArrayList<Sprite> spriteList = object.getSpriteList();
			for (Sprite sprite:spriteList) {
				sprite.setDrawPosition(x, y);
				totalSpriteList.add(sprite);
			}
		}
		
		Collections.sort(totalSpriteList,(sprite1, sprite2) -> sprite1.getDrawZ()-sprite2.getDrawZ()); //sort the list by ascending zPosition
		
		for (Sprite sprite:totalSpriteList) {
			double xDouble = sprite.getDrawX()*CONSTANTS.BLOCK_SIZE;
			double yDouble = sprite.getDrawY()*CONSTANTS.BLOCK_SIZE;
			g.drawImage(sprite.getImage(), (int) xDouble, (int) yDouble, CONSTANTS.BLOCK_SIZE, CONSTANTS.BLOCK_SIZE, null);
		}
		

	}
	
	public void setObjects(ArrayList<GameObject> objects){
		this.objects = objects;
	}
	
	public void redraw(){
		this.requestFocusInWindow();
		this.repaint();
	}
	
	
}





