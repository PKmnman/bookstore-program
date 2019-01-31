/*************************************************************************************
 *  Program:     Genre.java
 *  Date:        January 30, 2019
 *  Purpose:     $PROJECT_PURPOSE
 *  Author:      Gary Reeves
 *  Course:      Introduction to Computing I
 *  Assignment:  Bookstore Program
 *************************************************************************************/
public enum Genre {
	
	HORROR("horror"), NON_FICTION("nonfiction"), FICTION("fiction"), FANTASY("fantasy"),
	EDUCATIONAL("educational"), SCI_FI("sci-fi"), MYSTERY("mystery");
	
	private String desc;
	
	Genre(String genre){
		this.desc = genre;
	}
	
	public static Genre toGenre(String genre){
		for(Genre g:Genre.values()){
			if(genre.equalsIgnoreCase(g.desc)){
				return g;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return this.desc;
	}}
