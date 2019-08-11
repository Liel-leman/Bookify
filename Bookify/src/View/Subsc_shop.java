package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Subsc_ShopController;
import Model.PanelService;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class Subsc_shop extends JFrame {

	public JPanel contentPane;
	public JTable table;
	public JTextArea BillTextField;
	public int book_count = 0;
	public int BookID;
	public float totalprice=0;
	public float tax=0;

	
	
	//imsages
	Image img_BuyButton = new ImageIcon(this.getClass().getResource("/Subsc_BuyBotton.png")).getImage();
	
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public Subsc_shop() {
		super("Bookify");
		Subsc_ShopController controller = new Subsc_ShopController(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 326, 521);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 308, 202);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(SystemColor.menu);
		scrollPane.setViewportView(table);
		table.addMouseListener(controller);
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.setFont(new Font("Cooper Black", btnBuy.getFont().getStyle(), btnBuy.getFont().getSize() + 4));
		btnBuy.setHorizontalAlignment(SwingConstants.LEFT);
		btnBuy.setIcon(new ImageIcon(img_BuyButton));
		btnBuy.addActionListener(controller);
		btnBuy.setBounds(94, 204, 124, 49);
		contentPane.add(btnBuy);
		
		PanelService.UpdatePanel(4, table);
		
		BillTextField = new JTextArea();
		BillTextField.setBackground(SystemColor.menu);
		BillTextField.setText("\r\n==============Your Bill===============\r\nBook Tax=....................0$\r\nBook Cost with  tax=.........0$\r\n__________________________________________________________\r\n__________________________________________________________\r\n\r\nBooks Bought =...............0\r\nTotal TAX =..................0$\r\nTotal Cost=..................0$\r\n==========Come to Buy Again===========");
		BillTextField.setFont(new Font("Courier New", Font.PLAIN, 14));
		BillTextField.setBounds(0, 256, 304, 209);
		contentPane.add(BillTextField);
	}
}
