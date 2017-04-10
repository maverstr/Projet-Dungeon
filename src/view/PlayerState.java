package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import CONSTANTS.CONSTANTS;
import model.GameObject;
import model.Inventory;
import model.Item;
import model.Player;

public class PlayerState extends JPanel {
		private JProgressBar healthBar;
		private Inventory inventory;


	public PlayerState() {
		this.setPreferredSize(new Dimension(200, 600));
		this.setFocusable(true);
		this.setLayout(null);
		

		healthBar = new JProgressBar(0,5);
    	healthBar.setString("Health");
    	healthBar.setStringPainted(true);
    	healthBar.setBounds(10, 10, 180, 20);
		this.add(this.healthBar);
	}
	
	//public void paint(Graphics g)  {
	public void paintComponent(Graphics g)  {
		super.paintComponent(g);
		System.out.println("painting player state");
//		try {
//			BufferedImage backSprite = ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/Back.png").getFile()));
//		
//			
//			for(int i = 0; i< 4; i++){						
//				for(int j = 0; j<12; j++){
//					int x = i;
//					int y = j;
//					g.drawImage (backSprite, x*CONSTANTS.BLOCK_SIZE, y*CONSTANTS.BLOCK_SIZE, CONSTANTS.BLOCK_SIZE, CONSTANTS.BLOCK_SIZE, null); 
//				}// Paint a background sprite on the map
//			}
//		} catch (IOException e) {
//			System.out.println("error loading buffered image");
//		}
//		g.setColor(Color.BLUE);
//		g.fillRect(0, 30, 200, 570);
		int x = 0;
		int y = 50;
		for(Item item : this.inventory.items){
			System.out.println(String.format("xxxxx Inventory : %s",item));
			g.drawImage (item.getSprite(), x, y, 50,50, null);
			g.setColor(Color.RED);
			g.drawRect(x, y, 50, 50);
			g.fillOval(x+40, y+40, 20,20);
			g.setColor(Color.GREEN);
			g.drawString("1", x+50, y+50);
			
			x+=50;
			if (x>=200) {
				x=0;
				y+=50;
			}
			
		}

	
	}
	public void redraw(Player p){
		try {
			this.healthBar.setValue(p.getHealth());
			this.inventory = p.getInventory(); 
		this.repaint();
		} catch (NullPointerException e) {
			System.out.println("player not created yet"+e);
		}
	}
	
	public static void barUpdate(int i){
		//PlayerState.healthBar.setValue(i);
	}

}
