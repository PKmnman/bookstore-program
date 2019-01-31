/*************************************************************************************
 *  Program:     Test.java
 *  Date:        January 30, 2019
 *  Purpose:     $PROJECT_PURPOSE
 *  Author:      Gary Reeves
 *  Course:      Introduction to Computing I
 *  Assignment:  Bookstore Program
 *************************************************************************************/
public class Test {
	
	public static void main(String[] args) {
		Book b = new Book("How to Code 101", "SmartyPants McGee", "nonfiction", 9781603093224L);
		b.setPublisher("Fake Publisher .Inc");
		b.setPublishDate(3, 10, 2000);
		b.setNumOfPages(353);
		
		System.out.println(b);
	}
	
}
