package controller;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import CONSTANTS.CONSTANTS;
import model.Game;
import model.GameObject;
import model.Game.STATE;

public class Mouse implements MouseListener, MouseMotionListener{
	private Game game;
	
	private int wPW = CONSTANTS.getWINDOW_PIXEL_WIDTH();
	private Rectangle playButton;
	private Rectangle controlsButton;
	private Rectangle exitButton;


	public Mouse(Game game) {
		this.game = game;
		playButton = game.getWindow().getMenu().getPlayButton();
		controlsButton = game.getWindow().getMenu().getControlsButton();
		exitButton = game.getWindow().getMenu().getExitButton();
		


	}
	
	@Override
	public void mousePressed(MouseEvent e) {

		int mx = e.getX();
		int my = e.getY();

		if(game.getState() == Game.STATE.MENU){
			
			
			//playButton
			if(playButton.contains(mx, my)){
					game.setState(Game.STATE.CLASS);
					game.updateWindow();
					//game.gameStart();
			}
			//optionsButton
			if(controlsButton.contains(mx, my)){
					game.setState(Game.STATE.STORY);
					game.updateWindow();
			}
			//exitButton
			if(exitButton.contains(mx, my)){
					System.exit(0);
			}
		}
		
		else if(game.getState() == Game.STATE.CLASS){
			if(mx < wPW/3){
				game.ChooseClass(1);
			}
			else if(mx < (2*wPW)/3 && mx > wPW/3){
				game.ChooseClass(2);
			}
			else if(mx > (2*wPW)/3){
				game.ChooseClass(3);
			}
		}
		else if(game.getState() == Game.STATE.STORY){
			game.setState(Game.STATE.MENU);
			game.updateWindow();
		}
		else if(game.getState() == Game.STATE.OVER){
			game.setState(Game.STATE.MENU);
		}

		
		
	}
	@Override
	public void mouseMoved(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("/resources/sprites/Boss_U.png");
		try {
			image = ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/Boss_U.png").getFile()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Cursor cursor = toolkit.createCustomCursor(image , new Point(0,0), "");

		if(game.getState() == Game.STATE.MENU){
			if(playButton.contains(mx, my) || controlsButton.contains(mx, my) || exitButton.contains(mx, my)){
					//Cursor cursor = Cursor.getPredefinedCursor(a);
					game.getWindow().getMenu().setCursor(cursor);
			}
			else{
				Cursor classicCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
				game.getWindow().getMenu().setCursor(classicCursor);
			}

		}

	}

	
	
	
	
	
	
	
	
	////////////////////////////////////////////

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}



	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}


}
