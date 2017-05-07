package model;

import java.io.File;

import java.util.ArrayList;

public abstract class Character extends GameObject{

	private static final long serialVersionUID = 42L;

	protected int maxHealth;
	protected int health;
	protected boolean isBaptized;
	protected int penneSpriteIndex;

	public Character(int x, int y, Game game, ArrayList<Sprite> spriteList, int maxHealth, boolean isBaptized){
		super(x,y,game,spriteList);
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		if (isBaptized) {
			putPenne();
		}
	}

	public void move(Direction direction){
		this.setPosX(this.getPosX() + xForDirection(direction));
		this.setPosY(this.getPosY() + yForDirection(direction));
		setMoveDirection(direction);
	}

	public void setMoveDirection(Direction direction) {
		setDirection(direction);
	}

	public void wasHit(int damage) {
		this.health-=damage;
		if (this.health<=0) {
			this.health = 0;
			die();
		}
	}

	public void updateSpriteDirection(File up,File right,File down,File left) {
		Sprite sprite = getSpriteList().get(0);
		try {
			switch (this.getDirection()) {
			case North:sprite.setImageFromFile(up);
			break;
			case East:sprite.setImageFromFile(right);
			break;
			case South:sprite.setImageFromFile(down);
			break;
			case West:sprite.setImageFromFile(left);
			break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (this.isBaptized) {
			updatePenneDirection();
		}
	}

	public void updatePenneDirection() {
		try {
			Sprite sprite = getSpriteList().get(penneSpriteIndex);
			switch (this.getDirection()) {
			case North:sprite.setImageFromFile(Penne.getFileUp());
			break;
			case East:sprite.setImageFromFile(Penne.getFileRight());
			break;
			case South:sprite.setImageFromFile(Penne.getFileDown());
			break;
			case West:sprite.setImageFromFile(Penne.getFileLeft());
			break;
			default:
				break;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void putPenne() {
		if (!this.isBaptized) {
			this.isBaptized = true;
			this.getSpriteList().add(Sprite.makeSpriteFromFile(Penne.getFileRight(), 0, -0.45, 9));
			this.penneSpriteIndex = getSpriteList().size()-1;
			updatePenneDirection();
		}
	}

	public abstract void die();

	@Override
	public boolean isObstacle() {
		return true;
	}

	public int getHealth(){
		return this.health;
	}


}
