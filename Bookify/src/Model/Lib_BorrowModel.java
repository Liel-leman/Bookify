package Model;

import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.management.Query;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import View.Lib_Menu;
import net.proteanit.sql.DbUtils;

public class Lib_BorrowModel {
	
	sqliteConnection singelton= sqliteConnection.getInstance();
	Connection connection  = singelton.dbConnector();
	
	public void search(Lib_Menu obj)
	{
		JTable table = obj.table3;
		try {
		String query =" select bookscopyid,book.name,person.username[subscriber],RentingDate,ReturningDate from borrow join bookscopy using(BooksCopyID) join"
			        +" book using(bookid) join person on rentedby=id where subscriber like '"+obj.txtSearchbox.getText()+"%'";
		PreparedStatement pst = connection.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
        pst.close();
        rs.close();
		
		
		
		}
		catch (Exception e) 
		 { 
			 e.printStackTrace(); 
		 } 
		
		
	}
	public void returnFunc(Lib_Menu obj)
	{
		
		
		
		int bookcopy_table3=obj.bookcopy_table3;
		
		System.out.println(bookcopy_table3);
		JTable table = obj.table3;
		try 
		{
			String query ="Update Bookscopy set isrented='false' where bookscopyid="+bookcopy_table3;
			PreparedStatement pst = connection.prepareStatement(query);
			pst.execute();
			pst=null;
			
			query ="delete from borrow where bookscopyid="+bookcopy_table3;
			pst = connection.prepareStatement(query);
			pst.execute();
	        pst.close();
	        System.out.println("succesfully returned");
			PanelService.UpdatePanel(3, table);
			
		}
			catch (Exception e) 
		{ 
				 e.printStackTrace(); 
		} 
		
		
	}
	 
	
}
