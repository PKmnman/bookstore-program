package com.bookstore.inv;

import java.io.Serializable;
import java.util.Iterator;

/*************************************************************************************
 *  Program:     Inventory.java
 *  Date:        January 30, 2019
 *  Purpose:     $PROJECT_PURPOSE
 *  Author:      Gary Reeves
 *  Course:      Introduction to Computing I
 *  Assignment:  Bookstore Program
 *************************************************************************************/
public class Inventory<E> implements Serializable{
	
	private static final long serialVersionUID = 101200120012626L;
	
	private E[] items;
	private int next;
	
	public Inventory() {
		this.items = (E[]) new Object[10];
	}
	
	public Inventory(int capacity) {
		this.items = (E[]) new Object[capacity];
		this.next = 0;
	}
	
	public Inventory(E...items){
		this.items = (E[]) new Object[items.length + 1];
		this.next = 0;
		add(items);
	}
	
	//Add all items passed to this method to this Inventory
	public boolean add(E...items){
		boolean isComplete = true;
		for(E item:items){
			if(!isComplete){
				return false;
			}
			isComplete = insert(item);
		}
		return isComplete;
	}
	
	/**
	 * Searches for and returns the given object from this inventory
	 *
	 * @return if found, a copy of the object being searched
	 */
	
	public E fetch(Object reference){
		InventoryItem item;
		int index = -1;
		do{
			index++;
			item = (InventoryItem) items[index];
		}while (item.compareTo(reference) == 1 && index < next);
		if(index == next){
			return null;
		}
		return (E)item.copyObject();
	}
	
	//Removes the item matching the reference from the structure
	public boolean delete(Object reference){
		int index = indexOf((E)reference); //Finds index to start at
		if(index == -1){
			return false;
		}
		int remains = index + 1;
		
		while(remains < next){ //Moves all the following elements up
			items[index] = items[remains];
			index++; remains++;
		}
		next--;
		return true;
	}
	
	//Creates an Iterator to iterate through this Inventory
	public Iterator<E> iterator(){
		return new InvIterator();
	}
	
	//Returns the index of the item that matches reference
	public int indexOf(E reference){
		InventoryItem item;
		int index = 0;
		do{
			item = (InventoryItem) items[index];
			index++;
		}while (item.compareTo(reference) == 1 && index < next);
		if(index == next){
			return -1;
		}
		
		return index;
	}
	
	//Returns an array containing the elements of the Inventory
	public E[] toArray(){
		E[] temp = (E[]) new Object[size()];
		System.arraycopy(items,0,temp,0,size());
		return temp;
	}
	
	public void expand(){
		E[] temp = (E[]) new Object[capacity() + 1];
		System.arraycopy(temp,0, items,0,capacity()-1);
		this.items = temp;
	}
	
	//Returns the number of elements in Inventory
	public int size(){
		return this.next;
	}
	
	//Returns the total capacity of the inventory, including null
	//references
	public int capacity(){
		return this.items.length;
	}
	
	public void showAll(){
		if(size() > 0){
			for (int i = 0; i < size(); i++){
				if(items[i] != null) {
					E temp = (E) ((InventoryItem) items[i]).copyObject();
					System.out.print(temp + " ");
				}
			}
		}
		System.out.println();
	}
	
	private boolean insert(E newItem){
		InventoryItem item = (InventoryItem) newItem;
		if(next > capacity()){expand();}
		items[next] = (E) item.copyObject(); //inserts duplicate into array
		if(items[next] == null){
			return false;
		}
		next++;
		return true;
	}
	
	protected class InvIterator implements Iterator<E>{
		
		private int cursor;
		
		protected InvIterator(){
			cursor = 0;
		}
		
		protected InvIterator(int pos){
			cursor = pos;
		}
		
		@Override
		public boolean hasNext() {
			return cursor > size();
		}
		
		@Override
		public E next() {
			InventoryItem item = (InventoryItem) items[cursor];
			cursor++;
			return (E) item.copyObject();
		}
		
	}
}
