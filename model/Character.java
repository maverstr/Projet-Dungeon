package model;

import view.Sprite;

public abstract class Character extends GameObject{
	int posX;
	int posY;
	int Direction = 1;
	int Health = 5;
	//Weapon weapon = new Weapon();
	Sprite sprite;
	
	public Character(int x, int y , Sprite sprite){
		super(x,y,sprite);
		
	}
	public int getHealth{
		return this.Health;
	}

}
