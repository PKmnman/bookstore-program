package com.bookstore.common;

import com.bookstore.inv.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


/*************************************************************************************
 *  Program:     Book.java
 *  Date:        January 30, 2019
 *  Purpose:     $PROJECT_PURPOSE
 *  Author:      Gary Reeves
 *  Course:      Introduction to Computing I
 *  Assignment:  Bookstore Program
 *************************************************************************************/
public class Book extends Product implements Serializable {
	
	private String title, author, publisher;
	private int numOfPages;
	private ISBN iSBN;
	
	public Book(String title, String author, int numOfPages, String iSBN) {
		this.title = title;
		this.author = author;
		setNumOfPages(numOfPages);
		setiSBN(iSBN);
	}
	
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
		setNumOfPages(0);
		setiSBN(null);
	}
	
	//Used for ISBN searches
	public Book(String isbn) {
		this.title = "";
		this.author = "";
		setNumOfPages(0);
		setiSBN(isbn);
		
	}
	
	@Override
	public Object copyObject() {
		return new Book(title, author, numOfPages, iSBN.toString());
	}
	
	@Override
	public int compareTo(Object object) {
		if (!(object instanceof Book)) { //Check if object is Book
			return 1;
		}
		Book ref = (Book) object; //Cast object to Book
		if (this.iSBN.equals(ref.getiSBN())) { //Check if ISBN match
			return 0;
		}
		return 1;
	}
	
	//Setters & Getters
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public int getNumOfPages() {
		return numOfPages;
	}
	
	public void setNumOfPages(int numOfPages) {
		this.numOfPages = numOfPages;
	}
	
	public ISBN getiSBN() {
		return iSBN;
	}
	
	public void setiSBN(String iSBN) {
		this.iSBN = new ISBN(iSBN);
	}
	
	@Override
	public String toString() {
		return this.title + " (" + iSBN.toString() + ")";
	}
	
	private class ISBN {
		
		private int length;
		
		byte[] digits, prefix, group, publisher, title;
		byte check;
		
		public ISBN() {
			initDigits("0-0000-0000-0");
		}
		
		public ISBN(String iSBN) {
			initDigits(iSBN);
			
			if (!checkISBN(digits)) {
				throw new IllegalArgumentException("Invalid ISBN");
			}
		}
		
		//Initializes byte arrays containing each field of the ISBN
		private void initDigits(String iSBN) {
			
			ArrayList<byte[]> parts = new ArrayList<>();
			this.digits = new byte[13];
			this.check = Byte.valueOf(iSBN.charAt(iSBN.length() - 1) + "");
			
			int pos = 0, last = 0;
			for (int i = 0; i < iSBN.length(); i++) {
				if (iSBN.charAt(i) != '-') {
					digits[pos] = Byte.valueOf(iSBN.charAt(i) + "");
					pos++;
				} else {
					parts.add(Arrays.copyOfRange(digits, last, pos));
					last = pos;
				}
			}
			this.digits[pos - 1] = this.check;
			this.length = pos;
			
			//Initializes the byte arrays
			if (length == 10) {
				this.prefix = null;
				this.group = parts.get(0);
				this.publisher = parts.get(1);
				this.title = parts.get(2);
			} else {
				this.prefix = parts.get(0);
				this.group = parts.get(1);
				this.publisher = parts.get(2);
				this.title = parts.get(3);
			}
		}
		
		//Checks that the ISBN is valid
		public boolean checkISBN(final byte[] digits) {
			int t = 0, s = 0;
			if (length == 10) {
				for (int i = 0; i < 10; i++) {
					t += digits[i];
					s += t;
				}
				
				return (s % 11) == 0;
			} else if (length == 13) {
				for (int i = 0; i < 12; i += 2) {
					t += digits[i];
				}
				for (int i = 1; i < 12; i += 2) {
					t += digits[i] * 3;
				}
				int sum = t % 10;
				s = 10 - sum;
				return s == this.check;
			} else {
				return false;
			}
		}
		
		public int getLength() {
			return this.length;
		}
		
		//Converts ISBN to a long value
		public long toLong() {
			StringBuffer val = new StringBuffer();
			for (int i = 0; i < length; i++) {
				//Appends each digit to the StringBuffer
				val.append(digits[i]);
			}
			//Returns long value
			return Long.valueOf(val.toString());
		}
		
		@Override
		public String toString() {
			StringBuilder isbn = new StringBuilder();
			if (prefix != null) {
				ArrayList<byte[]> parts = new ArrayList<>();
				parts.add(prefix);
				parts.add(group);
				parts.add(publisher);
				parts.add(title);
				for (byte[] arr : parts) {
					for (int i = 0; i < arr.length; i++) {
						isbn.append(arr[i]);
					}
					isbn.append("-");
				}
				isbn.append(check);
			} else {
				ArrayList<byte[]> parts = new ArrayList<>();
				parts.add(group);
				parts.add(publisher);
				parts.add(title);
				for (byte[] arr : parts) {
					for (byte val : arr) {
						isbn.append(val + "");
					}
					isbn.append("-");
				}
				isbn.append(check);
			}
			
			return isbn.toString();
		}
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof ISBN)) {
				return false;
			}
			
			ISBN other = (ISBN) obj;
			
			if (other.getLength() != this.length) {
				return false;
			}
			
			for (int i = 0; i < length; i++) {
				if (other.digits[i] != this.digits[i]) {
					return false;
				}
			}
			
			return true;
		}
	
	}
	
}
