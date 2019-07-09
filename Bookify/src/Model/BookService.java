package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookService extends Book{

    private Book BookMaker (ResultSet resultSet){
    	Book book = new Book();
        try {
        	book.setBookID(resultSet.getInt("BookID"));
        	book.setAuthor(resultSet.getString("Author"));
        	book.setYearOfProducion(resultSet.getInt("yearOfProducion"));
        	book.setPages(resultSet.getInt("Pages"));
        	book.setName(resultSet.getString("Name"));
        	book.setCategory(resultSet.getString("Category"));
        	book.setLanguage(resultSet.getString("Language"));
        	book.setMinAge(resultSet.getInt("MinAge"));
        	book.setCost(resultSet.getInt("Cost"));
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return book;
    }

    public	 List<Book> getAllBooks(){
        List<Book> bookList = new ArrayList<>();
        Connection connection = Connect();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Book");

            while ( resultSet.next() ) {
                Book book = BookMaker(resultSet);
                bookList.add(book);
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        } finally {
            Disconnect(connection, statement, null, resultSet);
        }
        return bookList;
    }
}