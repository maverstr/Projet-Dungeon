package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


import CONSTANTS.CONSTANTS;
import model.GameObject;

public class Menu extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private int wPW = CONSTANTS.getWINDOW_PIXEL_WIDTH();
	private int wPH = CONSTANTS.getWINDOW_PIXEL_HEIGHT();
	
	public Rectangle playButton = new Rectangle(wPW/2 -150, 450,300,80);
	public Rectangle optionsButton = new Rectangle(wPW/2 -150, 550,300,80);
	public Rectangle exitButton = new Rectangle(wPW/2 -150, 650,300,80);
	
	private BufferedImage mainScreen;

	
	public Menu() throws IOException {	
		this.setPreferredSize(new Dimension(wPW, wPH));
		this.setFocusable(true);
		this.setEnabled(true);
		this.setRequestFocusEnabled(true);
		this.requestFocusInWindow();
		this.setLayout(null);
		
		mainScreen = ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/main_screen.png").getFile()));

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_TEXT_ANTIALIASING,
	             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    g2.setRenderingHints(rh);
		
		this.setBackground(Color.BLACK);
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g2.setFont(fnt0);
		g2.setColor(Color.WHITE);
		g2.drawString("   La Mine", wPW/2 -120, 100);
		g2.draw(playButton);
		g2.draw(optionsButton);
		g2.draw(exitButton);
		g2.drawString("New Game", playButton.x+20, playButton.y+60);
		g2.drawString("Story and", optionsButton.x+30, optionsButton.y+40);
		g2.drawString("Controls", optionsButton.x+45, optionsButton.y+80);
		g2.drawString("Quit Game", exitButton.x+25, exitButton.y+60);
		g2.drawImage(mainScreen, 100, 32, 780, 400, null);
	}
	
	public void redraw(){
		this.requestFocusInWindow();
		this.repaint();
		
	}



}

