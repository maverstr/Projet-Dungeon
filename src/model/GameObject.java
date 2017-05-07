package model;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class GameObject implements Serializable {
	
	private static final long serialVersionUID = 42L;
	private int posX;
	private int posY;
	private ArrayList<Sprite> spriteList;
	private Direction direction = Direction.North; //0=up,1=right,2=down,3=left
	protected Game game;
	
	public GameObject(int x, int y, Game game, ArrayList<Sprite> spriteList) {
		this.setPosX(x);
		this.setPosY(y);
		this.spriteList = spriteList;
		this.game = game;
	}
	
	public void setPos(int posX,int posY) {
		this.setPosX(posX);
		this.setPosY(posY);
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public int getPosX() {
		return this.posX;
	}
	
	public int getPosY() {
		return this.posY;
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public void setDirection(Direction d){
		this.direction = d;
	}
	
	public ArrayList<Sprite> getSpriteList() {
		return this.spriteList;
	}
	
	public boolean isAtPosition(int x, int y){
		return this.getPosX() == x && this.getPosY() == y;
	}
	
	public Game getGame() {
		return this.game;
	}
	
	public boolean isMoveable() {
		return false;
	}
	
	public boolean isPickable() {
		return false;
	}
	
	public boolean isOpenable() {
		return false;
	}
	
	public boolean isAttackable() {
		return false;
	}
	
	public abstract boolean isObstacle(); //abstract to not forget to define every block

	public boolean isExit() {
		return false;
	}
	
	public int xForDirection(Direction direction) {
		int res = 0;
		if (direction == Direction.West) {
			res = -1;
		}
		if (direction == Direction.East) {
			res = 1;
		}
		return res;
	}
	
	public int yForDirection(Direction direction) {
		int res = 0;
		if (direction == Direction.North) {
			res = -1;
		}
		if (direction == Direction.South) {
			res = 1;
		}
		return res;
	}
	
	public Direction directionFor(int xDirection, int yDirection) {
		Direction res = Direction.None;
		if (xDirection>0) {
			res = Direction.East;
		}
		if (xDirection<0) {
			res = Direction.West;
		}
		if (yDirection>0) {
			res = Direction.South;
		}
		if (yDirection<0) {
			res = Direction.North;
		}
		return res;
	}
	
	public Direction oppositeDirection(Direction direction) {
		Direction newDirection = Direction.None;
		switch (direction) {
		case North:
			newDirection = Direction.South;
			break;
		case South:
			newDirection = Direction.North;
			break;
		case West:
			newDirection = Direction.East;
			break;
		case East:
			newDirection = Direction.West;
			break;
		default:
			break;
		}
		return newDirection;
	}

	public boolean isPassive() {

		return false;
	}


	
	
	
}

