package com.bookstore.inv;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/*************************************************************************************
 *  Program:     Product.java
 *  Date:        February 08, 2019
 *  Purpose:     $PROJECT_PURPOSE
 *  Author:      Gary Reeves
 *  Course:      Introduction to Computing I
 *  Assignment:  Bookstore Program
 *************************************************************************************/
public abstract class Product implements Serializable, InventoryItem{
	
	protected Calendar dateAdded;
	protected double wholesaleCost;
	protected double retailPrice;
	protected int quantity;
	
	public Product(){
		this.quantity = 0;
		this.wholesaleCost = 0.0;
		this.retailPrice = 0.0;
		
		setAdded(System.currentTimeMillis());
	}
	
	public Product(Date added){
		this.dateAdded = Calendar.getInstance();
		this.dateAdded.setTime(added);
		
		this.quantity = 0;
		this.wholesaleCost = 0.0;
		this.retailPrice = 0.0;
	}
	
	public Product(int quantity, double wholesaleCost, Calendar dateAdded){
		setQuantity(0);
		this.wholesaleCost = wholesaleCost;
		this.dateAdded = dateAdded;
	}
	
	public Product(int quantity, double wholesaleCost, long dateAdded){
		setQuantity(0);
		this.wholesaleCost = wholesaleCost;
		setAdded(dateAdded);
	}
	
	private void setAdded(long dateAdded) {
		this.dateAdded = Calendar.getInstance();
		this.dateAdded.setTimeInMillis(dateAdded);
	}
	
	private void setAdded(int year, int month, int day) {
		this.dateAdded = Calendar.getInstance();
		this.dateAdded.set(year-1,month-1,day);
	}
	
	public double getRetailPrice(){
		return this.retailPrice;
	}
	
	public void setRetailPrice(double price){
		this.retailPrice = price;
	}
	
	@Override
	public int quantity() {
		return this.quantity;
	}
	
	public void setQuantity(int num){
		this.quantity = num;
	}
	
	public void increaseStock(int amt){
		this.quantity = this.quantity + amt;
	}
	
	public void reduceStock(int amt){
		this.quantity = this.quantity - amt;
	}
	
}