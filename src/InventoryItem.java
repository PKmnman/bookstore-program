import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/*************************************************************************************
 *  Program:     InventoryItem.java
 *  Date:        January 30, 2019
 *  Purpose:     $PROJECT_PURPOSE
 *  Author:      Gary Reeves
 *  Course:      Introduction to Computing I
 *  Assignment:  Bookstore Program
 *************************************************************************************/
public class InventoryItem implements Serializable {
	
	private long id, quantity;
	private double price;
	private Book item;
	
	public InventoryItem(int quantity, double price, Book item){
		this.quantity = quantity;
		this.price = price;
		this.item = item;
	}
	
	public InventoryItem(Book item){
		this.id = item.getiSBN();
		this.item = item;
	}
	
	public InventoryItem(){
		this.id = -1;
		this.quantity = 0;
		this.item = null;
		this.price = 0.0;
	}
	
	public void increaseStock(){
		this.quantity++;
	}
	
	public void increaseStock(int amt){
		this.quantity += amt;
	}
	
	public void decreaseStock(){
		this.quantity--;
	}
	
	public void decreaseStock(int amt){
		this.quantity -= amt;
	}
	
	public double getPrice() {
		NumberFormat money = NumberFormat.getCurrencyInstance(Locale.US);
		String value = money.format(this.price);
		return this.price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Book getItem() {
		return item;
	}
	
	public void setItem(Book item) {
		this.item = item;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
}
