package model;

import java.util.ArrayList;
import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Random;

import javax.imageio.ImageIO;

import CONSTANTS.CONSTANTS;

import java.io.IOException;

public class Boss extends Mob {
	
	private static final int initWaitTime = 2000;
	private static final int soundWaitTime = 10000;
	private int soundTime = 0;
	private boolean punchline1Bool = true;
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
	
	
	private static final File file1 = new File(GameObject.class.getResource("/resources/audio/Rotationnel2.m4a").getFile());
	private static final File file2 = new File(GameObject.class.getResource("/resources/audio/Produit_Scalaire.m4a").getFile());
	private static final File file3 = new File(GameObject.class.getResource("/resources/audio/En_Septembre.m4a").getFile());
	
	private static final Media punchline1Media = new Media(file1.toURI().toString());
	private static final Media punchline2Media = new Media(file2.toURI().toString());
	private static final Media punchline3Media = new Media(file3.toURI().toString());
	
	private static final MediaPlayer punchline1Player = new MediaPlayer(punchline1Media);
	private static final MediaPlayer punchline2Player = new MediaPlayer(punchline2Media);
	private static final MediaPlayer punchline3Player = new MediaPlayer(punchline3Media);
	

	public Boss(int x, int y, Game game) throws IOException {
		super(x, y, game, ImageIO.read(spriteFileU),maxHealth);
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			Player player = this.getGame().getPlayer();
			Random random = new Random();
			while(player.isAlive()){
				movePattern(player,random);
				updateSpriteDirection(spriteFileU,spriteFileR,spriteFileD,spriteFileL);
				this.getGame().updateWindow();
				Thread.sleep(waitTime/2);
				attackPattern();
				this.getGame().updateWindow();
				Thread.sleep(waitTime/2);
				setAudio();
			}
			punchline3Player.play();
			
		}catch(Exception e){}; 
	}
	
	public void movePattern(Player player,Random random) {
		int mobX = this.getPosX();
		int mobY = this.getPosY();
		int playerX = player.getPosX();
		int playerY = player.getPosY();
		
		int moveCloserX = moveCloser(mobX,playerX);
		if (obstacle(mobX,mobY,moveCloserX,0)) {
			moveCloserX = 0;
		}
		int moveCloserY = moveCloser(mobY,playerY);
		if (obstacle(mobX,mobY,0,moveCloserY)) {
			moveCloserY = 0;
		}
		
		if ((moveCloserX!=0) && (moveCloserY!=0)) {
			int randomInt = random.nextInt(2); // 0 or 1
			if (randomInt == 0) {
				move(moveCloserX,0);
			} else {
				move(0,moveCloserY);
			}
		} else {
			move(moveCloserX,moveCloserY);
		}
	}
	
	private boolean obstacle(int mobPosX, int mobPosY, int xMove, int yMove) {
		boolean res = false;
		int newPosX = mobPosX+CONSTANTS.BLOCK_SIZE*xMove;
		int newPosY = mobPosY+CONSTANTS.BLOCK_SIZE*yMove;
		for (GameObject object:this.getGame().getGameObjects()) {
			if (object.getPosX() == newPosX && object.getPosY() == newPosY) {
				if (object.isObstacle()) {
					//System.out.println("obstacle for skeleton");
					res = true;
				}
			}
		}
		return res;
	}
	
	@Override 
	public void wasHit() {
		super.wasHit();
		System.out.println("HAELTI LIFE : "+this.health);
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
				objects.add(new Laser(this.posX,this.posY+i*CONSTANTS.BLOCK_SIZE,this.getGame(),fileD(i,phase+2),phase));
				objects.add(new Laser(this.posX,this.posY-i*CONSTANTS.BLOCK_SIZE,this.getGame(),fileU(i,phase+2),phase));
				objects.add(new Laser(this.posX+i*CONSTANTS.BLOCK_SIZE,this.posY,this.getGame(),fileR(i,phase+2),phase));
				objects.add(new Laser(this.posX-i*CONSTANTS.BLOCK_SIZE,this.posY,this.getGame(),fileL(i,phase+2),phase));
			} catch(Exception e) {}
		}
	}
	
	private int moveCloser(int mobPos,int playerPos) {
		int res = 0;
		if (mobPos<playerPos) {
			res = 1;
		}
		if (mobPos>playerPos) {
			res = -1;
		}
		return res;
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
	
	public void setAudio() {
		soundTime+=waitTime;
		if (soundTime > soundWaitTime) {
			if (punchline1Bool) {
				punchline1Player.play();
			} else {
				punchline2Player.play();
			}
			punchline1Bool = !punchline1Bool;
			soundTime = 0;
		}
		
	}
	
	
	
	
	
}
