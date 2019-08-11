package Tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.BookService;
import Model.DBConnector;
import Model.Subscriber;

public class BookServiceTest extends DBConnector{
int count =0;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllBooks() {
		Connection connection = Connect();
        Statement statement = null;
        ResultSet resultSet = null;
    	try {
             statement = connection.createStatement();
             resultSet= statement.executeQuery("SELECT count(*) from book");
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
    	assertEquals(count,new BookService().getAllBooks().size() );
	}
}
