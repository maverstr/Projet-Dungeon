package Main;

import controller.Keyboard;
import model.Game;
import view.Window;

public class Main {
	public static void main(String[] args) {
		System.out.println("hello");
			Window window = new Window();
			
			Game game = new Game(window);
			Keyboard keyboard = new Keyboard(game);
			window.setKeyListener(keyboard);
	}
}

