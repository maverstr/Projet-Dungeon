package Main;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

import controller.Keyboard;
import javafx.embed.swing.JFXPanel;
import model.Game;
import view.*;

public class Main {
	public static void main(String[] args) throws IOException{
		
		initializeAudio();
		
		System.out.println("hello");
		Window window = new Window();
		window.update();
		
		Game game = new Game(window);
		Keyboard keyboard = new Keyboard(game);
		window.setKeyListener(keyboard);

		//System.out.println(Map.loadMap("map_1.txt"));

	}
	
	private static void initializeAudio() {
		final CountDownLatch latch = new CountDownLatch(1);
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        new JFXPanel();
		        latch.countDown();
		    }
		});
		try {
			latch.await();
		} catch (Exception e) {}
	}
}
