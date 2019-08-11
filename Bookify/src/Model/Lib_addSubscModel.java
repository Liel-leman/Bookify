package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import View.Lib_addSubsc;

public class Lib_addSubscModel {

	
	sqliteConnection singelton= sqliteConnection.getInstance();
	Connection connection  = singelton.dbConnector();

	
	public void addsubsc(Lib_addSubsc obj) {
		
				 JTextField UserNameField=obj.UserNameField;
				 JTextField PasswordField=obj.PasswordField;
				 JTextField CityField=obj.CityField;
				 JTextField CountryField=obj.CountryField;
				 JTextField LastNameField=obj.LastNameField;
				 JTextField AgeField=obj.AgeField;
				 JTextField FirstNameField=obj.FirstNameField;
				 JTextField AddressField=obj.AddressField;
		
		
		try 
		{
		String query = "insert into Person (UserName,Password,Country,City,Address,FirstName,LastName,Age,isLibrarian) values (?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst = connection.prepareStatement(query);
		int i=1;
		pst.setString(i++, UserNameField.getText());
		pst.setString(i++, PasswordField.getText());
		pst.setString(i++, CountryField.getText());
		pst.setString(i++, CityField.getText());
		pst.setString(i++, AddressField.getText());
		pst.setString(i++, FirstNameField.getText());
		pst.setString(i++, LastNameField.getText());
		pst.setInt(i++, Integer.parseInt(AgeField.getText()));
		pst.setString(i++, "False");
		pst.execute();
		JOptionPane.showMessageDialog(null, "Added Succesfully");
		pst.close();
		obj.dispose();
		}
		catch ( Exception e1 ) {
            System.err.println( e1 );
            
        }
	}
}
