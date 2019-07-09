package View;

import java.awt.EventQueue;

import Model.Book;
import Model.BookService;
import Model.sqliteConnection;

import java.sql.*;
import java.util.List;

import javax.swing.*;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Button;
public class Login {
	JRadioButton rdbtnLibrarian;
	JRadioButton rdbtnSubscriber;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					Login window = new Login();
					window.frame.setVisible(true);
			}
		});
	}
	Image img_lib = new ImageIcon(this.getClass().getResource("/libraryimg.PNG")).getImage();
	Image img_login = new ImageIcon(this.getClass().getResource("/secrecy-icon.png")).getImage();
	Image img_radioSubsc = new ImageIcon(this.getClass().getResource("/Subscriber.png")).getImage();
	Image img_radiolibrarian = new ImageIcon(this.getClass().getResource("/Librarian.png")).getImage();
	
	Connection connection = null;
	private JTextField username;
	private JPasswordField passwordField;
	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		sqliteConnection singelton= sqliteConnection.getInstance();
		connection  = singelton.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 697, 443);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UserName");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(330, 127, 122, 43);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(330, 201, 109, 50);
		frame.getContentPane().add(lblNewLabel_1);
		
		username = new JTextField();
		username.setBounds(448, 127, 216, 43);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setIcon(new ImageIcon(img_login));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				{
				try {
					String query = "select * from Person where username=? and password=? and isLibrarian=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,username.getText() );//index 1
					pst.setString(2,passwordField.getText());//index 2
					if(rdbtnLibrarian.isSelected())
					{
						pst.setString(3,"True");
					}
					else {pst.setString(3,"False");}
					ResultSet rs = pst.executeQuery();
					int count  = 0;
					while(rs.next())
					{
						count++;//counting matches
					}
					if(count ==1)
					{
						JOptionPane.showMessageDialog(null, "userName & pass is Correct");
						frame.dispose();
						if(rdbtnLibrarian.isSelected())
						{
						MenuWindow menuinfo = new MenuWindow();
						menuinfo.setVisible(true);
						MenuWindow.outputBooksToScreen();
						}
						else {
							MenuWindowSubsc menuinfo = new MenuWindowSubsc();
							menuinfo.setVisible(true);
						}
					}
					else if(count>1)
					{
						JOptionPane.showMessageDialog(null, "duplicate usr& pass");
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "incorrect");
					}
					rs.close();//close Connection to DB
					pst.close();//close connection to DB
				}catch(Exception e)
				{
				JOptionPane.showMessageDialog(null, e);
				}
			}
			}
		});
		
		btnNewButton.setBounds(448, 266, 216, 43);
		frame.getContentPane().add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(448, 199, 216, 40);
		frame.getContentPane().add(passwordField);
		
		JLabel ImgLable = new JLabel("");
		ImgLable.setIcon(new ImageIcon(img_lib));
		ImgLable.setBounds(0, 0, 311, 387);
		frame.getContentPane().add(ImgLable);
		
	    rdbtnLibrarian = new JRadioButton("Librarian");
		rdbtnLibrarian.setBounds(509, 69, 155, 29);
		frame.getContentPane().add(rdbtnLibrarian);
		
		rdbtnSubscriber = new JRadioButton("Subscriber");
		rdbtnSubscriber.setSelected(true);
		rdbtnSubscriber.setBounds(354, 69, 155, 29);
		frame.getContentPane().add(rdbtnSubscriber);
		
		ButtonGroup bg1 = new ButtonGroup( );//group radio buttons
		bg1.add(rdbtnSubscriber);
		bg1.add(rdbtnLibrarian);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(img_radioSubsc));
		lblNewLabel_2.setBounds(354, 52, 69, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(img_radiolibrarian));
		label.setBounds(511, 52, 69, 20);
		frame.getContentPane().add(label);
	}
}
