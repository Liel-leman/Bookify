package View;
import java.sql.*;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import Model.BooksCopyService;
import Model.Person;
import Model.sqliteConnection;
import net.proteanit.sql.DbUtils;

import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MenuWindow extends JFrame {
	//img
	Image img_Plus = new ImageIcon(this.getClass().getResource("/Actions-list-add-icon.png")).getImage();
	Image img_Minus = new ImageIcon(this.getClass().getResource("/Actions-list-remove-icon.png")).getImage();
	private JTextField BookIDField;
	private JTextField AuthorField;
	private JTextField YearofproducionField;
	private JTextField PagesField;
	private JTextField CategoryField;
	private JTextField NameField;
	private JTextField LanguageField;
	private JTextField MinAgeField;
	private JTextField CostField;
	private JPanel contentPane;
	private JTextField txtSearch;
	private static JTable table;
	private JComboBox Search_comboBox;
	///////////////////pane2
	private JTextField BookCopyIDField2;
	private JTextField BookIDField2;
	private JTextField BranchField;
	private JTextField ShelfField;
	private JComboBox Search_comboBox2;
	private JRadioButton Rented;
	private static JTable table2;
	
	////functions 
	static void pane1load()
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
	static void Pane2Load()
	{
		try 
		{
		String query = "SELECT * FROM BooksCopy";
		PreparedStatement pst = connection.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		table2.setModel(DbUtils.resultSetToTableModel(rs));
		pst.close();
		rs.close();
		}
		catch (Exception sqlException) 
		{
		sqlException.printStackTrace();
		}
	}
	public void fillComboBox() {
	try { 
		String query = "select * from Book";
		PreparedStatement pst = connection.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
	 
		while(rs.next())
		{ 
			Search_comboBox2.addItem(rs.getString("Name"));
		}
		pst.close();
		rs.close();
	}
	catch (Exception e) 
	 { 
		 e.printStackTrace(); 
	 } 
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuWindow frame = new MenuWindow();
					frame.setVisible(true);
					pane1load();
					Pane2Load();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	static Connection connection=null;
	
	
	
	public MenuWindow() {
		sqliteConnection singelton= sqliteConnection.getInstance();
		connection  = singelton.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1178, 598);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnBooks = new JMenu("Books");
		menuBar.add(mnBooks);
		
		JMenuItem mntmBooksinmylib = new JMenuItem("BooksInMyLib");
		mntmBooksinmylib.setIcon(new ImageIcon(MenuWindow.class.getResource("/com/sun/javafx/scene/web/skin/Paste_16x16_JFX.png")));
		mnBooks.add(mntmBooksinmylib);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		mntmExit.setIcon(new ImageIcon(MenuWindow.class.getResource("/com/sun/javafx/scene/web/skin/DecreaseIndent_16x16_JFX.png")));
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
	    });
	    BookExistPane.setViewportView(table);
	    
	    JButton SaveButton = new JButton("Save");
	    SaveButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		try 
	    		{
	    		String query = "insert into Book (BookID,Author,YearOfProducion,Pages,Name,Category,Language,MinAge,Cost) values (?,?,?,?,?,?,?,?,?)";
	    		
	    		PreparedStatement pst = connection.prepareStatement(query);
	    		pst.setInt(1,Integer.parseInt(BookIDField.getText()));
	    		pst.setString(2, AuthorField.getText());
	    		pst.setInt(3,Integer.parseInt(YearofproducionField.getText()));
	    		pst.setInt(4,Integer.parseInt(PagesField.getText()));
	    		pst.setString(5, NameField.getText());
	    		pst.setString(6, CategoryField.getText());
	    		pst.setString(7, LanguageField.getText());
	    		pst.setInt(8,Integer.parseInt(MinAgeField.getText()));
	    		pst.setInt(9,Integer.parseInt(CostField.getText()));
	    		
	    		pst.execute();
	    		
	    		JOptionPane.showMessageDialog(null, "Data Saved");
	    		pane1load();
	    		pst.close();
	    		}
	    		catch (Exception e) 
	    		{
	    		e.printStackTrace();
	    		}
	    	}
	    });
	    SaveButton.setBounds(1001, 395, 115, 29);
	    panel.add(SaveButton);
	    
	    JLabel lblBookID = new JLabel("BookID");
	    lblBookID.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblBookID.setBounds(806, 67, 69, 20);
	    panel.add(lblBookID);
	    
	    BookIDField = new JTextField();
	    BookIDField.setBounds(958, 65, 146, 26);
	    panel.add(BookIDField);
	    BookIDField.setColumns(10);
	    
	    JLabel lblAuthor = new JLabel("Author");
	    lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblAuthor.setBounds(806, 103, 69, 20);
	    panel.add(lblAuthor);
	    
	    AuthorField = new JTextField();
	    AuthorField.setColumns(10);
	    AuthorField.setBounds(958, 99, 146, 26);
	    panel.add(AuthorField);
	    
	    JLabel lblYearofproducion = new JLabel("YearOfProducion");
	    lblYearofproducion.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblYearofproducion.setBounds(806, 135, 137, 20);
	    panel.add(lblYearofproducion);
	    
	    YearofproducionField = new JTextField();
	    YearofproducionField.setColumns(10);
	    YearofproducionField.setBounds(958, 133, 146, 26);
	    panel.add(YearofproducionField);
	    
	    JLabel lblPages = new JLabel("Pages");
	    lblPages.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblPages.setBounds(806, 171, 69, 20);
	    panel.add(lblPages);
	    
	    PagesField = new JTextField();
	    PagesField.setColumns(10);
	    PagesField.setBounds(958, 168, 146, 26);
	    panel.add(PagesField);
	    
	    CategoryField = new JTextField();
	    CategoryField.setColumns(10);
	    CategoryField.setBounds(958, 240, 146, 26);
	    panel.add(CategoryField);
	    
	    NameField = new JTextField();
	    NameField.setColumns(10);
	    NameField.setBounds(958, 206, 146, 26);
	    panel.add(NameField);
	    
	    LanguageField = new JTextField();
	    LanguageField.setColumns(10);
	    LanguageField.setBounds(958, 274, 146, 26);
	    panel.add(LanguageField);
	    
	    MinAgeField = new JTextField();
	    MinAgeField.setColumns(10);
	    MinAgeField.setBounds(958, 309, 146, 26);
	    panel.add(MinAgeField);
	    
	    JLabel lblMinage = new JLabel("MinAge");
	    lblMinage.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblMinage.setBounds(806, 312, 69, 20);
	    panel.add(lblMinage);
	    
	    JLabel lblLanguage = new JLabel("Language");
	    lblLanguage.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblLanguage.setBounds(806, 277, 95, 20);
	    panel.add(lblLanguage);
	    
	    JLabel lblCategory = new JLabel("Category");
	    lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblCategory.setBounds(806, 243, 95, 20);
	    panel.add(lblCategory);
	    
	    JLabel lblName = new JLabel("Name");
	    lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblName.setBounds(806, 209, 69, 20);
	    panel.add(lblName);
	    
	    CostField = new JTextField();
	    CostField.setColumns(10);
	    CostField.setBounds(958, 341, 146, 26);
	    panel.add(CostField);
	    
	    JLabel lblCost = new JLabel("Cost");
	    lblCost.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblCost.setBounds(806, 344, 69, 20);
	    panel.add(lblCost);
	    
	    JButton UpdateButton = new JButton("Update");
	    UpdateButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		String query = "Update Book set BookID=?,Author=?,YearOfProducion=?,Pages=?,Name=?,Category=?,Language=?,MinAge=?,Cost=? where BookID=?";
	    		try 
	    		{
	    		PreparedStatement pst = connection.prepareStatement(query);
	    		pst.setInt(1,Integer.parseInt(BookIDField.getText()));
	    		pst.setString(2, AuthorField.getText());
	    		pst.setInt(3,Integer.parseInt(YearofproducionField.getText()));
	    		pst.setInt(4,Integer.parseInt(PagesField.getText()));
	    		pst.setString(5, NameField.getText());
	    		pst.setString(6, CategoryField.getText());
	    		pst.setString(7, LanguageField.getText());
	    		pst.setInt(8,Integer.parseInt(MinAgeField.getText()));
	    		pst.setInt(9,Integer.parseInt(CostField.getText()));
	    		pst.setInt(10,Integer.parseInt(BookIDField.getText()));
	    		pst.execute();
	    		
	    		JOptionPane.showMessageDialog(null, "Data Saved");
	    		pane1load();
	    		pst.close();
	    		}
	    		catch (Exception e1) 
	    		{
	    		e1.printStackTrace();
	    		}
	    	}
	    });
	    UpdateButton.setBounds(876, 395, 115, 29);
	    panel.add(UpdateButton);
	    
	    JButton DeleteButton = new JButton("Delete");
	    DeleteButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
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
	    		}
	    		pane1load();
	    	}
	    });
	    DeleteButton.setBounds(746, 395, 115, 29);
	    panel.add(DeleteButton);
	    
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
	    ///////////////////////////////////////////////////////////////////////////////////////////////////
	    
	    
	    
	    
	    
	    
	    
	    ButtonGroup bg1 = new ButtonGroup( );
	  		
	  		Panel panel2 = new Panel();
	  		tabbedPane.addTab("Book copy", null, panel2, null);
	  		panel2.setLayout(null);
	  		
	  		JButton AddButton = new JButton("");
	  		AddButton.setIcon(new ImageIcon(img_Plus));
	  		AddButton.setBounds(958, 254, 39, 29);
	  		AddButton.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent arg0) {
	  				try 
	  				{
	  				BooksCopyService BCS = new BooksCopyService();
	  				int numCopy=BCS.numcopycheck(Integer.parseInt(BookIDField2.getText()));
	  			    Boolean rented = ((Rented.isSelected()==true)? true:false);
	  				String query = "insert into BooksCopy (BookID,NumCopy,Shelf,Branch,IsRented) values (?,?,?,?,?)";
	  				
	  				PreparedStatement pst = connection.prepareStatement(query);
	  				int i =1;
	  				pst.setInt(i++, Integer.parseInt(BookIDField2.getText()));
	  				pst.setInt(i++, numCopy);
	  				pst.setString(i++, ShelfField.getText());
	  				pst.setString(i++, BranchField.getText());
	  				pst.setString(i++, rented.toString());
	  				pst.execute();
	  				
	  				JOptionPane.showMessageDialog(null, "Data Saved");
	  				Pane2Load();
	  				
	  				pst.close();
	  				
	  				}
	  				catch (Exception e) 
	  				{
	  				e.printStackTrace();
	  				}
	  			}
	  		});
	  		panel2.add(AddButton);
	  		
	  		JLabel lblBookID2 = new JLabel("BookID");
	  		lblBookID2.setBounds(806, 103, 69, 20);
	  		lblBookID2.setFont(new Font("Tahoma", Font.PLAIN, 18));
	  		panel2.add(lblBookID2);
	  		
	  		BookIDField2 = new JTextField();
	  		BookIDField2.setBounds(958, 99, 146, 26);
	  		BookIDField2.setEditable(false);
	  		BookIDField2.setFont(new Font("Tahoma", Font.PLAIN, 18));
	  		BookIDField2.setColumns(10);
	  		panel2.add(BookIDField2);
	  		
	  		JLabel lblIsRented = new JLabel("IsRented");
	  		lblIsRented.setBounds(806, 135, 137, 20);
	  		lblIsRented.setFont(new Font("Tahoma", Font.PLAIN, 18));
	  		panel2.add(lblIsRented);
	  		
	  		JLabel lblBranch = new JLabel("Branch");
	  		lblBranch.setBounds(806, 171, 69, 20);
	  		lblBranch.setFont(new Font("Tahoma", Font.PLAIN, 18));
	  		panel2.add(lblBranch);
	  		
	  		BranchField = new JTextField();
	  		BranchField.setBounds(958, 168, 146, 26);
	  		BranchField.setColumns(10);
	  		panel2.add(BranchField);
	  		
	  		ShelfField = new JTextField();
	  		ShelfField.setBounds(958, 206, 146, 26);
	  		ShelfField.setColumns(10);
	  		panel2.add(ShelfField);
	  		
	  		JLabel lblShelf = new JLabel("Shelf");
	  		lblShelf.setBounds(806, 209, 69, 20);
	  		lblShelf.setFont(new Font("Tahoma", Font.PLAIN, 18));
	  		panel2.add(lblShelf);
	  		
	  		JButton RemoveButton = new JButton("");
	  		RemoveButton.setIcon(new ImageIcon(img_Minus));
	  		RemoveButton.setBounds(904, 254, 39, 29);
	  		RemoveButton.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent arg0) {
	  				int action = JOptionPane.showConfirmDialog(null, "do you Really Want To Delete?","Delete",JOptionPane.YES_NO_OPTION);
	  				if(action==0)
	  				{
	  					try 
	  					{
	  						String query = "Delete from BooksCopy where BooksCopyID=?";
	  						PreparedStatement pst = connection.prepareStatement(query);
	  						pst.setInt(1, Integer.parseInt(BookCopyIDField2.getText()));
	  						pst.execute();
	  				
	  						JOptionPane.showMessageDialog(null, "Data Deleted");
	  				
	  						pst.close();
	  						
	  					}
	  					catch (Exception e) 
	  					{
	  						e.printStackTrace();
	  					}
	  				}
	  				Pane2Load();
	  			}
	  		});
	  		panel2.add(RemoveButton);
	  		
	  		Search_comboBox2 = new JComboBox();
	  		Search_comboBox2.setBounds(771, 16, 141, 26);
	  		Search_comboBox2.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent arg0) {
	  				try 
	      			{
	      				String query = "select * from Book where name=?";
	      				PreparedStatement pst = connection.prepareStatement(query);
	      				pst.setString(1, (String)Search_comboBox2.getSelectedItem());
	      				ResultSet rs = pst.executeQuery();
	      				BookIDField2.setText(rs.getString("BookID"));
	      				pst.execute();	
	      				pst.close();
	      			}
	      			catch (Exception e) 
	      			{
	      				e.printStackTrace();
	      			}
	  			}
	  		});
	  		panel2.add(Search_comboBox2);
	  		
	  		
	  		Rented = new JRadioButton("Yes");
	  		Rented.setBounds(968, 132, 65, 29);
	  		Rented.setSelected(true);
	  		panel2.add(Rented);
	  		
	  		JRadioButton Notrented = new JRadioButton("No");
	  		Notrented.setBounds(1040, 132, 62, 29);
	  		panel2.add(Notrented);
	  		bg1.add(Rented);
	  		bg1.add(Notrented);
	  		
	  		JLabel lblBookCopyID = new JLabel("BookCopyID");
	  		lblBookCopyID.setBounds(806, 65, 121, 20);
	  		lblBookCopyID.setFont(new Font("Tahoma", Font.PLAIN, 18));
	  		panel2.add(lblBookCopyID);
	  		
	  		BookCopyIDField2 = new JTextField();
	  		BookCopyIDField2.setBounds(958, 61, 146, 26);
	  		BookCopyIDField2.setFont(new Font("Tahoma", Font.PLAIN, 18));
	  		BookCopyIDField2.setEditable(false);
	  		BookCopyIDField2.setColumns(10);
	  		panel2.add(BookCopyIDField2);
	  		
	  		JScrollPane BookCopy = new JScrollPane();
	  		BookCopy.setBounds(0, 0, 686, 318);
	  		panel2.add(BookCopy);
	  		
	  		table2 = new JTable();
	  		table2.addMouseListener(new MouseAdapter() {
	  			@Override
	  			public void mouseClicked(MouseEvent arg0) {
	  				try 
	      			{
	  				int row = table2.getSelectedRow();
					String BookID_=(table2.getModel().getValueAt(row, 0)).toString();
						
					String query = "select * from BooksCopy where BooksCopyID =?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, BookID_);
					ResultSet rs = pst.executeQuery();
					while(rs.next())
					{
						BookCopyIDField2.setText(rs.getString("BooksCopyID"));
						BookIDField.setText(rs.getString("BookID"));
						ShelfField.setText(rs.getString("Shelf"));
						BranchField.setText(rs.getString("Branch"));
					
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
	  		BookCopy.setViewportView(table2);
	  		
	  	    fillComboBox();
		
	}
}
