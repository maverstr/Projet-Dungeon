package model;

import java.util.ArrayList;

public class Inventory {
	public ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	public ArrayList<Consumable> consumables = new ArrayList<Consumable>();
	private Item currentWeaponIndex;

	public Inventory()  {
		
	}
	
	public void addConsumable(Consumable item) {
		boolean found=false;
		for(Consumable i : this.consumables){
			if (i.getClass() == item.getClass()) {
				i.setDurability(i.getDurability()+1);
				found = true;
				break;
			} 
		}
		
		if (! found) {
			consumables.add(item);
		}
		
	}

	
	public void addWeapon(Weapon item) {
		boolean found=false;
		for(Weapon i : this.weapons){
			if (i.getClass() == item.getClass()) {
				System.out.println("weapon already is weapon list");
				found = true;
				break;
			} 
		}
		
		if (! found) {
			weapons.add(item);
		}
		
	}

	
//	public Item takeConsumable(int itemIndex) {
		
//		Item item;
//		item =items.remove(itemIndex); /*TODO: could work with same items grouped together, thus before removing, first check for item count */
//		System.out.println(String.format("use item at index %d : %s", itemIndex, item));
//		return item;
//	}
}
