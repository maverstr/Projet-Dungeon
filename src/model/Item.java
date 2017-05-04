package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;


//public abstract class Item extends GameObject {

public abstract class Item extends GameObject implements IObstacle, IPickable, IPassive{
	private static final long serialVersionUID = 42L;
	private transient BufferedImage inventoryImage;

	private int durability;
	
	public Item(BufferedImage inventoryImage, int x, int y, Game game, ArrayList<Sprite> spriteList) {
		super(x,y,game,spriteList);
		this.inventoryImage=inventoryImage;
		this.durability=1;
	}
	
	public abstract void pickUp(Inventory inventory);
	
	public BufferedImage getInventoryImage() {
		return this.inventoryImage;
	}
	
	public void setDurability(int d) 
	{
		this.durability = d;
	}
	
	public void used(){ //When an item is used, the item counter or the durability is reduced
		this.durability-=1;
	}
	
	public int getDurability() {
		return this.durability;
	}
	
	@Override 
	public boolean isObstacle() {
		return false;
	}
	
	@Override
	public boolean isPickable() {
		return true;
	}
	
	@Override
	public boolean isPassive(){
		return false;
	}

	public void use() {
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ImageIO.write(inventoryImage, "png", out); // png is lossless
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        inventoryImage = ImageIO.read(in);
    }
	

}
