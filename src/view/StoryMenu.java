package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import CONSTANTS.CONSTANTS;
import model.GameObject;

public class StoryMenu extends JPanel{
	private static final long serialVersionUID = 42L;

	private int wPW = CONSTANTS.getWINDOW_PIXEL_WIDTH();
	private int wPH = CONSTANTS.getWINDOW_PIXEL_HEIGHT();

	private transient BufferedImage storyScreen;

	public StoryMenu() throws IOException {
		this.setPreferredSize(new Dimension(wPW, wPH));
		this.setFocusable(true);
		this.setEnabled(true);
		this.setRequestFocusEnabled(true);
		this.requestFocusInWindow();
		this.setLayout(null);

		storyScreen = ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/Story_Screen.png").getFile()));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		this.setBackground(Color.WHITE);

		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHints(rh);

		g2.drawImage(storyScreen, 0, 0, wPW-16, wPH-38, null);
	}

	public void redraw(){
		this.requestFocusInWindow();
		this.repaint();

	}

}
