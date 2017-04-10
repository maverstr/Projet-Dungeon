package model;

import java.util.ArrayList;

public class Inventory {
	public ArrayList<Item> items = new ArrayList<Item>();
	private Item current;

	public Inventory()  {
		
	}
	
	public void add(Item item) {
		items.add(item);
		System.out.println(String.format("adding %s to the inventory", item));
		
	}
	
	public Item use(int itemIndex) {
		Item item;
		item =items.remove(itemIndex); /*TODO: could work with same items grouped together, thus before removing, first check for item count */
		System.out.println(String.format("use item at index %d : %s", itemIndex, item));
		return item;
	}
}
