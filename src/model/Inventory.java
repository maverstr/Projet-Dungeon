package model;

import java.util.ArrayList;

public class Inventory {
	public ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	public ArrayList<Consumable> consumables = new ArrayList<Consumable>();
	private int currentWeaponIndex = 0;

	public Inventory() {

	}

	public void addConsumable(Consumable item) { // Add a consumable object with
													// durability or quantity
													// count if duplicates
		boolean found = false;
		for (Consumable i : this.consumables) {
			if (i.getType().equals(item.getType())) {
				i.setDurability(i.getDurability() + 1);
				found = true;
				break;
			}
			// }
		}

		if (!found) {
			consumables.add(item);
		}

	}

	public void addWeapon(Weapon item) { // Add a weapon object. No more than
											// one weapon of the same type. No
											// durability
		boolean found = false;
		for (Weapon i : this.weapons) {
			if (i.getClass() == item.getClass()) {
				System.out.println("weapon already in weapon list");
				found = true;
				break;
			}
		}

		if (!found) {
			weapons.add(item);
		}

	}

	public Weapon getWeapon() { // return the Weapon to check the instanceof (cf
								// Player hit)
		return this.weapons.get(currentWeaponIndex);
	}

	public void setWeaponIndex(int index) {// Set the weapon index (cf Player
											// changeTool)
		this.currentWeaponIndex = index;
	}

	public int getWeaponIndex() { // Returns weapon index for checking CURRENT
									// Weapon (cf PlayerState paintComponent)
		return this.currentWeaponIndex;
	}
	
	public int getItemCount() {
		return weapons.size()+consumables.size();
	}

	// public Item takeConsumable(int itemIndex) {

	// Item item;
	// item =items.remove(itemIndex); /*TODO: could work with same items grouped
	// together, thus before removing, first check for item count */
	// System.out.println(String.format("use item at index %d : %s", itemIndex,
	// item));
	// return item;
	// }
}
