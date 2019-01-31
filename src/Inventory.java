import java.io.Serializable;
import java.util.ArrayList;

/*************************************************************************************
 *  Program:     Inventory.java
 *  Date:        January 30, 2019
 *  Purpose:     $PROJECT_PURPOSE
 *  Author:      Gary Reeves
 *  Course:      Introduction to Computing I
 *  Assignment:  Bookstore Program
 *************************************************************************************/
public class Inventory implements Serializable{
	
	private ArrayList<InventoryItem> contents;
	
	public Inventory(int initialCapacity) {
		this.contents = new ArrayList<>(initialCapacity);
	}
	
	public Inventory() {
		this.contents = new ArrayList<>();
	}
	
	public void addItem(InventoryItem item){
		this.contents.add(item);
	}
	
	public InventoryItem getItem(int id){
		for(InventoryItem item:contents){
			if(item.getId() == id){
				return item;
			}
		}
		return null;
	}
}
