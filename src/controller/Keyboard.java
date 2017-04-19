package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Game;


public class Keyboard implements KeyListener{
	private Game game;
	
	public Keyboard(Game game){
		this.game = game;
		System.out.println(game.state);

	}

	//@Override
	public void keyPressed(KeyEvent event) {
		int key = event.getKeyCode();
		System.out.println(key);		
		if (game.state == Game.STATE.GAME){ //Only when in the game and not in the menu
			System.out.println("key GAME");
		switch (key){
			case KeyEvent.VK_RIGHT: 
				game.movePlayer(1, 0); /* TODO: replace all with direction enum */
				break;
			case KeyEvent.VK_LEFT:
				game.movePlayer(-1, 0);
				break;
			case KeyEvent.VK_DOWN:
				game.movePlayer(0, 1);
				break;
			case KeyEvent.VK_UP:
				game.movePlayer(0, -1);
				break;
			case KeyEvent.VK_Z:
				System.out.println("key hit up");
				game.playerUseWeapon(0, -1);
				break;
			case KeyEvent.VK_Q:
				System.out.println("key hit left");
				game.playerUseWeapon(-1, 0);
				break;
			case KeyEvent.VK_S:
				System.out.println("key hit down");
				game.playerUseWeapon(0, 1);
				break;
			case KeyEvent.VK_D:
				System.out.println("key hit right");
				game.playerUseWeapon(1, 0);
				break;
			/*case KeyEvent.VK_A:
				game.playerChangeTool();
				break;*/

			case 49:
			case 50:
			case 51:
			case 52:
			case 53:
			case 54:
			case 55:
			case 56:
			case 57:
				game.itemAtIndex(key-49); //numbers set items in inventory, begin with 1 to 9
				break;
			case 48: game.itemAtIndex(key-39); // 0 is equal to item #10
				
				/*
			case KeyEvent.VK_SPACE:
				game.dropBomb("nuke", player1);
				break;
			case KeyEvent.VK_B:
				game.dropBomb("bomb", player1);
				*/
		}
		}
		else if(game.state == Game.STATE.MENU){
			System.out.println("key MENU");
			switch(key){
			case KeyEvent.VK_ENTER:
				game.setState(Game.STATE.MENU);
				System.out.println("key ENTER");
				break;
			}
		}
		
	}

	//@Override
	public void keyTyped(KeyEvent e) {
	}

	//@Override
	public void keyReleased(KeyEvent e) {
	}

}
