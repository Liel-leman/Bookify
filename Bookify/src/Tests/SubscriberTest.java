package Tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.DBConnector;
import Model.Person;
import Model.Subscriber;

public class SubscriberTest  extends DBConnector{
	int count=0;
	@Before
	public void setUp() throws Exception{
	
	
	}
	@After
	public void tearDown() throws Exception {
		
		
		
	}
	@Test
	public void constractortest() {
		Subscriber S = new Subscriber(8);
    	assertEquals("test",S.subscriber.FirstName);
	}
		
	@Test
	public void getNumOfBorrowedBookstest() {
		Connection connection = Connect();
        Statement statement = null;
        ResultSet resultSet = null;
    	try {
             statement = connection.createStatement();
             resultSet= statement.executeQuery("SELECT count(*) from borrow where rentedby ='8'");
             count = resultSet.getInt(1);
    	 }
    	 catch ( Exception e ) {
             System.err.println( e.getClass().getName() + ": " + e.getMessage() );
             System.exit(0);
         }
    	 finally 
    	 {
             Disconnect(connection, statement, null, resultSet);
         }
    	assertEquals(count,new Subscriber(8).getNumOfBorrowedBooks() );
	}
		
		
}
