package model;

import java.io.File;
import java.util.ArrayList;

public class CM extends Player {
	
	private static final long serialVersionUID = 42L;
	
	private static final File spriteFileCMU = new File(GameObject.class.getResource("/resources/sprites/CM_U.png").getFile());
	private static final File spriteFileCMR = new File(GameObject.class.getResource("/resources/sprites/CM_R.png").getFile());
	private static final File spriteFileCMD = new File(GameObject.class.getResource("/resources/sprites/CM_D.png").getFile());
	private static final File spriteFileCML = new File(GameObject.class.getResource("/resources/sprites/CM_L.png").getFile());
	
	private static final int maxHealthCM = 30;
	private static final int maxManaCM = 20;
	private static final int luckCM = 1;
	
	private static final ArrayList<File> fileList = new ArrayList<File>() {{
	    add(spriteFileCMU);
	    add(spriteFileCMR);
	    add(spriteFileCMD);
	    add(spriteFileCML);
	}};

	public CM(int x, int y, Game game) {
		super(x, y, game, Sprite.makeSpriteList(spriteFileCMU,0,-0.25,5),fileList,maxHealthCM,maxManaCM,luckCM);
		
	}
	
	@Override
	public void setInventory(Inventory inventory) {
		//Add weapons and items to the Player at the beginning of the Game.
		try {
			inventory.addWeapon(new Sword(0,0,this.getGame()));
			inventory.addWeapon(new Scepter(0,0,this.getGame()));
			inventory.addConsumable(new PotionVie(0, 0, this.getGame())); //Note the type of potion
			inventory.addConsumable(new PotionVie(0, 0, this.getGame()));
			inventory.addConsumable(new PotionMana(0, 0, this.getGame()));
			inventory.addSpell(new Fire(0,0,game,true));
			inventory.addSpell(new Thunder(0,0,game,true));
			inventory.addSpell(new Ice(0,0,game,true));
			inventory.addSpell(new Heal(0,0,game,true));
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		inventory.setWeaponIndex(0); //Select The Sword as the beginning weapon at start.
	}
}
