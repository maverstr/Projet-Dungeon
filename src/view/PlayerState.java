package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;


import javax.swing.JPanel;
import javax.swing.JProgressBar;

import model.Inventory;
import model.Item;
import model.Player;

public class PlayerState extends JPanel { //Jpanel for Player Stats and Inventory at the right of the screen

	private static final long serialVersionUID = -1983468608760988132L;
		private JProgressBar healthBar;
		private Inventory inventory;


	public PlayerState() {
		this.setPreferredSize(new Dimension(200, 600)); //Set Size of screen part for player STATS
		this.setFocusable(true);
		this.setLayout(null);
		

		healthBar = new JProgressBar(0,5);
    	healthBar.setString("Health");
    	healthBar.setStringPainted(true);
    	healthBar.setBounds(10, 10, 180, 20);
		this.add(this.healthBar);
	}
	
	public void paintComponent(Graphics g)  { //Note : DO NOT override paint(g). 
		super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D) g; //Allows thickness for Rect
		g2.setStroke(new java.awt.BasicStroke(3)); // thickness of 3 for countouring CURRENT Weapon

		g.setColor(Color.darkGray); //Background color for Panel
		g.fillRect(0, 30, 200, 570);
		
		//Paints INVENTORY
		int x = 0;
		int y = 50;
		for (int i = 0; i < this.inventory.weapons.size(); i ++) { //WEAPON inventory
			Item item = this.inventory.weapons.get(i);
		
			g.drawImage (item.getSprite(), x, y, 50,50, null); //Paints sprite of weapon
			if (i == this.inventory.getWeaponIndex()) { // If CURRENT Weapon, red Countouring
				g.setColor(Color.RED);
				g.drawRect(x, y, 50, 50);
			}
			x+=60;
			if (x>=200) {
				x=0;
				y+=60;
			}

		}
			
		 x = 0;
		 y = 120;
		
		for (int i = 0; i < this.inventory.consumables.size(); i ++) { //CONSUMABLE Inventory
			Item item = this.inventory.consumables.get(i);

			g.drawImage (item.getSprite(), x, y, 50,50, null);//Paints sprite of consumable
			g.setColor(Color.RED);
			g.fillOval(x+40, y+40, 20,20); //Red oval for durability
			g.setColor(Color.GREEN);
			g.setFont(new Font("default", Font.BOLD, 16)); 
			g.drawString(String.format("%d",item.getDurability()), x+46, y+56);// #durability
			
			x+=60;
			if (x>=200) {
				x=0;
				y+=60;
			}
			
		}
		

	
	}
	public void redraw(Player p){
		try {
			this.healthBar.setValue(p.getHealth()); //update healthBar at each redraw (Window.update)
			this.inventory = p.getInventory(); //update the inventory list to draw
		this.repaint();
		} catch (NullPointerException e) { //At the beginning player may not be created -> nullpointer
			System.out.println("player not created yet"+e);
		}
	}
	

}
