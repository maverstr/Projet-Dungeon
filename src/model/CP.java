package model;

import java.io.File;
import java.util.ArrayList;

public class CP extends Player {
	
	private static final File spriteFileCPU = new File(GameObject.class.getResource("/resources/sprites/CP_U.png").getFile());
	private static final File spriteFileCPR = new File(GameObject.class.getResource("/resources/sprites/CP_R.png").getFile());
	private static final File spriteFileCPD = new File(GameObject.class.getResource("/resources/sprites/CP_D.png").getFile());
	private static final File spriteFileCPL = new File(GameObject.class.getResource("/resources/sprites/CP_L.png").getFile());
	
	private static final File spriteFilePCPU = new File(GameObject.class.getResource("/resources/sprites/P_CP_U.png").getFile());
	private static final File spriteFilePCPR = new File(GameObject.class.getResource("/resources/sprites/P_CP_R.png").getFile());
	private static final File spriteFilePCPD = new File(GameObject.class.getResource("/resources/sprites/P_CP_D.png").getFile());
	private static final File spriteFilePCPL = new File(GameObject.class.getResource("/resources/sprites/P_CP_L.png").getFile());
	
	private static final int maxHealthCP = 50;
	private static final int maxManaCP = 10;
	private static final int luckCP = 1;
	
	@SuppressWarnings("serial")
	private static final ArrayList<File> fileList = new ArrayList<File>() {{
	    add(spriteFileCPU);
	    add(spriteFileCPR);
	    add(spriteFileCPD);
	    add(spriteFileCPL);
	    add(spriteFilePCPU);
	    add(spriteFilePCPR);
	    add(spriteFilePCPD);
	    add(spriteFilePCPL);
	}};

	public CP(int x, int y, Game game) {
		super(x, y, game, Sprite.makeSpriteList(spriteFileCPU,0,-0.25,1),fileList,maxHealthCP,maxManaCP,luckCP);
		
	}

	@Override
	public void setInventory(Inventory inventory) {
		//Add weapons and items to the Player at the beginning of the Game.
		try {
			inventory.addWeapon(new Sword());
			inventory.addWeapon(new Sword()); // adds a second sword -> prints an error message cause there cant be two same weapons
			inventory.addWeapon(new Pickaxe());
		}catch (Exception e){}
		
		inventory.setWeaponIndex(0); //Select The Sword as the beginning weapon at start.
	}
	
	@Override
	public void specialAbility() {
		
	}

}
