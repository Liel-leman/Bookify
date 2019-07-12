package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import net.proteanit.sql.DbUtils;

public class Person extends DBConnector{
	public int ID =0;
	public String Username="";
	public String Password="";
	public String Country="";
	public String City="";
	public String Address ="";
	public String FirstName="";
	public String LastName="";
	public int Age=0;
	public String isLibrarian="false";
	
	
	
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	
	public void setisLibrarian(String isLibrarian) {
		this.isLibrarian = isLibrarian;
	}
	@Override
	public String toString() {
		return "Person [ID=" + ID + ", Username=" + Username + ", Password=" + Password + ", Country=" + Country
				+ ", City=" + City + ", Address=" + Address + ", FirstName=" + FirstName + ", LastName=" + LastName
				+ ", Age=" + Age + ", isLibrarian=" + isLibrarian + "]";
	}
	
	
	
	
	
	

	
}
