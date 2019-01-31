import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.GregorianCalendar;

/*************************************************************************************
 *  Program:     Book.java
 *  Date:        January 30, 2019
 *  Purpose:     $PROJECT_PURPOSE
 *  Author:      Gary Reeves
 *  Course:      Introduction to Computing I
 *  Assignment:  Bookstore Program
 *************************************************************************************/
public class Book extends InventoryItem implements Serializable {
	
	private String title, author, publisher;
	private Genre genre;
	private int numOfPages;
	private long iSBN;
	private GregorianCalendar publishDate;
	
	public Book(String title, String author, String genre, long iSBN){
		this.title = title;
		this.author = author;
		this.genre = Genre.toGenre(genre);
		this.iSBN = iSBN;
		this.setId(this.iSBN);
		this.setItem(this);
	}
	
	@SuppressWarnings("MagicConstant")
	public void setPublishDate(int month, int day, int year){
		this.publishDate = new GregorianCalendar(year, month - 1, day);
	}
	
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
	
	public Genre getGenre() {
		return genre;
	}
	
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	public int getNumOfPages() {
		return numOfPages;
	}
	
	public void setNumOfPages(int numOfPages) {
		this.numOfPages = numOfPages;
	}
	
	public long getiSBN() {
		return iSBN;
	}
	
	public void setiSBN(long iSBN) {
		this.iSBN = iSBN;
	}
	
	public GregorianCalendar getPublishDate() {
		return publishDate;
	}
	
	public void setPublishDate(GregorianCalendar publishDate) {
		this.publishDate = publishDate;
	}
	
	@Override
	public String toString() {
		Field[] fields = this.getClass().getDeclaredFields();
		
		StringBuilder string = new StringBuilder();
		string.append(this.getClass().getSimpleName() + "\n");
		string.append("--------------------------------------\n");
		
		for (Field f:fields){
			try {
				String val = f.get(this).toString();
				if(f.getName().equalsIgnoreCase("publishdate")){
					String date = DateTimeFormatter.ISO_LOCAL_DATE.format(publishDate.toZonedDateTime());
					string.append(String.format("%-13s %-21s%n",f.getName() + ":", date));
				}else{
					string.append(String.format("%-13s %-21s%n",f.getName() + ":", val));
				}
			} catch (IllegalAccessException e) {
				string.append(this.getClass().getFields()[0].toString());
				e.printStackTrace();
			}
		}
		
		return string.toString();
	}
	
}
