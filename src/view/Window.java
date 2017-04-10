package view;
import model.GameObject;
import model.Player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Window {
	private Map map = new Map();
	private PlayerState playerState = new PlayerState();
	private Player player;
	
	
	
	public Window() throws IOException{	  
		
	    JFrame frame = new JFrame("HaelterMINE");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setBounds(0, 0, CONSTANTS.CONSTANTS.WINDOW_PIXEL_WIDTH, CONSTANTS.CONSTANTS.WINDOW_PIXEL_HEIGHT);
	    frame.setIconImage(ImageIO.read(new File(GameObject.class.getResource("/resources/sprites/Boss_U.png").getFile())));
	    frame.getContentPane().setBackground(Color.gray);
	     
///////
	    
	    //left side of top level container
	    JPanel leftContainer = new JPanel();
	    leftContainer.setLayout(new BoxLayout(leftContainer, BoxLayout.Y_AXIS));
	    leftContainer.add(this.map);
	    
	    
	    
//	    //right side of top level container
	    JPanel rightContainer = new JPanel();
	    rightContainer.setLayout(new BoxLayout(rightContainer, BoxLayout.Y_AXIS));
	    rightContainer.add(this.playerState);
	    
	    
    
//	    //Top level container 
	    JPanel container = new JPanel();
	    container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
	    container.add(leftContainer);
	    container.add(Box.createHorizontalStrut(10));
	    container.add(rightContainer, BorderLayout.CENTER);
	    
	    //Add the top level container to the frame
	    frame.getContentPane().add(container);
	    frame.setVisible(true);
	    
	    
	}
	
	public void setGameObjects(ArrayList<GameObject> objects){ //Update the GameObjects list and notify VIEW with update()
		this.map.setObjects(objects);
		this.update();
	}
	
	public void setPlayer(Player p) {
		this.player=p;
	}
	
	public void update(){ //Redraw the graphics
		this.map.redraw();
		this.playerState.redraw(this.player);
	}
	
	public void setKeyListener(KeyListener keyboard){
	    this.map.addKeyListener(keyboard);
	}
}
