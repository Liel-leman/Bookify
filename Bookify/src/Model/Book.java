package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Book extends DBConnector{
	  int BookID;
	  String Author;
	  int YearOfProducion;
	  int Pages;
	  String Name;
	  String Category;
	  String Language ;
	  int MinAge;
	  int Cost;

	  
	 @Override
	public String toString() {
		return "Book [BookID=" + BookID + ", Author=" + Author + ", YearOfProducion=" + YearOfProducion + ", Pages="
				+ Pages + ", Name=" + Name + ", Category=" + Category + ", Language=" + Language + ", MinAge=" + MinAge
				+ ", Cost=" + Cost + "]";
	}

	public void setBookID(int bookID) {
		BookID = bookID;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public void setYearOfProducion(int yearOfProducion) {
		YearOfProducion = yearOfProducion;
	}

	public void setPages(int pages) {
		Pages = pages;
	}

	public void setName(String name) {
		Name = name;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public void setMinAge(int minAge) {
		MinAge = minAge;
	}

	public void setCost(int cost) {
		Cost = cost;
	}

	public Book()
	 {
		BookID = 0;
		Author = "";
		YearOfProducion = 0;
		Pages = 0;
		Name = "";
		Category = "";
		Language = "";
		MinAge = 0;
		Cost = 0;	 
	 }
	  
	public Book(int bookID, String author, int yearOfProducion, int pages, String name, String category,
			String language, int minAge, int cost) {
		super();
		BookID = bookID;
		Author = author;
		YearOfProducion = yearOfProducion;
		Pages = pages;
		Name = name;
		Category = category;
		Language = language;
		MinAge = minAge;
		Cost = cost;	
	}
	public int getBookID() {
		return BookID;
	}
	public String getAuthor() {
		return Author;
	}
	public int getYearOfProducion() {
		return YearOfProducion;
	}
	public int getPages() {
		return Pages;
	}
	public String getName() {
		return Name;
	}
	public String getCategory() {
		return Category;
	}
	public String getLanguage() {
		return Language;
	}
	public int getMinAge() {
		return MinAge;
	}
	public int getCost() {
		return Cost;
	}
}
