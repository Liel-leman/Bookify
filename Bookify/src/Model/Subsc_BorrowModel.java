package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListModel;

import com.toedter.calendar.JDateChooser;

import View.Subsc_Borrow;

public class Subsc_BorrowModel extends DBConnector{

	sqliteConnection singelton= sqliteConnection.getInstance();
	Connection connection  = singelton.dbConnector();
	JList<String> inLibrary=null;
	JDateChooser dateChooserFrom=null;
	JDateChooser dateChooserUntill=null;
	JList<String> ToBorrow=null;
		
	public void moveRight(Subsc_Borrow obj)
	{
		DefaultListModel model1=obj.model1;
		DefaultListModel model2=obj.model2;
		inLibrary=obj.inLibrary;
		ToBorrow=obj.ToBorrow;
		
		if (inLibrary.getSelectedIndices().length > 0) {
            int[] selectedIndices = inLibrary.getSelectedIndices();
            //append selected to list2
            for (int i = 0; i < selectedIndices.length; i++) {
                model2.addElement(inLibrary.getModel().getElementAt(selectedIndices[i]));
            }
            //remove selected from list1
            for (int i = selectedIndices.length - 1; i >= 0; i--) {
                model1.removeElementAt(selectedIndices[i]);
            }
            ToBorrow.setModel(model2);
        }
	}
	
	
	public void moveLeft(Subsc_Borrow obj)
	{
		inLibrary=obj.inLibrary;
	    ToBorrow=obj.ToBorrow;
		DefaultListModel model1=obj.model1;
		DefaultListModel model2=obj.model2;
		
		
		if (ToBorrow.getSelectedIndices().length > 0) {
            int[] selectedIndices = ToBorrow.getSelectedIndices();
            //append selected to list1
            for (int i = 0; i < selectedIndices.length; i++) {
                model1.addElement(ToBorrow.getModel().getElementAt(selectedIndices[i]));
            }
            //remove selected from list2
            for (int i = selectedIndices.length - 1; i >= 0; i--) {
                model2.removeElementAt(selectedIndices[i]);
            }
            inLibrary.setModel(model1);
        }
	}
	
	
	
	
	
	
	
	
	
	
	public void Borrowfunc(Subsc_Borrow obj) {
		inLibrary=obj.inLibrary;
		JList ToBorrow=obj.ToBorrow;
		JPanel contentPane=obj.contentPane;
		dateChooserFrom=obj.dateChooserFrom;
		dateChooserUntill=obj.dateChooserUntill;
		Subscriber subsc = obj.subsc;
		ListModel<String> LM=ToBorrow.getModel();
		if(LM.getSize()!=0 && dateChooserFrom.getDate()!=null && dateChooserUntill.getDate()!=null)
		{
				
			for(int i=0;i<LM.getSize();i++)
			{
				int bookcopyID=0;
				//finding the first book that matches the criteria
				try 
				{
				PreparedStatement pst = connection.prepareStatement("select bookscopyid from bookscopy join book using( bookid )where isrented = 'false' and name = ?");
	    		pst.setString(1,LM.getElementAt(i) );
	    		ResultSet rs = pst.executeQuery();
	    		bookcopyID = rs.getInt(1);

	    		pst.close();
	    		rs.close();
				}
				catch (Exception e1) 
				{ 
					 e1.printStackTrace(); 
				}
				//updating it value of isrented = true
				try
				{
					String query ="Update bookscopy set IsRented='true' where bookscopyid=?";
					PreparedStatement pst = connection.prepareStatement(query);
		    		pst.setInt(1, bookcopyID);
		    		pst.execute();
		    		pst.close();
		    	    updateBorrowsql(bookcopyID,subsc.subscriber.getID());
				}
				catch (Exception e1) 
				{ 
						 e1.printStackTrace(); 
				}
			}
			
			JOptionPane.showMessageDialog(null, "Congrats! you borrowed them.");
			
			obj.dispose();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "select books and fill all the fields");
		}
		
	    }
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		private void updateBorrowsql(int bookcopyID, int subscID) {
			System.out.println(bookcopyID);
			System.out.println(subscID);

			
			try 
			{
			String query = "insert into Borrow (RentedBy,RentingDate,ReturningDate,BooksCopyID) values (?,?,?,?)";
			
			PreparedStatement pst = connection.prepareStatement(query);
			int i=1;
			pst.setInt(i++, subscID);
			pst.setString(i++,dateChooserFrom.getDate().toString() );
			pst.setString(i++,dateChooserUntill.getDate().toString());
			pst.setInt(i++, bookcopyID);
			
			pst.execute();
			System.out.println("borrow sql updated , bookcopyID="+bookcopyID);
			pst.close();
			}
			catch ( Exception e ) {
	            System.err.println( e );
	            
	        }
			
		}
		
		
		
}
		
		
		
