package Model;

import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import View.Subsc_MyBorrowings;

public class Subsc_MyBorrowingsModel extends DBConnector{
	 int selected_borrowedID=0,selected_bookscopyID=0;
	 JPanel contentPane;
	 JTable table;
	 Subscriber subsc=null;
	sqliteConnection singelton= sqliteConnection.getInstance();
	Connection connection  = singelton.dbConnector();
	
	

	public void selectedOnTable(Subsc_MyBorrowings obj) {
		selected_borrowedID=obj.selected_borrowedID;
		selected_bookscopyID=obj.selected_borrowedID;
		  contentPane=obj.contentPane;
		  table=obj.table;
		  subsc=obj.subsc;
		int row = table.getSelectedRow();
		selected_borrowedID=(int) table.getModel().getValueAt(row, 2);
		selected_bookscopyID=(int)table.getModel().getValueAt(row, 3);
		System.out.println(selected_borrowedID);
	}
	
	
	
	
	
	public void returnFunc(Subsc_MyBorrowings obj)
	{
		
		contentPane=obj.contentPane;
			  table=obj.table;
			  subsc=obj.subsc;
		
		if(selected_bookscopyID != 0)
		{
		try 
		{//fist i set the rented atribute to false than i deleate from boorow table
    		String query = "Update Bookscopy set isRented='false' where bookscopyid="+selected_bookscopyID;
    		PreparedStatement pst = connection.prepareStatement(query);
    		pst.execute();
    		
			query = "Delete from Borrow where borrowID="+selected_borrowedID;
			pst = connection.prepareStatement(query);
			pst.execute();
	
			JOptionPane.showMessageDialog(null, "succesfully returne");
			
			pst.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		}
		else
			JOptionPane.showMessageDialog(null, "you need to select a book");
		PanelService.UpdatePanel(1, table, subsc.subscriber.getID());
	}
		 
	
}
