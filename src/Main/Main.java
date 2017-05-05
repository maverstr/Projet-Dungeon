package Main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import CONSTANTS.CONSTANTS;
import controller.Keyboard;
import controller.Mouse;
import javafx.embed.swing.JFXPanel;
import model.Game;
import view.*;

public class Main {
	
	private static Keyboard keyboard;
	private static Mouse mouse;
	
	public static void main(String[] args) throws IOException{
		
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); //For cross-platform progress bar;
		} catch (Exception e) {}
		
		initializeAudio();
		
		System.out.println("hello");
		
		
		Window window = new Window();
		
		Game game = new Game(window);
		
		keyboard = new Keyboard(game);
		mouse = new Mouse(game);
		window.setKeyListener(keyboard);
		window.setMouseListener(mouse);
		
		game.addRedrawObserver(window);
		
		
	}

	public static void save(Game game) {
		//Save when entering a new room
		System.out.println("save");
		try {
			FileOutputStream file = new FileOutputStream("personne.serial");
			System.out.println(file);
			ObjectOutputStream oos= new ObjectOutputStream(file);
			System.out.println(oos);
			oos.writeObject(game);
			System.out.println(oos);
			oos.flush(); 
			oos.close();
			System.out.println("ok");
			// Writes the content of the object to the file // Flushes the buffer
			// Closes the ObjectOutputStream oos
		}catch(Exception e){
			System.out.println("catch save");
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	private static Game load() {
		Game gameloaded = null;
		try {
			FileInputStream file = new FileInputStream("personne.serial");
			System.out.println(file);
			ObjectInputStream ois= new ObjectInputStream(file);
			System.out.println(ois);
			gameloaded = (Game) ois.readObject(); 
			ois.close();
			System.out.println(gameloaded);
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println("catch");
		}
		
		return gameloaded;
	}
	
	public static void loadRunning(Window window) throws IOException {
		Game game = load();
		
		if (game!=null) {
			//Window window = new Window();
			CONSTANTS.update(game.getSavedBlockSize(),game.getSavedDarkness(),game.getSavedLineOfSight());
			game.gameInit(window);
			
			mouse.updateGame(game);
			keyboard.updateGame(game);
			/*
			Keyboard keyboard = new Keyboard(game);
			Mouse mouse = new Mouse(game);
			window.setKeyListener(keyboard);
			window.setMouseListener(mouse);
			*/
			game.addRedrawObserver(window);
			
			
			game.gameStart(true);
		}
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
