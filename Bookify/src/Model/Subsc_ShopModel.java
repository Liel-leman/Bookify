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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import View.Subsc_shop;

public class Subsc_ShopModel extends DBConnector{
	 int selected_bookID=0;
	 int book_count=0;
	 float totalTax=0;
	 float totalCost=0;
	 float costToAdd;
	 float taxToAdd;
	 

	public void selectedOnTable(Subsc_shop obj) {
		JTextArea BillTextField=obj.BillTextField;
	    JTable table=obj.table;
	
		
		int row = table.getSelectedRow();
		selected_bookID=(int) table.getModel().getValueAt(row, 0);
		float cost=(int)table.getModel().getValueAt(row, 2);
		float temptax=(float) (cost*0.18);
		float tempCost =temptax+cost;
		
		
		
		BillTextField.setText("\r\n==============Your Bill===============\r\nBook "
				+ "Tax=...................."+temptax+"$"
				+ "\r\nBook Cost with  tax=........."+tempCost+"$"
				+ "\r\n__________________________________________________________\r\n__________________________________________________________\r\n"
				+ "\r\nBooks Bought =..............."+book_count+""
				+ "\r\nTotal TAX =.................."+totalTax+"$"
				+ "\r\nTotal Cost=.................."+String.format("%.2f", totalCost)+"$"
				+ "\r\n==========Come to Buy Again===========");
		
		costToAdd=tempCost;
		taxToAdd=temptax;
		
		
		}
	
	
	
	
	
	
	public void buy(Subsc_shop obj)
	{
	JPanel contentPane=obj.contentPane;
	JTable table=obj.table;
	JTextArea BillTextField = obj.BillTextField;
	
		if(selected_bookID != 0)
		{
			book_count++;
			totalTax+=taxToAdd;
			totalCost+=costToAdd;
			
			BillTextField.setText("\r\n==============Your Bill===============\r\nBook "
					+ "Tax=...................."+taxToAdd+"$"
					+ "\r\nBook Cost with  tax=........."+costToAdd+"$"
					+ "\r\n__________________________________________________________\r\n__________________________________________________________\r\n"
					+ "\r\nBooks Bought =..............."+book_count+""
					+ "\r\nTotal TAX =.................."+String.format("%.2f", totalTax)+"$"
					+ "\r\nTotal Cost=.................."+String.format("%.2f", totalCost)+"$"
					+ "\r\n==========Come to Buy Again===========");
			
		}
		else
			JOptionPane.showMessageDialog(null, "you need to select a book");
		PanelService.UpdatePanel(4, table);
	}
		 
	
}
