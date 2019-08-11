package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import View.Lib_Menu;
import View.Login;
import View.Subsc_Menu;

public class LoginModel extends DBConnector{

	sqliteConnection singelton= sqliteConnection.getInstance();
	Connection connection  = singelton.dbConnector();

	public void LoginFunc(Login obj)
	{
		
		  JTextField usernameField= obj.usernameField;
		  JPasswordField passwordField = obj.passwordField;
		  JRadioButton rdbtnLibrarian = obj.rdbtnLibrarian;
		try {
			
			String query = "select * from Person where username=? and password=? and isLibrarian=?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1,usernameField.getText() );//index 1
			pst.setString(2,passwordField.getText());//index 2
			if(rdbtnLibrarian.isSelected())
			{
				pst.setString(3,"True");
			}
			else {pst.setString(3,"False");}
			ResultSet rs = pst.executeQuery();
			int count  = 0;
			int ID=0;
			while(rs.next())
			{
				count++;//counting matches
				ID = rs.getInt("ID");
				
			}
			if(count ==1)
			{
				JOptionPane.showMessageDialog(null, "userName & pass is Correct");
				obj.frame.dispose();
				if(rdbtnLibrarian.isSelected())
				{
				System.out.println("logined as libririan");
				new Lib_Menu().setVisible(true);
				}
				else {
				new Subsc_Menu(new Subscriber(ID)).setVisible(true);
				System.out.println("logined as subscriber");
					
			}
			}
			else if(count>1)
			{
				JOptionPane.showMessageDialog(null, "duplicate usr& pass");
			}
			else 
			{
				JOptionPane.showMessageDialog(null, "incorrect");
				System.out.println("incorrect");
			}
		 } catch ( Exception e ) {
	            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	            System.exit(0);
		 }
	}
}
