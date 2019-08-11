package Tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.BooksCopy;
import Model.DBConnector;
import Model.Person;
import Model.PersonService;
import Model.Subscriber;

public class PersonServiceTest extends DBConnector {

	
	

	@Test
	public void testGetAllPersons() {
		List<Person> subsc_list = new PersonService().getAllPersons();
		
		assertEquals(4,subsc_list.size());
	}

	@Test
	public void testGetPersonByID() {
		Person P = new PersonService().getPersonByID(8);
		assertEquals("test", P.getFirstName());
	}

}
