package model;

import java.io.File;
import java.util.ArrayList;

public class CM extends Player {
	
	private static final File spriteFileCMU = new File(GameObject.class.getResource("/resources/sprites/CM_U.png").getFile());
	private static final File spriteFileCMR = new File(GameObject.class.getResource("/resources/sprites/CM_R.png").getFile());
	private static final File spriteFileCMD = new File(GameObject.class.getResource("/resources/sprites/CM_D.png").getFile());
	private static final File spriteFileCML = new File(GameObject.class.getResource("/resources/sprites/CM_L.png").getFile());
	
	private static final File spriteFilePCMU = new File(GameObject.class.getResource("/resources/sprites/P_CM_U.png").getFile());
	private static final File spriteFilePCMR = new File(GameObject.class.getResource("/resources/sprites/P_CM_R.png").getFile());
	private static final File spriteFilePCMD = new File(GameObject.class.getResource("/resources/sprites/P_CM_D.png").getFile());
	private static final File spriteFilePCML = new File(GameObject.class.getResource("/resources/sprites/P_CM_L.png").getFile());
	
	private static final int maxHealthCM = 30;
	private static final int maxManaCM = 20;
	private static final int luckCM = 3;
	
	
	@SuppressWarnings("serial")
	private static final ArrayList<File> fileList = new ArrayList<File>() {{
	    add(spriteFileCMU);
	    add(spriteFileCMR);
	    add(spriteFileCMD);
	    add(spriteFileCML);
	    add(spriteFilePCMU);
	    add(spriteFilePCMR);
	    add(spriteFilePCMD);
	    add(spriteFilePCML);
	}};

	public CM(int x, int y, Game game) {
		super(x, y, game, Sprite.makeSpriteList(spriteFileCMU,0,-0.25,1),fileList,maxHealthCM,maxManaCM,luckCM);
		
	}
	
	@Override
	public void setInventory(Inventory inventory) {
		//Add weapons and items to the Player at the beginning of the Game.
		try {
			inventory.addWeapon(new Sword());
			inventory.addWeapon(new Sword()); // adds a second sword -> prints an error message cause there cant be two same weapons
			inventory.addConsumable(new Potion(Potion.potionType.vie)); //Note the type of potion
			inventory.addConsumable(new Potion(Potion.potionType.vie));
			inventory.addConsumable(new Potion(Potion.potionType.mana));
		}catch (Exception e){}
		
		inventory.setWeaponIndex(0); //Select The Sword as the beginning weapon at start.
	}
	
	@Override
	public void specialAbility() {
		//TODO heal()
	}

}
