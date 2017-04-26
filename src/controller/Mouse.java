package controller;

import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


import CONSTANTS.CONSTANTS;
import model.Game;

public class Mouse implements MouseListener, MouseMotionListener{
	private Game game;
	public Mouse(Game game) {
		this.game = game;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {

		int mx = e.getX();
		int my = e.getY();

		if(game.state == Game.STATE.MENU){
			
			
			//playButton
			if(mx >= CONSTANTS.WINDOW_PIXEL_WIDTH/2 -150 && mx <= CONSTANTS.WINDOW_PIXEL_WIDTH/2 +150){
				if(my >= 450 && my <= 530){
					game.gameStart();
				}
			}
			//optionsButton
			if(mx >= CONSTANTS.WINDOW_PIXEL_WIDTH/2 -150 && mx <= CONSTANTS.WINDOW_PIXEL_WIDTH/2 +150){
				if(my >= 550 && my <= 630){
				}
			}
			//exitButton
			if(mx >= CONSTANTS.WINDOW_PIXEL_WIDTH/2 -150 && mx <= CONSTANTS.WINDOW_PIXEL_WIDTH/2 +150){
				if(my >= 650 && my <= 730){
					//Press
				}
			}
		}
		
//		public Rectangle playButton = new Rectangle(CONSTANTS.WINDOW_PIXEL_WIDTH/2 -150, 450,300,80);
//		public Rectangle optionsButton = new Rectangle(CONSTANTS.WINDOW_PIXEL_WIDTH/2 -150, 550,300,80);
//		public Rectangle exitButton = new Rectangle(CONSTANTS.WINDOW_PIXEL_WIDTH/2 -150, 650,300,80);
		
		
		
	}
	@Override
	public void mouseMoved(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		
		if(mx >= CONSTANTS.WINDOW_PIXEL_WIDTH/2 -150 && mx <= CONSTANTS.WINDOW_PIXEL_WIDTH/2 +150){
			if(my >= 450 && my <= 530){
				Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				game.getWindow().getMenu().setCursor(cursor);
			}
		}
		else{
		Cursor cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
		game.getWindow().getMenu().setCursor(cursor);
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
