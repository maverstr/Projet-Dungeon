package model;

import java.io.File;
import java.io.IOException;

public class Laser extends Mob {
	
	private int waitTime;
	private int liveTime;
	private int time = 0;

	public Laser(int x, int y, Game game, File file,int phase) throws IOException {
		super(x, y, game, Sprite.makeSpriteList(file,0,0,2),10000);
		this.liveTime = 500/phase;
		this.waitTime = 300/phase;
	}
	
	@Override
	public void run(){
		try{
			Player player = this.getGame().getPlayer();
			while(player.isAlive()){
				if (game.state == Game.STATE.RUN) {
					
					attackPattern();
					
					if (time>liveTime) {
						this.die();
						this.getGame().updateWindow();
					}
					Thread.sleep(waitTime);
					time+=waitTime;
				}
			}
		}catch(Exception e){}; 
	}
	
	
	@Override
	public boolean isObstacle() {
		return false;
	}
	
	@Override
	public void attackPattern() {
		attack(0,0);
	}

}
