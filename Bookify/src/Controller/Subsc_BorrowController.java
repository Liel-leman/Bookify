package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Subsc_BorrowModel;
import View.Subsc_Borrow;

public class Subsc_BorrowController implements ActionListener{
	Subsc_BorrowModel model;
	Subsc_Borrow view;

	public Subsc_BorrowController(Subsc_Borrow view) {
		this.model = new Subsc_BorrowModel();
		this.view = view;
	}

	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent caller) {
		switch(caller.getActionCommand())
				{
				case("<"):
					model.moveLeft(view);
					break;
				case(">"):

					model.moveRight(view);
					break;
				case("Borrow"):

					model.Borrowfunc(view);
					
					break;
				}
	}

	

	
	
	
}