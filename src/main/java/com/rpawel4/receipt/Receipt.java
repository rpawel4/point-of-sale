package com.rpawel4.receipt;

import java.util.LinkedList;
import java.util.List;

public class Receipt {

	// date, cashier, shop etc.

	private List<Item> items;

	public Receipt() {
		this.items = new LinkedList<>();
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void addItem(Item item) {
		this.items.add(item);
	}

	@Override
	public String toString() {
		return "Receipt [items=" + items + "]";
	}

}
