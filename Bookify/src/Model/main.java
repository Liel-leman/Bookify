package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sqliteConnection singelton= sqliteConnection.getInstance();
		Connection connection  = singelton.dbConnector();
		try {
		Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Book");
        while ( resultSet.next() ) {
            System.out.println(resultSet.getString("Name")+"\n");
        }
		}
		catch(Exception sqlException){
			System.out.println(sqlException);
		}
		
	}

}
