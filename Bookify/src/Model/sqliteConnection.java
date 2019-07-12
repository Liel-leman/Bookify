package Model;
import java.sql.*;
import javax.swing.*;
public class sqliteConnection {
	private static sqliteConnection firstInstance= null; 
	private sqliteConnection() {}
	static boolean firstThread=true;
	public static sqliteConnection getInstance()//שלא יפתחו שני קונקשיאוונים במקביל 
	{
		if(firstInstance==null)
		{
			firstInstance = new sqliteConnection();
			firstThread=false;
			return firstInstance;
		}else {
			return firstInstance;
		}
	}
	Connection conn=null;
	public Connection dbConnector()
	{
		
		try {
			Class.forName("org.sqlite.JDBC");
			String ConString = "jdbc:sqlite:"+System.getProperty("user.dir")+"\\Bookify.sqlite";//directory of the sqlite.db
			Connection conn = DriverManager.getConnection(ConString);
			System.out.println("connection Succesfull");
			return conn;
		}
		catch(Exception e)
		{
		JOptionPane.showMessageDialog(null, e);
		return null;
		}
		
	}
}
