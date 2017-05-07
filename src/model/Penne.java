package model;

import java.io.File;

public class Penne extends Item {
	private static final long serialVersionUID = 42L;
	
	private static final File spriteFilePCPU = new File(GameObject.class.getResource("/resources/sprites/P_CP_U.png").getFile());
	private static final File spriteFilePCPR = new File(GameObject.class.getResource("/resources/sprites/P_CP_R.png").getFile());
	private static final File spriteFilePCPD = new File(GameObject.class.getResource("/resources/sprites/P_CP_D.png").getFile());
	private static final File spriteFilePCPL = new File(GameObject.class.getResource("/resources/sprites/P_CP_L.png").getFile());
	
	private static final File spriteFilePCSU = new File(GameObject.class.getResource("/resources/sprites/P_CS_U.png").getFile());
	private static final File spriteFilePCSR = new File(GameObject.class.getResource("/resources/sprites/P_CS_R.png").getFile());
	private static final File spriteFilePCSD = new File(GameObject.class.getResource("/resources/sprites/P_CS_D.png").getFile());
	private static final File spriteFilePCSL = new File(GameObject.class.getResource("/resources/sprites/P_CS_L.png").getFile());
	
	private static final File spriteFilePCMU = new File(GameObject.class.getResource("/resources/sprites/P_CM_U.png").getFile());
	private static final File spriteFilePCMR = new File(GameObject.class.getResource("/resources/sprites/P_CM_R.png").getFile());
	private static final File spriteFilePCMD = new File(GameObject.class.getResource("/resources/sprites/P_CM_D.png").getFile());
	private static final File spriteFilePCML = new File(GameObject.class.getResource("/resources/sprites/P_CM_L.png").getFile());
	
	private static File spriteFilePU = spriteFilePCPU;
	private static File spriteFilePR = spriteFilePCPR;
	private static File spriteFilePD = spriteFilePCPD;
	private static File spriteFilePL = spriteFilePCPL;

	public Penne(int x, int y, Game game, File spriteFile) {
		super(Sprite.imageFromFile(spriteFile), x, y, game, Sprite.makeSpriteList(spriteFile, 0, 0, 0));
	}

	@Override
	public void pickUp(Inventory inventory) {
		this.getGame().getGameObjects().remove(this);
		this.getGame().getPlayer().putPenne();
	}
	
	public static void initSprites(int cercleNumber) {
		switch (cercleNumber) {
		case 0:
			break;
		case 1:
			spriteFilePU = spriteFilePCMU;
			spriteFilePR = spriteFilePCMR;
			spriteFilePD = spriteFilePCMD;
			spriteFilePL = spriteFilePCML;
			break;
		case 2:
			spriteFilePU = spriteFilePCSU;
			spriteFilePR = spriteFilePCSR;
			spriteFilePD = spriteFilePCSD;
			spriteFilePL = spriteFilePCSL;
			break;
		}
	}
	
	public static File getFileUp() {
		return spriteFilePU;
	}
	
	public static File getFileRight() {
		return spriteFilePR;
	}
	
	public static File getFileDown() {
		return spriteFilePD;
	}
	
	public static File getFileLeft() {
		return spriteFilePL;
	}
	
	@Override
	public void drop(int x, int y) {
		
	}

}
