package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Subsc_MyBorrowingsController;
import Model.PanelService;
import Model.Subscriber;
import Model.sqliteConnection;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.SystemColor;

public class Subsc_MyBorrowings extends JFrame {
	public int selected_borrowedID=0,selected_bookscopyID=0;
	public JPanel contentPane;
	public JTable table;
	public Subscriber subsc=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Subsc_MyBorrowings frame = new Subsc_MyBorrowings(new Subscriber(6));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public Subsc_MyBorrowings(Subscriber subscriber) {
		super("Bookify");
	    Subsc_MyBorrowingsController controller=new Subsc_MyBorrowingsController(this);	
		subsc = subscriber;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 620, 452);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 16, 568, 277);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(SystemColor.controlHighlight);
		table.addMouseListener(controller);
		scrollPane.setViewportView(table);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(controller);
		btnReturn.setBounds(248, 334, 115, 29);
		contentPane.add(btnReturn);
		
		PanelService.UpdatePanel(1, table, subsc.subscriber.getID());
		
	}

}
