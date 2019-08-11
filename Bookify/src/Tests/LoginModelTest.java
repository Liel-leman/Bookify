package Tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.JRadioButton;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Controller.LoginController;
import Model.LoginModel;
import View.Login;

public class LoginModelTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	

	  
	@Before
	public void setUp() throws Exception { 
	System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(originalOut);
	    System.setErr(originalErr);
	}

	@Test
	public void testLoginFuncasLibrarian() {//libririan log in 
		Login view = new Login();
	    view.rdbtnLibrarian.setSelected(true);
		 
		view.usernameField.setText("eli");
		view.passwordField.setText("123456");
		new LoginModel().LoginFunc(view);
		

		boolean Bool = outContent.toString().contains("logined as libririan");
		
		
		
		assertEquals(true,Bool);
		
	}

	
	@Test
	public void testLoginFuncasSubscriber() {//subscriber log in 
		Login view = new Login();
	    view.rdbtnLibrarian.setSelected(false);
		 
		view.usernameField.setText("test");
		view.passwordField.setText("test");
		new LoginModel().LoginFunc(view);
		
		boolean Bool = outContent.toString().contains("logined as subscriber");
		
		
		assertEquals(true, Bool);
	}
	
	@Test
	public void testLoginWrongUserOrPASS() {//wrong field
		Login view = new Login();
	    view.rdbtnLibrarian.setSelected(false);
		 
		view.usernameField.setText("tesjkt");
		view.passwordField.setText("test");
		new LoginModel().LoginFunc(view);
		
		boolean Bool = outContent.toString().contains("incorrect");
		
		
		assertEquals(true, Bool);
	}
	
	
	@Test
	public void testLoginWrongAuthorities() {//scene where subscriber want to connect as libriirian
		Login view = new Login();
	    view.rdbtnLibrarian.setSelected(true);
		 
		view.usernameField.setText("test");
		view.passwordField.setText("test");
		new LoginModel().LoginFunc(view);
		
		boolean Bool = outContent.toString().contains("incorrect");
		
		
		assertEquals(true, Bool);
	}
	
	
}
