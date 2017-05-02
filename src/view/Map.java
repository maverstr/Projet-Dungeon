package view;
import model.GameObject;
import model.Player;
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
	private BufferedImage backSprite_transparent;

	private Player player;
	
	public Map() throws IOException{
		this.setPreferredSize(new Dimension(CONSTANTS.getMAP_WIDTH(), CONSTANTS.getMAP_HEIGHT()));
		this.setFocusable(true);
		this.setEnabled(true);
		this.setRequestFocusEnabled(true);
		backSprite = ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/Back.png").getFile()));
		backSprite_transparent = ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/Back_transparent.png").getFile()));

	}

	
	public synchronized void paintComponent(Graphics g) { //Note : DO NOT override paint(g) 
		super.paintComponent(g);
		int los = CONSTANTS.getLINE_OF_SIGHT();
		int bs = CONSTANTS.getBLOCK_SIZE();
		for(int i = 0; i< CONSTANTS.getMAP_BLOCK_WIDTH(); i++){						
			for(int j = 0; j<CONSTANTS.getMAP_BLOCK_HEIGHT(); j++){
				int x = i;
				int y = j;
				
									// Paint a background sprite on the map
				if(CONSTANTS.getDARKNESS_MODIFIER()){//If DARKNESS mode is activated, blocks not in sight are half-transparent
					if(Math.abs(x - player.getPosX()) < los && Math.abs(y - player.getPosY()) < los){
						g.drawImage(backSprite, x*bs, y*bs, bs, bs, null); 
					}
					else{
						g.drawImage(backSprite_transparent, x*bs, y*bs, bs,bs, null); 
					}

				}
				else{ 
					g.drawImage(backSprite, x*bs, y*bs, bs, bs, null); 
				}

	}
}

		ArrayList<GameObject> clone = (ArrayList<GameObject>) objects.clone(); //Clone() allows to create a DEEPCOPY of the list to get the variables without actually blocking the real list
		ArrayList<Sprite> totalSpriteList = new ArrayList<Sprite>();
		synchronized (objects){
			for(GameObject object : clone) { //Paint the sprite of the object at the right place
				try {
					int x = object.getPosX();
					int y = object.getPosY();
					ArrayList<Sprite> spriteList = object.getSpriteList();
					for (Sprite sprite:spriteList) {
						if(CONSTANTS.getDARKNESS_MODIFIER()){
							if(Math.abs(x - player.getPosX()) < los && Math.abs(y - player.getPosY()) < los){
								sprite.setDrawPosition(x, y);
								totalSpriteList.add(sprite);
							}
						}
						else{
						sprite.setDrawPosition(x, y);
						totalSpriteList.add(sprite);
						}
					}
				}catch (Exception e){
					System.out.println(e.getMessage());
					System.out.println(object);
					/*
					System.out.println(object.getPosX());
					System.out.println(object.getPosY());
					*/
				}
				
			}
		}
		
		Collections.sort(totalSpriteList,(sprite1, sprite2) -> sprite1.getDrawZ()-sprite2.getDrawZ()); //sort the list by ascending zPosition
		
		for (Sprite sprite:totalSpriteList) {
			double xDouble = sprite.getDrawX()*bs;
			double yDouble = sprite.getDrawY()*bs;
			g.drawImage(sprite.getImage(), (int) xDouble, (int) yDouble, bs, bs, null);
		}
		

	}
	
	public synchronized void setObjects(ArrayList<GameObject> objects){
		this.objects = objects;
	}
	
	public void redraw(Player player){
		this.player = player;
		this.requestFocusInWindow();
		this.repaint();
	}
	
	
}





