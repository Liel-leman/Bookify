package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Model.Subsc_MyBorrowingsModel;
import View.Subsc_MyBorrowings;

public class Subsc_MyBorrowingsController extends MouseAdapter implements ActionListener{
	Subsc_MyBorrowingsModel model;
	Subsc_MyBorrowings view;

	public Subsc_MyBorrowingsController(Subsc_MyBorrowings view) {
		this.model = new Subsc_MyBorrowingsModel();
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent caller) {
		
		
		switch(caller.getActionCommand())
				{
				case("Return"):
					System.out.println("return");
					model.returnFunc(view);
					break;
				}
	}

	public void mouseClicked(MouseEvent e) {  
        model.selectedOnTable(view);
    } 
	
}