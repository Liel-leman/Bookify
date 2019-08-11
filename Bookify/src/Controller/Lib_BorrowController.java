package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Model.Lib_BorrowModel;
import View.Lib_Menu;

public class Lib_BorrowController extends KeyAdapter implements ActionListener{
	Lib_BorrowModel model;
	Lib_Menu view;

	public Lib_BorrowController(Lib_Menu view) {
		this.model = new Lib_BorrowModel();
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent caller) {
					System.out.println("controller acceptted");
					model.returnFunc(view);
	}
	@Override
	public void keyReleased(KeyEvent event)
	{
		
		model.search(view);
		
 	}
	
	
	
	
	
	
}