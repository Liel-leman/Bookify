package View;
import java.sql.*;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import Controller.Lib_BorrowController;
import Controller.Lib_MenuController;
import Model.BooksCopyService;
import Model.Person;
import Model.sqliteConnection;
import Model.PanelService;
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
import java.awt.SystemColor;

public class Lib_Menu extends JFrame {
	//img
	Image img_Plus = new ImageIcon(this.getClass().getResource("/Actions-list-add-icon.png")).getImage();
	Image img_Minus = new ImageIcon(this.getClass().getResource("/Actions-list-remove-icon.png")).getImage();
	Image img_Refresh = new ImageIcon(this.getClass().getResource("/Refresh-icon.png")).getImage();
	public JTextField BookIDField;
	public JTextField AuthorField;
	public JTextField YearofproducionField;
	public JTextField PagesField;
	public JTextField CategoryField;
	public JTextField NameField;
	public JTextField LanguageField;
	public JTextField MinAgeField;
	public JTextField CostField;
	public JPanel contentPane;
	public JTextField txtSearch;
	public static JTable table;
	public JComboBox Search_comboBox;
	///////////////////pane2
	public JTextField BookIDField2;
	public int BookID_var;
	public JTextField BranchField;
	public JTextField ShelfField;
	public JComboBox Search_comboBox2;
	public static JTable table2;
	public String BookcopyID_;
	///////////////////pane3
	public static JTable table3;
	public JTextField txtSearchbox;
	public int bookcopy_table3=0;
	
	
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
					Lib_Menu frame = new Lib_Menu();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	static Connection connection=null;
	
	
	
	
	public Lib_Menu() {	
		
		Lib_MenuController controller=new Lib_MenuController(this);
		Lib_BorrowController controller3=new Lib_BorrowController(this);
		sqliteConnection singelton= sqliteConnection.getInstance();
		connection  = singelton.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1140, 534);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("Menu");
		menuBar.add(mnFile);
		
		JMenuItem mntmAddSubscriber = new JMenuItem("Add Subscriber");
		mntmAddSubscriber.addActionListener(controller);
		mntmAddSubscriber.setIcon(new ImageIcon(Lib_Menu.class.getResource("/com/sun/javafx/scene/web/skin/Paste_16x16_JFX.png")));
		mnFile.add(mntmAddSubscriber);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(controller);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(controller);
		mntmLogout.setIcon(new ImageIcon(Lib_Menu.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
		mnFile.add(mntmLogout);
		mntmExit.setIcon(new ImageIcon(Lib_Menu.class.getResource("/com/sun/javafx/scene/web/skin/DecreaseIndent_16x16_JFX.png")));
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1128, 459);
		contentPane.add(tabbedPane);
	    
	    Panel panel = new Panel();
	    panel.setBackground(SystemColor.activeCaption);
	    tabbedPane.addTab("Book Catalog", null, panel, null);
	    panel.setLayout(null);
	    
	    JScrollPane BookExistPane = new JScrollPane();
	    BookExistPane.setBounds(0, 0, 709, 424);
	    panel.add(BookExistPane);
	    
	    table = new JTable();
	    table.addMouseListener(controller);
	    BookExistPane.setViewportView(table);
	    
	    JButton SaveButton = new JButton("Save");
	    SaveButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
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
	    });
	    SaveButton.setBounds(1001, 395, 115, 29);
	    panel.add(SaveButton);
	    
	    JLabel lblBookID = new JLabel("BookID");
	    lblBookID.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblBookID.setBounds(806, 67, 69, 20);
	    panel.add(lblBookID);
	    
	    BookIDField = new JTextField();
	    BookIDField.setEditable(false);
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
	    UpdateButton.addActionListener(controller);
	    UpdateButton.setBounds(876, 395, 115, 29);
	    panel.add(UpdateButton);
	    
	    JButton DeleteButton = new JButton("Delete");
	    DeleteButton.addActionListener(controller);
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
	    txtSearch.addKeyListener(controller);
	    txtSearch.setColumns(10);
	   
	   // 
		 ///////////////////////////////////////////////////////////////////////////////////////////////////
		 
		 
		 
		 
		 
		 
	  		Panel panel2 = new Panel();
	  		panel2.setBackground(SystemColor.activeCaption);
	  		tabbedPane.addTab("in my library", null, panel2, null);
	  		panel2.setLayout(null);
	  		
			Search_comboBox2 = new JComboBox();
	  		Search_comboBox2.setActionCommand("Combobox pane 2");
	  		Search_comboBox2.setBounds(958, 129, 146, 26);
	  		Search_comboBox2.addActionListener(controller);
	  		panel2.add(Search_comboBox2);
	  		
	  		
	  		
	  		
	  		
	  		JButton AddButton = new JButton("");
	  		AddButton.setActionCommand("hidenAddButton2");
	  		AddButton.setIcon(new ImageIcon(img_Plus));
	  		AddButton.setBounds(958, 254, 39, 29);
	  		AddButton.addActionListener(controller);
	  		panel2.add(AddButton);
	  		
	  		JLabel lblBookID2 = new JLabel("Name");
	  		lblBookID2.setBounds(806, 131, 69, 20);
	  		lblBookID2.setFont(new Font("Tahoma", Font.PLAIN, 18));
	  		panel2.add(lblBookID2);
	  		
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
	  		RemoveButton.setActionCommand("hidenRemoveButton2");
	  		RemoveButton.setIcon(new ImageIcon(img_Minus));
	  		RemoveButton.setBounds(904, 254, 39, 29);
	  		RemoveButton.addActionListener(controller);
	  		panel2.add(RemoveButton);
	  		
	  
	  		JLabel lblBookCopyID = new JLabel("BookID");
	  		lblBookCopyID.setBounds(806, 93, 121, 20);
	  		lblBookCopyID.setFont(new Font("Tahoma", Font.PLAIN, 18));
	  		panel2.add(lblBookCopyID);
	  		
	  		BookIDField2 = new JTextField();
	  		BookIDField2.setBounds(958, 89, 146, 26);
	  		BookIDField2.setFont(new Font("Tahoma", Font.PLAIN, 18));
	  		BookIDField2.setEditable(false);
	  		BookIDField2.setColumns(10);
	  		panel2.add(BookIDField2);
	  		
	  		JScrollPane BookCopy = new JScrollPane();
	  		BookCopy.setBounds(0, 0, 709, 424);
	  		panel2.add(BookCopy);
	  		
	  		table2 = new JTable();
	  		table2.addMouseListener(new MouseAdapter() {
	  			@Override
	  			public void mouseClicked(MouseEvent arg0) {
	  				try 
	      			{
	  				int row = table2.getSelectedRow();
					 BookcopyID_=(table2.getModel().getValueAt(row, 0)).toString();
					String query = "select * from BooksCopy where BooksCopyID =?";
					PreparedStatement pst = connection.prepareStatement(query);

					pst.setString(1, BookcopyID_);
					ResultSet rs = pst.executeQuery();
					while(rs.next())
					{
						BookIDField2.setText(rs.getString("BookID"));
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
	  		
		 JButton RefreshButton = new JButton("");
		 RefreshButton.setActionCommand("hidenRefreshButton2");
		 RefreshButton.addActionListener(controller);
		 RefreshButton.setIcon(new ImageIcon(img_Refresh));
		 RefreshButton.setBounds(934, 288, 39, 35);
		 panel2.add(RefreshButton);
		 PanelService.UpdatePanel(2, table2);
		 
	    
		 JPanel panel3 = new JPanel();
		 panel3.setBackground(SystemColor.activeCaption);
		 tabbedPane.addTab("Boorow List", null, panel3, null);
		 panel3.setLayout(null);
		 
		 JScrollPane scrollPane = new JScrollPane();
		 scrollPane.setBounds(0, 0, 709, 424);
		 panel3.add(scrollPane);
		 
		 table3 = new JTable();
		 table3.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		int row = table3.getSelectedRow();
		 		bookcopy_table3=(int) table3.getModel().getValueAt(row, 0);
		 	}
		 });
		 scrollPane.setViewportView(table3);
		 PanelService.UpdatePanel(1, table);
		 PanelService.UpdatePanel(3, table3);
		 
		 
		 JButton btnReturn = new JButton("Return");
		 btnReturn.setFont(new Font("Cooper Black", btnReturn.getFont().getStyle(), btnReturn.getFont().getSize() + 4));
		 btnReturn.addActionListener(controller3);
		 btnReturn.setBounds(889, 197, 115, 29);
		 panel3.add(btnReturn);
		 
		 txtSearchbox = new JTextField();
		 txtSearchbox.setFont(new Font("Cooper Black", txtSearchbox.getFont().getStyle(), txtSearchbox.getFont().getSize() + 4));
			
		 txtSearchbox.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		txtSearchbox.setText("");
		 	}
		 });
		 txtSearchbox.addKeyListener(controller3);
		 txtSearchbox.setText("Search");
		 txtSearchbox.setBounds(868, 146, 154, 35);
		 panel3.add(txtSearchbox);
		 txtSearchbox.setColumns(10);
		 
		 JLabel lblSearchBySubscriber = new JLabel("Search By Subscriber");
		 lblSearchBySubscriber.setFont(new Font("Cooper Black", lblSearchBySubscriber.getFont().getStyle(), lblSearchBySubscriber.getFont().getSize() + 4));
		 lblSearchBySubscriber.setBounds(832, 122, 230, 20);
		 panel3.add(lblSearchBySubscriber);
		 fillComboBox();
		 
	}
}
