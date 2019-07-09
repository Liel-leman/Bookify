package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import net.proteanit.sql.DbUtils;

public class Person {
	Connection connection = null;
	public int ID =0;
	public String Username="";
	public String Password="";
	public String Country="";
	public String City="";
	public String Address ="";
	public String FirstName="";
	public String LastName="";
	public int Age=0;
	
	
	
	
	
	
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public void setUsername(String username) {
		this.Username = username;
	}
	public void setPassword(String password) {
		this.Password = password;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public void setCity(String city) {
		City = city;
	}
	public void setAge(int age) {
		Age = age;
	}

	
}
