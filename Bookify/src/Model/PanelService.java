package Model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class PanelService extends DBConnector{
	
	public static void UpdatePanel(int queryNum,JTable table)
	{
		sqliteConnection singelton= sqliteConnection.getInstance();
    	Connection connection = singelton.dbConnector();
	        Statement statement = null;
	        ResultSet resultSet = null;
	        String query="";
	        switch (queryNum) {
	        	case 1:
	        		query = "select * from Book";
	        		break;
	        	case 2:
	        		query = "select bookscopyid,Name,Numcopy,cost,language,category,branch,shelf from BooksCopy join Book using(bookid) where isrented='false'";
	        		break;
	        	case 3:
	        		query = " select bookscopyid,book.name,person.username[subscriber],RentingDate,ReturningDate from borrow join bookscopy using(BooksCopyID) join"
	    			        +" book using(bookid) join person on rentedby=id ";
	        		break;
	        	case 4:
	        		query = "select bookID,name,cost from book ";
	        		break;
	        }//end switch case

	        try 
	        {
	            statement = connection.createStatement();
	            resultSet= statement.executeQuery(query);
	            table.setModel(DbUtils.resultSetToTableModel(resultSet));
	            statement.close();
	            resultSet.close();
	        }
			catch ( Exception e ) 
	        {
	            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	            System.exit(0);
	        } 
	       
	}
	
	public static void UpdatePanel(int queryNum,JTable table,int ID)
	{
		sqliteConnection singelton= sqliteConnection.getInstance();
    	Connection connection = singelton.dbConnector();
	        Statement statement = null;
	        ResultSet resultSet = null;
	        String query="";
	        switch (queryNum) {
	        	case 1:
	        		query = "select Name,ReturningDate,BorrowID,bookscopyid from borrow join bookscopy using(BooksCopyID) join Book using(BookID) where rentedby= "+ID;
	        		break;
	        }//end switch case

	        try 
	        {
	            statement = connection.createStatement();
	            
	            resultSet= statement.executeQuery(query);
	            table.setModel(DbUtils.resultSetToTableModel(resultSet));
	            statement.close();
	            resultSet.close();
	        }
			catch ( Exception e ) 
	        {
	            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	            System.exit(0);
	        } 
	       
	}

}
