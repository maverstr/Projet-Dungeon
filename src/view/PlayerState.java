package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

import model.Boss;
import model.Inventory;
import model.Item;
import model.Player;

public class PlayerState extends JPanel { // Jpanel for Player Stats and
											// Inventory at the right of the
											// screen

	private static final long serialVersionUID = -1983468608760988132L;
	private JProgressBar healthBar;
	private JProgressBar bossHealthBar;
	private Inventory inventory;
	

	public PlayerState() {
		this.setPreferredSize(new Dimension(200, CONSTANTS.CONSTANTS.MAP_HEIGHT)); // Set Size of screen
														// part for player STATS
		this.setFocusable(false);
		this.setLayout(null);

		healthBar = new JProgressBar(0,1); // Initialize the progress bar. Max health is always updated in paint in case of overheal or upgrade
    	healthBar.setString("Health");
    	healthBar.setStringPainted(true);
    	healthBar.setForeground(Color.GREEN);
    	healthBar.setBackground(Color.RED);
    	healthBar.setBounds(10, 10, 180, 20);
		this.add(this.healthBar);
		
		
		bossHealthBar = new JProgressBar(0,1); 
		bossHealthBar.setVisible(false);
		bossHealthBar.setString("HAELTI LIFE");
		bossHealthBar.setStringPainted(true);
		bossHealthBar.setForeground(Color.RED);
		bossHealthBar.setBackground(Color.BLUE);
		bossHealthBar.setBounds(10, CONSTANTS.CONSTANTS.MAP_HEIGHT-20, 180, 20); 
		this.add(this.bossHealthBar);
		
	}
	
	
	
	

	public void paintComponent(Graphics g) { // Note : DO NOT override paint(g).
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g; // Allows thickness for Rect
		g2.setStroke(new java.awt.BasicStroke(3)); // thickness of 3 for
													// countouring CURRENT
													// Weapon

		g.setColor(Color.darkGray); // Background color for Panel
		g.fillRect(0, 30, 200, CONSTANTS.CONSTANTS.MAP_HEIGHT-30);
		g.setColor(Color.blue);
		for (int e = 2; e <182; e+=60){ //Note the initialization at 2 because of the thickness of the line (=3)
				g.drawRect(e, 50,50, 50);
			}
		
		g.setColor(Color.green);
		for (int e = 2; e <182; e+=60){
			for (int l =120; l < 240; l+=60){
				g.drawRect(e, l,50, 50);
			}}
		g.drawRect(62, 260, 50, 50); // Case dedicated PASSIVE ITEM
		setPassiveText(27,240,g);


		// Paints INVENTORY
		int x = 2;
		int y = 50;
		int keyNumber = 0;
		try {
			for (int i = 0; i < this.inventory.weapons.size(); i++) { // WEAPON
																		// inventory
				setKeyNumber(x,y,keyNumber,g);
				keyNumber++;
				
				Item item = this.inventory.weapons.get(i);

				g.drawImage(item.getSprite(), x, y, 50, 50, null); // Paints
																	// sprite of
																	// weapon
				if (i == this.inventory.getWeaponIndex()) { // If CURRENT
															// Weapon, red
															// Countouring
					g.setColor(Color.RED);
					g.drawRect(x, y, 50, 50);
				}
				x += 60; /*
							 * TODO: Do some Math here to place it correctly
							 * whatever PlayerState Dimensions are
							 */
				if (x >= 200) {
					x = 0;
					y += 60;
				}
			}
		} catch (NullPointerException e) { // At the beginning player may not be
											// created -> nullpointer
			System.out.println("player not created yet A" + e);
		}

		x = 2;
		y = 120;
		try {
			for (int i = 0; i < this.inventory.consumables.size(); i++) { // CONSUMABLE
																			// Inventory
				setKeyNumber(x,y,keyNumber,g);
				keyNumber++;
				
				Item item = this.inventory.consumables.get(i);

				g.drawImage(item.getSprite(), x, y, 50, 50, null);// Paints
																	// sprite of
																	// consumable
				if (item.getDurability() > 1) { /*
												 * display 'quantity' only if >
												 * 1 of this item in inventory
												 */
					g.setColor(Color.RED);
					g.fillOval(x + 40, y + 40, 20, 20); // Red oval for
														// durability
					g.setColor(Color.GREEN);
					g.setFont(new Font("default", Font.BOLD, 16));
					g.drawString(String.format("%d", item.getDurability()), x + 46, y + 56);// #durability
				}
				x += 60; /*
							 * TODO: Do some Math here to place it correctly
							 * whatever PlayerState Dimensions are
							 */
				if (x >= 200) {
					x = 0;
					y += 60;
				}
			}
		} catch (NullPointerException e) { // At the beginning player may not be
											// created -> nullpointer
			System.out.println("player not created yet B" + e);
		}
	}
	
	private void setKeyNumber(int x, int y, int keyNumber, Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x + 42, y - 20, 20, 20); // Black oval for
											// key number
		g.setColor(Color.WHITE);
		g.setFont(new Font("default", Font.BOLD, 16));
		g.drawString(String.format("%d", keyNumber+1), x + 48, y - 4);// #key number
	}
	
	private void setPassiveText(int x, int y, Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(x, y, 120, 18); // 
		g.setColor(Color.WHITE);
		g.setFont(new Font("default", Font.BOLD, 16));
		g.drawString(String.format("PASSIVE ITEM"), x+5, y+15);// #key number
	}

	public void redraw(Player p, Boss b, boolean bossBool) {
		int pMaxHealth = p.getMaxHealth();
		int pHealth = p.getHealth();
		int bMaxHealth = b.getMaxHealth();
		int bHealth = b.getHealth();
		try {
	    	this.healthBar.setString(String.format("Health %d/%d", pHealth, pMaxHealth));
			this.healthBar.setMaximum(pMaxHealth);
			this.healthBar.setValue(pHealth); //update healthBar at each redraw (Window.update)
			this.inventory = p.getInventory(); //update the inventory list to draw
			
			
			if(bossBool){
		    	this.bossHealthBar.setString(String.format("HAELTI LIFE %d/%d", bHealth, bMaxHealth));
				this.bossHealthBar.setVisible(true); //This Healthbar only appears when in the boss room
				this.bossHealthBar.setValue(bHealth);
				this.bossHealthBar.setMaximum(bMaxHealth);
			}

			this.repaint();
		} catch (NullPointerException e) { // At the beginning player may not be
											// created -> nullpointer
			System.out.println("player not created yet C" + e);
		}
	}
	

}
