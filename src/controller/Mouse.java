package controller;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import CONSTANTS.CONSTANTS;
import model.Game;
import model.Game.STATE;

public class Mouse implements MouseListener, MouseMotionListener{
	private Game game;
	
	private int wPW = CONSTANTS.getWINDOW_PIXEL_WIDTH();

	public Mouse(Game game) {
		this.game = game;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {

		int mx = e.getX();
		int my = e.getY();

		if(game.getState() == Game.STATE.MENU){
			
			
			//playButton
			if(mx >= wPW/2 -150 && mx <= wPW/2 +150){
				if(my >= 450 && my <= 530){
					game.setState(Game.STATE.CLASS);
					game.updateWindow();
					//game.gameStart();
				}
			}
			//optionsButton
			if(mx >= wPW/2 -150 && mx <= wPW/2 +150){
				if(my >= 550 && my <= 630){
					game.setState(Game.STATE.STORY);
					game.updateWindow();
				}
			}
			//exitButton
			if(mx >= wPW/2 -150 && mx <= wPW/2 +150){
				if(my >= 650 && my <= 730){
					System.exit(0);
				}
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

		if(game.getState() == Game.STATE.MENU){
			if(mx >= wPW/2 -150 && mx <= wPW/2 +150){
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
