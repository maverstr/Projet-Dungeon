package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.io.File;

import java.io.Serializable;

public class Sprite implements Serializable {

	private static final long serialVersionUID = 42L;
	private transient BufferedImage image;
	private double offsetX;
	private double offsetY;
	private double drawX;
	private double drawY;
	private int drawZ; //0 to 5, background to foreground
	
	
	public Sprite(BufferedImage image,double offsetX,double offsetY, int drawZ) {
		this.image = image;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.drawZ = drawZ;
	}
	
	public void setOffset(double x, double y) {
		this.offsetX = x;
		this.offsetY = y;
	}
	
	public void setDrawPosition(int objectX,int objectY) {
		this.drawX = objectX+offsetX;
		this.drawY = objectY+offsetY;
	}
	
	public double getDrawX() {
		return drawX;
	}
	
	public double getDrawY() {
		return drawY;
	}
	
	public int getDrawZ() {
		return drawZ;
	}
	
	public void setImageFromFile(File file) throws IOException {
		BufferedImage image = ImageIO.read(file);
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public static BufferedImage imageFromFile(File file) {
		try {
			BufferedImage image = ImageIO.read(file);
			return image;
		} catch (IOException e) {
			return null;
		}
	}
	
	public static Sprite makeSpriteFromFile(File file, double x, double y, int z) {
		try {
			BufferedImage image = ImageIO.read(file);
			Sprite sprite = new Sprite(image,x,y,z);
			return sprite;
		} catch (IOException e) {
			return null;
		}
	}
	
	public static ArrayList<Sprite> makeSpriteList(File file, double x, double y, int z) {
		ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
		try {
			BufferedImage image = ImageIO.read(file);
			spriteList.add(new Sprite(image,x,y,z));
		} catch (IOException e) {}
		return spriteList;
	}
	
	// NOTE HERE : Overcharge of "makeSpriteList" depending of if a 3D model (with double sprite list and offset) is required or not
	public static ArrayList<Sprite> makeSpriteList(File file1, double x1, double y1, int z1, File file2, double x2, double y2, int z2) {
		ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
		try {
			BufferedImage image1 = ImageIO.read(file1);
			BufferedImage image2 = ImageIO.read(file2);
			spriteList.add(new Sprite(image1,x1,y1,z1));
			spriteList.add(new Sprite(image2,x2,y2,z2));
		} catch (IOException e) {}
		
		return spriteList;
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException { //Redefining wirte and ReadObject in order to serialize BufferedImage
        out.defaultWriteObject();
        ImageIO.write(image, "png", out); // png is lossless
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        image = ImageIO.read(in);
    }
	

}
