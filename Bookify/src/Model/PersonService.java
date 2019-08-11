package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonService extends Person{
	
	
	 private Person PersonMaker (ResultSet resultSet){
	    	Person person = new Person();
	        try {
	        	person.setID(resultSet.getInt("ID"));
	        	person.setUsername(resultSet.getString("Username"));
	        	person.setPassword(resultSet.getString("Password"));
	        	person.setCountry(resultSet.getString("Country"));
	        	person.setCity(resultSet.getString("City"));
	        	person.setAddress(resultSet.getString("Address"));
	        	person.setFirstName(resultSet.getString("FirstName"));
	        	person.setLastName(resultSet.getString("LastName"));
	        	person.setAge(resultSet.getInt("Age"));
	        	person.setisLibrarian(resultSet.getString("IsLibrarian"));
	        } catch ( Exception e ) {
	            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	            System.exit(0);
	        }
	        return person;
	    }

	    public	 List<Person> getAllPersons(){
	        List<Person> PersonList = new ArrayList<>();
	        Connection connection = Connect();
	        Statement statement = null;
	        ResultSet resultSet = null;

	        try {
	            statement = connection.createStatement();
	            resultSet = statement.executeQuery("SELECT * FROM Person");

	            while ( resultSet.next() ) {
	                Person person = PersonMaker(resultSet);
	                PersonList.add(person);
	            }
	        } catch ( Exception e ) {
	            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	            System.exit(0);
	        } finally {
	            Disconnect(connection, statement, null, resultSet);
	        }
	        return PersonList;
	    }
	    
	    
	    public	 Person getPersonByID(int ID){
	        Person person = new Person();
	        Connection connection = Connect();
	        Statement statement = null;
	        ResultSet resultSet = null;

	        try {
	            statement = connection.createStatement();
	            resultSet = statement.executeQuery("SELECT * FROM Person Where ID = "+ID);
	            person = PersonMaker(resultSet);
	        } catch ( Exception e ) {
	            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	            System.exit(0);
	        } finally {
	            Disconnect(connection, statement, null, resultSet);
	        }
	        return person;
	    }
}
