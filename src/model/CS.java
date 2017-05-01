package model;

import java.io.File;
import java.util.ArrayList;

public class CS extends Player {
	
	private static final File spriteFileCSU = new File(GameObject.class.getResource("/resources/sprites/CS_U.png").getFile());
	private static final File spriteFileCSR = new File(GameObject.class.getResource("/resources/sprites/CS_R.png").getFile());
	private static final File spriteFileCSD = new File(GameObject.class.getResource("/resources/sprites/CS_D.png").getFile());
	private static final File spriteFileCSL = new File(GameObject.class.getResource("/resources/sprites/CS_L.png").getFile());
	
	private static final int maxHealthCS = 50;
	private static final int maxManaCS = 10;
	private static final int luckCS = 3;
	
	@SuppressWarnings("serial")
	private static final ArrayList<File> fileList = new ArrayList<File>() {{
	    add(spriteFileCSU);
	    add(spriteFileCSR);
	    add(spriteFileCSD);
	    add(spriteFileCSL);
	}};

	public CS(int x, int y, Game game) {
		super(x, y, game, Sprite.makeSpriteList(spriteFileCSU,0,-0.25,5),fileList,maxHealthCS,maxManaCS,luckCS);
		setLuck(3);
	}
	
	@Override
	public void setInventory(Inventory inventory) {
		//Add weapons and items to the Player at the beginning of the Game.
		try {
			inventory.addWeapon(new Sword(0,0,this.getGame()));
			inventory.addWeapon(new Scepter(0,0,this.getGame()));
			inventory.addConsumable(new PotionVie(0, 0, this.getGame())); //Note the type of potion
			inventory.addSpell(new Fire(0,0,game,true));
			inventory.addSpell(new Thunder(0,0,game,true));
			inventory.addSpell(new Ice(0,0,game,true));
			inventory.replacePassive(new Torch(0,0,game));
		}catch (Exception e){}
		
		inventory.setWeaponIndex(0); //Select The Sword as the beginning weapon at start.
	}
	
	@Override
	public void specialAbility() {
		
	}

}
