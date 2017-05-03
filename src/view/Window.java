package view;
import model.Boss;
import model.GameObject;
import model.Player;
import model.RedrawObservable;
import model.RedrawObserver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window implements RedrawObserver {
	private Player player;
	private boolean bossBool = false;
	private Boss boss;

	
	private Menu menu = new Menu();
	private Map map = new Map();
	private PlayerState playerState = new PlayerState();
	private ClassMenu classMenu = new ClassMenu();
	private GameOverScreen goScreen = new GameOverScreen();
	private StoryMenu storyScreen = new StoryMenu();
	
    JPanel mapContainer = new JPanel();
	JPanel menuContainer = new JPanel();
    JPanel playerstatetContainer = new JPanel();
    JPanel classContainer = new JPanel();
    JPanel goContainer = new JPanel();
    JPanel storyContainer = new JPanel();
    
    
	
	public Window() throws IOException{	  

		
	    JFrame frame = new JFrame("HaelterMINE");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setBounds(0, 0, CONSTANTS.CONSTANTS.getWINDOW_PIXEL_WIDTH(), CONSTANTS.CONSTANTS.getWINDOW_PIXEL_HEIGHT());
	    frame.setIconImage(ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/Boss_U.png").getFile())));
	    frame.getContentPane().setBackground(Color.gray);
	     
///////
	    
	    //left side of top level container
	    mapContainer.setLayout(new BoxLayout(mapContainer, BoxLayout.X_AXIS));
	    mapContainer.add(this.map);
	    mapContainer.add(Box.createHorizontalStrut(5)); //Small separator between game map and inventory
	    mapContainer.setVisible(false);
	    
///////
	    
	    //Menu/pause case
		menuContainer.setLayout(new BoxLayout(menuContainer, BoxLayout.Y_AXIS));
		menuContainer.add(this.menu);
	    menuContainer.setVisible(false);
	    
	///////
	    
		//Class choosing case
	    classContainer.setLayout(new BoxLayout(classContainer, BoxLayout.X_AXIS));
		classContainer.add(this.classMenu);
		classContainer.setVisible(false);
	    
	    
//	    //right side of top level container
	    playerstatetContainer.setLayout(new BoxLayout(playerstatetContainer, BoxLayout.Y_AXIS));
	    playerstatetContainer.add(this.playerState);
	    playerstatetContainer.setVisible(false);
	    
	   
//
	    //GameOver Container
	    goContainer.setLayout(new BoxLayout(goContainer, BoxLayout.Y_AXIS));
		goContainer.add(this.goScreen);
		goContainer.setVisible(false);
		
		
//
	    //Story + Controls Container
	    storyContainer.setLayout(new BoxLayout(storyContainer, BoxLayout.Y_AXIS));
		storyContainer.add(this.storyScreen);
		storyContainer.setVisible(false);
	    
	    
    
//	    //Top level container 
	    JPanel container = new JPanel();
	    container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
	    container.add(mapContainer);
	    container.add(menuContainer);
	    container.add(classContainer);
	    container.add(goContainer);
	    container.add(storyContainer);
	    container.add(playerstatetContainer, BorderLayout.CENTER);
	    
	    //Add the top level container to the frame
	    frame.getContentPane().add(container);
	    frame.setVisible(true);
	    
	    
	}
	
	public void setGameObjects(ArrayList<GameObject> objects){ //Update the GameObjects list
		this.map.setObjects(objects);
	}
	
	public void setPlayer(Player p) { //Game gives to window the reference to the player for udpdating healthbar...
		this.player=p;
	}
	
	public void setBoss(Boss b) { 
		this.boss=b;
		this.bossBool = true;
	}

	
	public void setKeyListener(KeyListener keyboard){
	    this.map.addKeyListener(keyboard);
	    this.menu.addKeyListener(keyboard);
	    this.classMenu.addKeyListener(keyboard);
	    this.storyScreen.addKeyListener(keyboard);

	}
	
	public void setMouseListener(MouseListener mouse){
		this.menu.addMouseListener(mouse);
	    this.map.addMouseListener(mouse);
	    this.menu.addMouseMotionListener((MouseMotionListener) mouse);
	    this.classMenu.addMouseListener(mouse);
	    this.classMenu.addMouseMotionListener((MouseMotionListener) mouse);
	    this.storyScreen.addMouseListener(mouse);
	    this.goScreen.addMouseListener(mouse);


	}
	
	public Menu getMenu(){
		return this.menu;
	}

	@Override
	public void redraw(RedrawObservable obj) {
		playerstatetContainer.setVisible(true);
		mapContainer.setVisible(true);
		menuContainer.setVisible(false);
		classContainer.setVisible(false);
		storyContainer.setVisible(false);
		goContainer.setVisible(false);
		this.map.redraw(this.player);
		this.playerState.redraw(this.player, this.boss, this.bossBool);
		this.map.requestFocusInWindow();
		
	}
	
	public void redrawMenu(){

		playerstatetContainer.setVisible(false);
		mapContainer.setVisible(false);
		classContainer.setVisible(false);
		storyContainer.setVisible(false);
		goContainer.setVisible(false);
		menuContainer.setVisible(true);
		this.menu.redraw();
		this.menu.requestFocusInWindow();
	}
	
	public void redrawClass(){

		playerstatetContainer.setVisible(false);
		mapContainer.setVisible(false);
		menuContainer.setVisible(false);
		goContainer.setVisible(false);
		storyContainer.setVisible(false);
		classContainer.setVisible(true);
		this.classMenu.redraw();
		this.classMenu.requestFocusInWindow();
	}
	
	public void redrawGameOver(){
		playerstatetContainer.setVisible(false);
		mapContainer.setVisible(false);
		menuContainer.setVisible(false);
		classContainer.setVisible(false);
		storyContainer.setVisible(false);
		goContainer.setVisible(true);
		this.goScreen.redraw();
		this.goScreen.requestFocusInWindow();
	}
	
	public void redrawStory(){
		playerstatetContainer.setVisible(false);
		mapContainer.setVisible(false);
		menuContainer.setVisible(false);
		classContainer.setVisible(false);
		goContainer.setVisible(false);
		storyContainer.setVisible(true);
		this.storyScreen.redraw();
		this.storyScreen.requestFocusInWindow();
	}
}
