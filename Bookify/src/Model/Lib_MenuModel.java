package Model;

import java.awt.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale.Category;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import View.Lib_Menu;
import View.Lib_addSubsc;
import View.Login;
import net.proteanit.sql.DbUtils;

public class Lib_MenuModel extends DBConnector{

	sqliteConnection singelton= sqliteConnection.getInstance();
	Connection connection  = singelton.dbConnector();

	public void OpenAddSubscWindow(Lib_Menu obj)
	{
		new Lib_addSubsc().setVisible(true);
	}
	
	public void Logout(Lib_Menu obj)
	{
		obj.dispose();
		new Login().frame.setVisible(true);;
	}
	
	public void ExitPressed(Lib_Menu obj)
	{
		
		System.exit(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	
	public void mouseklicked_pane1(Lib_Menu obj)
	{
		JTable table=obj.table;
		JTextField BookIDField=obj.BookIDField;
		JTextField AuthorField=obj.AuthorField;
		JTextField YearofproducionField=obj.YearofproducionField;
		JTextField PagesField=obj.PagesField;
		JTextField NameField=obj.NameField;
		JTextField CategoryField=obj.CategoryField;
		JTextField LanguageField=obj.LanguageField;
		JTextField MinAgeField=obj.MinAgeField;
		JTextField CostField=obj.CostField;
		
		
		
		try 
		{
			int row = table.getSelectedRow();
			String BookID_=(table.getModel().getValueAt(row, 0)).toString();
			
		String query = "select * from Book where BookID =?";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setString(1, BookID_);
		ResultSet rs = pst.executeQuery();
		while(rs.next())
		{
			BookIDField.setText(rs.getString("BookID"));
			AuthorField.setText(rs.getString("Author"));
			YearofproducionField.setText(rs.getString("Yearofproducion"));
			PagesField.setText(rs.getString("Pages"));
			NameField.setText(rs.getString("Name"));
			CategoryField.setText(rs.getString("Category"));
			LanguageField.setText(rs.getString("Language"));
			MinAgeField.setText(rs.getString("MinAge"));
			CostField.setText(rs.getString("Cost"));
		}
		pst.close();
		rs.close();
		}
		catch (Exception e1) 
		{
		e1.printStackTrace();
		}
	}
	
	
	
	public void Update_panel1(Lib_Menu obj)
	{
		JTable table=obj.table;
		JTextField BookIDField=obj.BookIDField;
		JTextField AuthorField=obj.AuthorField;
		JTextField YearofproducionField=obj.YearofproducionField;
		JTextField PagesField=obj.PagesField;
		JTextField NameField=obj.NameField;
		JTextField CategoryField=obj.CategoryField;
		JTextField LanguageField=obj.LanguageField;
		JTextField MinAgeField=obj.MinAgeField;
		JTextField CostField=obj.CostField;

		String query = "Update Book set Author=?,YearOfProducion=?,Pages=?,Name=?,Category=?,Language=?,MinAge=?,Cost=? where BookID=?";
		try 
		{
		PreparedStatement pst = connection.prepareStatement(query);
		int i=1;
		pst.setString(i++, AuthorField.getText());
		pst.setInt(i++,Integer.parseInt(YearofproducionField.getText()));
		pst.setInt(i++,Integer.parseInt(PagesField.getText()));
		pst.setString(i++, NameField.getText());
		pst.setString(i++, CategoryField.getText());
		pst.setString(i++, LanguageField.getText());
		pst.setInt(i++,Integer.parseInt(MinAgeField.getText()));
		pst.setInt(i++,Integer.parseInt(CostField.getText()));
		pst.setInt(i++,Integer.parseInt(BookIDField.getText()));
		pst.execute();
		
		JOptionPane.showMessageDialog(null, "Data Saved");
		PanelService.UpdatePanel(1, table);
		pst.close();
		}
		catch (Exception e1) 
		{
		e1.printStackTrace();
		JOptionPane.showMessageDialog(null, "Eror Please select Book");
		}
	}
	public void Save_panel1(Lib_Menu obj)
	{
		JTable table=obj.table;
		JTable table2=obj.table2;
		JTextField BookIDField=obj.BookIDField;
		JTextField AuthorField=obj.AuthorField;
		JTextField YearofproducionField=obj.YearofproducionField;
		JTextField PagesField=obj.PagesField;
		JTextField NameField=obj.NameField;
		JTextField CategoryField=obj.CategoryField;
		JTextField LanguageField=obj.LanguageField;
		JTextField MinAgeField=obj.MinAgeField;
		JTextField CostField=obj.CostField;
		
		try 
		{
		String query = "insert into Book (Author,YearOfProducion,Pages,Name,Category,Language,MinAge,Cost) values (?,?,?,?,?,?,?,?)";
		
		PreparedStatement pst = connection.prepareStatement(query);
		int i=1;
		pst.setString(i++, AuthorField.getText());
		pst.setInt(i++,Integer.parseInt(YearofproducionField.getText()));
		pst.setInt(i++,Integer.parseInt(PagesField.getText()));
		pst.setString(i++, NameField.getText());
		pst.setString(i++, CategoryField.getText());
		pst.setString(i++, LanguageField.getText());
		pst.setInt(i++,Integer.parseInt(MinAgeField.getText()));
		pst.setInt(i++,Integer.parseInt(CostField.getText()));
		
		pst.execute();
		
		JOptionPane.showMessageDialog(null, "Data Saved");
		PanelService.UpdatePanel(1, table);
		PanelService.UpdatePanel(2, table2);
		pst.close();
		}
		catch ( Exception e ) {
            System.err.println( e );
            
        }
	}
	
	
	
	
	
	public void Delete_panel1(Lib_Menu obj)
	{
		JTextField BookIDField=obj.BookIDField;
		JTable table=obj.table;
		
		int action = JOptionPane.showConfirmDialog(null, "do you Really Want To Delete?","Delete",JOptionPane.YES_NO_OPTION);
		if(action==0)
		{
			try 
			{
				String query = "Delete from Book where BookID='"+BookIDField.getText()+"' ";
				PreparedStatement pst = connection.prepareStatement(query);
		
				pst.execute();
		
				JOptionPane.showMessageDialog(null, "Data Deleted");
		
				pst.close();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			PanelService.UpdatePanel(1, table);
		}
	}
	
	
	public void keyreleased_panel1(Lib_Menu obj)
	{
		JTextField txtSearch=obj.txtSearch;
		JComboBox Search_comboBox=obj.Search_comboBox;
		JTable table=obj.table;
		try 
		{
		String category = (String)Search_comboBox.getSelectedItem();
		PreparedStatement pst = connection.prepareStatement(
			    "SELECT * FROM Book WHERE "+category+" like ?");
		pst.setString(1, txtSearch.getText() + "%");
		ResultSet rs = pst.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		pst.close();
		rs.close();
		}
		catch (Exception e1) 
		{
		e1.printStackTrace();
		}
	}
	
	
	
	
	
	
	public void pane2_addbutton(Lib_Menu obj)
	{
		JTextField BookIDField2=obj.BookIDField2;
		JTextField ShelfField = obj.ShelfField;
		JTextField BranchField = obj.BranchField;
		JTable table2=obj.table2;
		
		try 
			{
			BooksCopyService BCS = new BooksCopyService();
			int numCopy=BCS.numcopycheck(Integer.parseInt(BookIDField2.getText()));
			
			String query = "insert into BooksCopy (BookID,NumCopy,Shelf,Branch,IsRented) values (?,?,?,?,?)";
			
			PreparedStatement pst = connection.prepareStatement(query);
			int i =1;
			pst.setInt(i++,Integer.parseInt(BookIDField2.getText()));
			pst.setInt(i++, numCopy);
			pst.setString(i++, ShelfField.getText());
			pst.setString(i++, BranchField.getText());
			pst.setString(i++, "false");
			pst.execute();
			JOptionPane.showMessageDialog(null, "Data Saved");
			PanelService.UpdatePanel(2, table2);
			
			pst.close();
			
			}
			catch (Exception e) 
			{
			e.printStackTrace();
			}
	}
	
	
	
	public void pane2_removebutton(Lib_Menu obj)
	{
		String BookcopyID_=obj.BookcopyID_;
		JTable table2=obj.table2;
		
		int action = JOptionPane.showConfirmDialog(null, "do you Really Want To Delete?","Delete",JOptionPane.YES_NO_OPTION);
			if(action==0)
			{
				try 
				{
					String query = "Delete from BooksCopy where BooksCopyID=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setInt(1, Integer.parseInt(BookcopyID_));
					pst.execute();
					
			
					JOptionPane.showMessageDialog(null, "Data Deleted");
			
					pst.close();
					
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			PanelService.UpdatePanel(2, table2);
	}
	
	
	public void pane2_refreshbutton(Lib_Menu obj)
	{
		
		String BookcopyID_=obj.BookcopyID_;
		JTextField ShelfField = obj.ShelfField;
		JTextField BranchField = obj.BranchField;
		JTable table2=obj.table2;
		
		try 
			{
			String query = "update BooksCopy set Shelf=?,Branch=?,IsRented=? where books"
					+ "copyid=?";
			
			PreparedStatement pst = connection.prepareStatement(query);
			int i =1;
			pst.setString(i++, ShelfField.getText());
			pst.setString(i++, BranchField.getText());
			pst.setString(i++, "true");
			pst.setString(i++, BookcopyID_);
			pst.execute();
			
			JOptionPane.showMessageDialog(null, "Updated");
			PanelService.UpdatePanel(2, table2);
			
			pst.close();
			
			}
			catch (Exception e1) 
			{
			e1.printStackTrace();
			}
	}
	public void pane2_getbookIDbycombobox(Lib_Menu obj)
	{
		JTextField BookIDField2=obj.BookIDField2;
		JComboBox Search_comboBox2=obj.Search_comboBox2;
		int BookID_var=obj.BookID_var;
		
		try 
			{
				String query = "select * from Book where name=?";
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1, (String)Search_comboBox2.getSelectedItem());
				ResultSet rs = pst.executeQuery();
				BookID_var = rs.getInt("BookID");
				BookIDField2.setText(rs.getString("BookID"));
				pst.execute();	
				pst.close();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
	}
	
	
	
}
