package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import CONSTANTS.CONSTANTS;
import model.GameObject;

public class WinScreen extends JPanel{
private static final long serialVersionUID = 1L;
	
	private int wPW = CONSTANTS.getWINDOW_PIXEL_WIDTH();
	private int wPH = CONSTANTS.getWINDOW_PIXEL_HEIGHT();
	
	private transient BufferedImage winScreen;

	public WinScreen() throws IOException {
		this.setPreferredSize(new Dimension(wPW, wPH));
		this.setFocusable(true);
		this.setEnabled(true);
		this.setRequestFocusEnabled(true);
		this.requestFocusInWindow();
		this.setLayout(null);
		
		winScreen = ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/winScreen.png").getFile()));	}
	

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		g.drawImage(winScreen, 0, 0, wPW-16, wPH-38, null);
	}
	
	public void redraw(){
		this.requestFocusInWindow();
		this.repaint();
		
	}

}
