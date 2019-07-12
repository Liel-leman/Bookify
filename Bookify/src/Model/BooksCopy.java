package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BooksCopy extends DBConnector{
	  int BooksCopyID;
	  int BookID;
	  int NumCopy;
	  String Shelf;
	  String Branch;
	  
	  
	  public BooksCopy() {
			super();
			BooksCopyID = 0;
			BookID = 0;
			NumCopy = 0;
			Shelf = "";
			Branch = "";
			IsRented = false;
		}
	  
	 
	
	public BooksCopy(int BooksCopyID, int bookID, int numCopy, String shelf, String branch, Boolean isRented) {
		super();
		this.BooksCopyID = BooksCopyID;
		BookID = bookID;
		NumCopy = numCopy;
		Shelf = shelf;
		Branch = branch;
		IsRented = isRented;
	}
	@Override
	public String toString() {
		return "BooksCopy [BooksCopyID=" + BooksCopyID + ", BookID=" + BookID + ", NumCopy=" + NumCopy + ", Shelf="
				+ Shelf + ", Branch=" + Branch + ", IsRented=" + IsRented + "]";
	}
	Boolean IsRented;
	public int getBooksCopyID() {
		return BooksCopyID;
	}
	public void setBooksCopyID(int BooksCopyID) {
		this.BooksCopyID = BooksCopyID;
	}
	public int getBookID() {
		return BookID;
	}
	public void setBookID(int bookID) {
		BookID = bookID;
	}
	public int getNumCopy() {
		return NumCopy;
	}
	public void setNumCopy(int numCopy) {
		NumCopy = numCopy;
	}
	public String getShelf() {
		return Shelf;
	}
	public void setShelf(String shelf) {
		Shelf = shelf;
	}
	public String getBranch() {
		return Branch;
	}
	public void setBranch(String branch) {
		Branch = branch;
	}
	public Boolean getIsRented() {
		return IsRented;
	}
	public void setIsRented(Boolean isRented) {
		IsRented = isRented;
	}
	  
}
