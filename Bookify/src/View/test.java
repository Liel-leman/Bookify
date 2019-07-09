package View;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Book;
import Model.Librarian;
import Model.Person;
import Model.sqliteConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Panel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class test extends JFrame {
	private JTextField BookCopyIDField;
	private JTextField lblBookField;
	private JTextField IsRentedField;
	private JTextField BranchField;
	private JTextField ShelfField;
	private JPanel contentPane;
	private JTextField txtSearch;
	private static JTable table;
	private static JTable bookCopyTable;
	private JComboBox Search_comboBox;
	static void outputBooksToScreen()
	{
		try 
		{
		String query = "select * from Book";
		PreparedStatement pst = connection.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		pst.close();
		rs.close();
		}
		catch (Exception sqlException) 
		{
		sqlException.printStackTrace();
		}
	}
	static void outputBooksToScreenPanel2()

	{
		try 
		{
		String query = "select * from BooksCopy";
		PreparedStatement pst = connection.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		bookCopyTable.setModel(DbUtils.resultSetToTableModel(rs));
		pst.close();
		rs.close();
		}
		catch (Exception sqlException) 
		{
		sqlException.printStackTrace();
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
					frame.setVisible(true);
					outputBooksToScreen();
					outputBooksToScreenPanel2();
					
					Librarian librarian = new Librarian("eli");
					
					
					
					JOptionPane.showMessageDialog(null, librarian.Address);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	static Connection connection=null;
	

	/*
	 * public void fillComboBox() { try { String query = "select * from Book";
	 * PreparedStatement pst = connection.prepareStatement(query); ResultSet rs =
	 * pst.executeQuery();
	 * 
	 * while(rs.next()) { comboBox.addItem(rs.getString("Name")); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */
	/**
	 * Create the frame.
	 */
	public test() {
		sqliteConnection singelton= sqliteConnection.getInstance();
		connection  = singelton.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1178, 598);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnBooks = new JMenu("Books");
		menuBar.add(mnBooks);
		
		JMenuItem mntmBooksinmylib = new JMenuItem("BooksInMyLib");
		mntmBooksinmylib.setIcon(new ImageIcon(test.class.getResource("/com/sun/javafx/scene/web/skin/Paste_16x16_JFX.png")));
		mnBooks.add(mntmBooksinmylib);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		mntmExit.setIcon(new ImageIcon(test.class.getResource("/com/sun/javafx/scene/web/skin/DecreaseIndent_16x16_JFX.png")));
		mnBooks.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1156, 522);
		contentPane.add(tabbedPane);
	    
	    Panel panel = new Panel();
	    tabbedPane.addTab("BookExists", null, panel, null);
	    panel.setLayout(null);
	    
	    JScrollPane BookExistPane = new JScrollPane();
	    BookExistPane.setBounds(0, 0, 709, 424);
	    panel.add(BookExistPane);
	    
	    table = new JTable();
	    table.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
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
	    			BookCopyIDField.setText(rs.getString("BookID"));
	    			lblBookField.setText(rs.getString("Author"));
	    			IsRentedField.setText(rs.getString("Yearofproducion"));
	    			BranchField.setText(rs.getString("Pages"));
	    			ShelfField.setText(rs.getString("Name"));
	    			
	    		}
	    		pst.close();
	    		rs.close();
	    		}
	    		catch (Exception e1) 
	    		{
	    		e1.printStackTrace();
	    		}
	    	}
	    });
	    BookExistPane.setViewportView(table);
	    
	    JButton AddButton = new JButton("Add");
	    AddButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		try 
	    		{
	    		String query = "insert into Book (BookID,Author,YearOfProducion,Pages,Name,Category,Language,MinAge,Cost) values (?,?,?,?,?,?,?,?,?)";
	    		
	    		PreparedStatement pst = connection.prepareStatement(query);
	    		pst.setInt(1,Integer.parseInt(BookCopyIDField.getText()));
	    		pst.setString(2, lblBookField.getText());
	    		pst.setInt(3,Integer.parseInt(IsRentedField.getText()));
	    		pst.setInt(4,Integer.parseInt(BranchField.getText()));
	    		pst.setString(5, ShelfField.getText());

	    		
	    		pst.execute();
	    		
	    		JOptionPane.showMessageDialog(null, "Data Saved");
	    		outputBooksToScreen();
	    		pst.close();
	    		}
	    		catch (Exception e) 
	    		{
	    		e.printStackTrace();
	    		}
	    	}
	    });
	    AddButton.setBounds(987, 289, 115, 29);
	    panel.add(AddButton);
	    
	    JLabel lblBookCopyID = new JLabel("BookCopyID");
	    lblBookCopyID.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblBookCopyID.setBounds(806, 67, 137, 20);
	    panel.add(lblBookCopyID);
	    
	    BookCopyIDField = new JTextField();
	    BookCopyIDField.setBounds(958, 65, 146, 26);
	    panel.add(BookCopyIDField);
	    BookCopyIDField.setColumns(10);
	    
	    JLabel lblBookID = new JLabel("BookID");
	    lblBookID.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblBookID.setBounds(806, 103, 69, 20);
	    panel.add(lblBookID);
	    
	    lblBookField = new JTextField();
	    lblBookField.setColumns(10);
	    lblBookField.setBounds(958, 99, 146, 26);
	    panel.add(lblBookField);
	    
	    JLabel lblIsRented = new JLabel("IsRented");
	    lblIsRented.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblIsRented.setBounds(806, 135, 137, 20);
	    panel.add(lblIsRented);
	    
	    IsRentedField = new JTextField();
	    IsRentedField.setColumns(10);
	    IsRentedField.setBounds(958, 133, 146, 26);
	    panel.add(IsRentedField);
	    
	    JLabel lblBranch = new JLabel("Branch");
	    lblBranch.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblBranch.setBounds(806, 171, 69, 20);
	    panel.add(lblBranch);
	    
	    BranchField = new JTextField();
	    BranchField.setColumns(10);
	    BranchField.setBounds(958, 168, 146, 26);
	    panel.add(BranchField);
	    
	    ShelfField = new JTextField();
	    ShelfField.setColumns(10);
	    ShelfField.setBounds(958, 206, 146, 26);
	    panel.add(ShelfField);
	    
	    JLabel lblShelf = new JLabel("Shelf");
	    lblShelf.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblShelf.setBounds(806, 209, 69, 20);
	    panel.add(lblShelf);
	    
	    JButton RemoveButton = new JButton("Remove");
	    RemoveButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		int action = JOptionPane.showConfirmDialog(null, "do you Really Want To Delete?","Delete",JOptionPane.YES_NO_OPTION);
	    		if(action==0)
	    		{
	    			try 
	    			{
	    				String query = "Delete from Book where BookID='"+BookCopyIDField.getText()+"' ";
	    				PreparedStatement pst = connection.prepareStatement(query);
	    		
	    				pst.execute();
	    		
	    				JOptionPane.showMessageDialog(null, "Data Deleted");
	    		
	    				pst.close();
	    			}
	    			catch (Exception e) 
	    			{
	    				e.printStackTrace();
	    			}
	    		}
	    		outputBooksToScreen();
	    	}
	    });
	    RemoveButton.setBounds(830, 289, 115, 29);
	    panel.add(RemoveButton);
	    
	    Search_comboBox = new JComboBox();
	    Search_comboBox.setBounds(771, 16, 141, 26);
	    panel.add(Search_comboBox);
	    
	    
	    
	    
	    
	    Search_comboBox.setModel(new DefaultComboBoxModel(new String[] {"BookID", "Author", "YearOfProducion", "Pages", "Name", "Category", "Language", "MinAge", "Cost"}));
	    
	    txtSearch = new JTextField();
	    txtSearch.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		txtSearch.setText("");
	    	}
	    });
	    txtSearch.setText("Search");
	    txtSearch.setBounds(958, 16, 146, 26);
	    panel.add(txtSearch);
	    txtSearch.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyReleased(KeyEvent arg0) {
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
	    });
	    txtSearch.setColumns(10);
	    
	    JPanel panel_BooksCopy = new JPanel();
	    
	    tabbedPane.addTab("Books Copy", null, panel_BooksCopy, null);
	    panel_BooksCopy.setLayout(null);
	    
	    JScrollPane scrollPane_1 = new JScrollPane();
	    scrollPane_1.setBounds(0, 0, 717, 377);
	    panel_BooksCopy.add(scrollPane_1);
	    
	    bookCopyTable = new JTable();
	    scrollPane_1.setViewportView(bookCopyTable);
		
	}
}
