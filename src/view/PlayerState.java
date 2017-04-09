package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import CONSTANTS.CONSTANTS;
import model.GameObject;

public class PlayerState extends JPanel {
	

	public PlayerState() {
		this.setPreferredSize(new Dimension(200, 600));
		this.setFocusable(true);
		
	}
	
	public void paint(Graphics g)  {
		System.out.println("painting player state");
		try {
			BufferedImage backSprite = ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/Back.png").getFile()));
		
			
//			for(int i = 0; i< 4; i++){						
//				for(int j = 0; j<12; j++){
//					int x = i;
//					int y = j;
//					g.drawImage (backSprite, x*CONSTANTS.BLOCK_SIZE, y*CONSTANTS.BLOCK_SIZE, CONSTANTS.BLOCK_SIZE, CONSTANTS.BLOCK_SIZE, null); 
//				}// Paint a background sprite on the map
//			}
		} catch (IOException e) {
			System.out.println("error loading buffered image");
		}
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 200, 600);
		g.setColor(Color.RED);
		g.fillRect(50, 50, 100, 500);
		
	}
	public void redraw(){
		this.repaint();
	}

}
