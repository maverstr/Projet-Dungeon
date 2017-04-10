package model;

import java.util.ArrayList;

public class Inventory {
	public ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	public ArrayList<Consumable> consumables = new ArrayList<Consumable>();
	private int currentWeaponIndex=0;

	public Inventory()  {
		
	}
	
	public void addConsumable(Consumable item) {
		boolean found=false;
		for(Consumable i : this.consumables){
			//if (i.getClass() == item.getClass()) {
				if (i.getType().equals(item.getType())) {
					i.setDurability(i.getDurability()+1);
					found = true;
					break;
				}
			//} 
		}
		
		if (! found) {
			consumables.add(item);
		}
		
	}

	
	public void addWeapon(Weapon item) {
		boolean found=false;
		for(Weapon i : this.weapons){
			if (i.getClass() == item.getClass()) {
				System.out.println("weapon already in weapon list");
				found = true;
				break;
			} 
		}
		
		if (! found) {
			weapons.add(item);
		}
		
	}
	
	public Weapon getWeapon() {
		return this.weapons.get(currentWeaponIndex);
	}
	
	public void setWeaponIndex(int index) {
		this.currentWeaponIndex=index;
	}
	
	public int getWeaponIndex() {
		return this.currentWeaponIndex;
	}


	
//	public Item takeConsumable(int itemIndex) {
		
//		Item item;
//		item =items.remove(itemIndex); /*TODO: could work with same items grouped together, thus before removing, first check for item count */
//		System.out.println(String.format("use item at index %d : %s", itemIndex, item));
//		return item;
//	}
}
