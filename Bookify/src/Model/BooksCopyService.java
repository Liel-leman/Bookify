package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BooksCopyService extends BooksCopy{

    private BooksCopy BooksCopyMaker (ResultSet resultSet){
    	BooksCopy booksCopy = new BooksCopy();
        try {
        	booksCopy.setBooksCopyID(resultSet.getInt("BooksCopyID"));
        	booksCopy.setBookID(resultSet.getInt("BookID"));
        	booksCopy.setNumCopy(resultSet.getInt("NumCopy"));
        	booksCopy.setShelf(resultSet.getString("Shelf"));
        	booksCopy.setIsRented(resultSet.getBoolean("IsRented"));
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return booksCopy;
    }

    public	 List<BooksCopy> getAllBooksCopys(){
        List<BooksCopy> BooksCopyList = new ArrayList<>();
        Connection connection = Connect();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM BooksCopy");

            while ( resultSet.next() ) {
                BooksCopy booksCopy = BooksCopyMaker(resultSet);
                BooksCopyList.add(booksCopy);
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        } finally {
            Disconnect(connection, statement, null, resultSet);
        }
        return BooksCopyList;
    }
    
    
    public	 List<BooksCopy> getAllBooksCopysBypersonID(int ID){
        List<BooksCopy> BooksCopyList = new ArrayList<>();
        Connection connection = Connect();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM BooksCopy join Borrow using(BooksCopyID) where RentedBy = " + ID);

            while ( resultSet.next() ) {
                BooksCopy booksCopy = BooksCopyMaker(resultSet);
                BooksCopyList.add(booksCopy);
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        } finally {
            Disconnect(connection, statement, null, resultSet);
        }
        return BooksCopyList;
    }
    
    
    
    public int numcopycheck(int bookid)
    {
    	System.out.println(bookid);
    	List<BooksCopy> BooksCopyList = new ArrayList<>();
    	 BooksCopyList=getAllBooksCopys();
    	 int count=1;
    	 for (BooksCopy book : BooksCopyList){
             if(book.getBookID()==bookid)
             {
            	 count++;
             }
         }
    	 update_numcop(bookid, count);
    	 return count;
    	
    }
    public void update_numcop(int bookid,int count)
    {
    	sqliteConnection singelton= sqliteConnection.getInstance();
    	Connection connection = singelton.dbConnector();
    	Statement statement = null;
        ResultSet resultSet = null;
    	try {
             statement = connection.createStatement();
             statement.executeUpdate("Update BooksCopy set NumCopy="+count+" where BookID="+bookid);
            
    	 }
    	 catch ( Exception e ) {
             System.err.println( e.getClass().getName() + ": " + e.getMessage() );
             System.exit(0);
         }
    	 finally 
    	 {
             Disconnect(connection, statement, null, resultSet);
         }
    }
    
}