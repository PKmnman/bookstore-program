package com.bookstore.inv;

/*************************************************************************************
 *  Program:     InventoryItem.java
 *  Date:        February 07, 2019
 *  Purpose:     $PROJECT_PURPOSE
 *  Author:      Gary Reeves
 *  Course:      Introduction to Computing I
 *  Assignment:  Bookstore Program
 *************************************************************************************/
public interface InventoryItem {
	
	Object copyObject();
	
	int compareTo(Object object);
	
	int quantity();
	
}
