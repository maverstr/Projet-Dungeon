package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.io.File;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Boss extends Mob {
	
	private static final int initWaitTime = 1000;
	private int waitTime = initWaitTime;
	private static final int maxHealth = 40;
	private int phase = 1;
	
	private static final File spriteFileU = new File(GameObject.class.getResource("/resources/sprites/Boss_U.png").getFile());
	private static final File spriteFileR = new File(GameObject.class.getResource("/resources/sprites/Boss_R.png").getFile());
	private static final File spriteFileD = new File(GameObject.class.getResource("/resources/sprites/Boss_D.png").getFile());
	private static final File spriteFileL = new File(GameObject.class.getResource("/resources/sprites/Boss_L.png").getFile());
	
	private static final File spriteFile1D = new File(GameObject.class.getResource("/resources/sprites/Laser_1_D.png").getFile());
	private static final File spriteFile1L = new File(GameObject.class.getResource("/resources/sprites/Laser_1_L.png").getFile());
	private static final File spriteFile1R = new File(GameObject.class.getResource("/resources/sprites/Laser_1_R.png").getFile());
	private static final File spriteFile1U = new File(GameObject.class.getResource("/resources/sprites/Laser_1_U.png").getFile());
	private static final File spriteFile2D = new File(GameObject.class.getResource("/resources/sprites/Laser_2_D.png").getFile());
	private static final File spriteFile2L = new File(GameObject.class.getResource("/resources/sprites/Laser_2_L.png").getFile());
	private static final File spriteFile2R = new File(GameObject.class.getResource("/resources/sprites/Laser_2_R.png").getFile());
	private static final File spriteFile2U = new File(GameObject.class.getResource("/resources/sprites/Laser_2_U.png").getFile());
	private static final File spriteFile3D = new File(GameObject.class.getResource("/resources/sprites/Laser_3_D.png").getFile());
	private static final File spriteFile3L = new File(GameObject.class.getResource("/resources/sprites/Laser_3_L.png").getFile());
	private static final File spriteFile3R = new File(GameObject.class.getResource("/resources/sprites/Laser_3_R.png").getFile());
	private static final File spriteFile3U = new File(GameObject.class.getResource("/resources/sprites/Laser_3_U.png").getFile());

	public Boss(int x, int y, Game game, BufferedImage sprite) throws IOException {
		super(x, y, game, ImageIO.read(spriteFileU),maxHealth);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			Player player = this.getGame().getPlayer();
			while(player.isAlive()){
				int mobX = this.getPosX();
				int mobY = this.getPosY();
				int playerX = player.getPosX();
				int playerY = player.getPosY();
				
				
				
				this.getGame().updateWindow();
				Thread.sleep(waitTime/(2));
				
				attackPattern();
				Thread.sleep(waitTime/(2));
			}
		}catch(Exception e){}; 
	}
	
	@Override 
	public void wasHit() {
		super.wasHit();
		
		int newPhase = 0;
		if (this.health < 3*maxHealth/4) {
			newPhase = 2;
		}
		if (this.health < 2*maxHealth/4) {
			newPhase = 3;
		}
		if (this.health < 1*maxHealth/4) {
			newPhase = 4;
		}
		if (newPhase>phase) {
			phase = newPhase;
			waitTime = initWaitTime/phase;
		}
	}
	
	@Override
	public void attackPattern() {
		ArrayList<GameObject> objects = this.getGame().getGameObjects();
		for (int i = 1; i<phase+3; i++) {
			try {
				attack(0,i);
				objects.add(new Laser(this.posX,this.posY+i,this.getGame(),fileD(i,phase+3)));
				
				attack(0,-i);
				objects.add(new Laser(this.posX,this.posY+i,this.getGame(),fileU(i,phase+3)));
				
				attack(i,0);
				objects.add(new Laser(this.posX,this.posY+i,this.getGame(),fileR(i,phase+3)));
				
				attack(-i,0);
				objects.add(new Laser(this.posX,this.posY+i,this.getGame(),fileL(i,phase+3)));
			} catch(Exception e) {}
		}
	}
	
	public File fileU(int i, int maxI) {
		File file = spriteFile2U;
		if (i == 1) {
			file = spriteFile1U;
		}
		if (i == maxI) {
			file = spriteFile3U;
		}
		return file;
	}
	
	public File fileR(int i, int maxI) {
		File file = spriteFile2R;
		if (i == 1) {
			file = spriteFile1R;
		}
		if (i == maxI) {
			file = spriteFile3R;
		}
		return file;
	}
	
	public File fileD(int i, int maxI) {
		File file = spriteFile2D;
		if (i == 1) {
			file = spriteFile1D;
		}
		if (i == maxI) {
			file = spriteFile3D;
		}
		return file;
	}
	
	public File fileL(int i, int maxI) {
		File file = spriteFile2L;
		if (i == 1) {
			file = spriteFile1L;
		}
		if (i == maxI) {
			file = spriteFile3L;
		}
		return file;
	}
	
	
	
	
	
}
