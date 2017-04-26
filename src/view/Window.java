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
	private Map map = new Map();
	private PlayerState playerState = new PlayerState();
	private Player player;
	private boolean bossBool = false;
	private Boss boss;

	
	private Menu menu = new Menu();
	
    JPanel leftContainer = new JPanel();
	JPanel menuContainer = new JPanel();
    JPanel rightContainer = new JPanel();
	
	
	
	public Window() throws IOException{	  

		
	    JFrame frame = new JFrame("HaelterMINE");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setBounds(0, 0, CONSTANTS.CONSTANTS.WINDOW_PIXEL_WIDTH, CONSTANTS.CONSTANTS.WINDOW_PIXEL_HEIGHT);
	    frame.setIconImage(ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/Boss_U.png").getFile())));
	    frame.getContentPane().setBackground(Color.gray);
	     
///////
	    
	    //left side of top level container
	    leftContainer.setLayout(new BoxLayout(leftContainer, BoxLayout.Y_AXIS));
	    leftContainer.add(this.map);
	    leftContainer.setVisible(false);
	    
///////
	    
	    //Menu/pause case
		menuContainer.setLayout(new BoxLayout(menuContainer, BoxLayout.Y_AXIS));
		menuContainer.add(this.menu);
	    menuContainer.setVisible(false);
	    
	    
	    
	    
//	    //right side of top level container
	    rightContainer.setLayout(new BoxLayout(rightContainer, BoxLayout.Y_AXIS));
	    rightContainer.add(this.playerState);
	    rightContainer.setVisible(false);
	    
	    
    
//	    //Top level container 
	    JPanel container = new JPanel();
	    container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
	    container.add(leftContainer);
	    container.add(menuContainer);
	    container.add(Box.createHorizontalStrut(10));
	    container.add(rightContainer, BorderLayout.CENTER);
	    
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

	}
	
	public void setMouseListener(MouseListener mouse){
		this.menu.addMouseListener(mouse);
	    this.map.addMouseListener(mouse);
	    this.menu.addMouseMotionListener((MouseMotionListener) mouse);

	}
	
	public Menu getMenu(){
		return this.menu;
	}

	@Override
	public void redraw(RedrawObservable obj) {
		rightContainer.setVisible(true);
		leftContainer.setVisible(true);
		menuContainer.setVisible(false);
		this.map.redraw(this.player);
		this.playerState.redraw(this.player, this.boss, this.bossBool);
		this.map.requestFocusInWindow();
		
	}
	
	public void redrawMenu(){

		rightContainer.setVisible(false);
		leftContainer.setVisible(false);
		menuContainer.setVisible(true);
		this.menu.redraw();
		this.menu.requestFocusInWindow();


	}
}
