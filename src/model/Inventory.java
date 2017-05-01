package model;

import java.util.ArrayList;

public class Inventory {
	public ArrayList<Spell> spells = new ArrayList<Spell>();
	public ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	public ArrayList<Consumable> consumables = new ArrayList<Consumable>();
	public Item passiveItem;
	private int currentWeaponIndex = 0;
	private int currentSpellIndex = 0;

	public Inventory() {

	}

	public void addConsumable(Consumable item) { // Add a consumable object with durability or quantity count if duplicates
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
	public void useConsumable(Consumable item){
		if(item.getDurability()>1){
		item.used();	//Diminish durability of item 
		}
		else{
			this.consumables.remove(item); //Remove if last count of item
		}
	}
	
	public void addSpell(Spell spell) {
		spells.add(spell);
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

	public Weapon getWeapon() { 
		System.out.println(this.weapons);
		System.out.println(currentWeaponIndex);
		return this.weapons.get(currentWeaponIndex);
	}
	
	public Spell getSpell() {
		return this.spells.get(currentSpellIndex);
	}

	public void setWeaponIndex(int index) {// Set the weapon index 
		this.currentWeaponIndex = index;
	}
	
	public void updateSpellIndex() {
		this.currentSpellIndex++;
		if (this.currentSpellIndex>=spells.size()) {
			this.currentSpellIndex = 0;
		}
	}

	public int getWeaponIndex() { // Returns weapon index for checking CURRENT
									// Weapon (cf PlayerState paintComponent)
		return this.currentWeaponIndex;
	}
	
	public int getSpellIndex() {
		return this.currentSpellIndex;
	}
	
	public int getItemCount() {
		return weapons.size()+consumables.size();
	}
	
	public void replacePassive(Item item){
		this.passiveItem = item;
		item.use();
	}
	
	public Item getPassive(){
		return this.passiveItem;
	}


}
