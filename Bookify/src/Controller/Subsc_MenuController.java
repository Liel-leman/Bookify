package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Subsc_MenuModel;
import View.Subsc_Menu;
import View.Subsc_myInfo;

public class Subsc_MenuController implements ActionListener{
	Subsc_MenuModel model;
	Subsc_Menu view;

	public Subsc_MenuController(Subsc_Menu view) {
		this.model = new Subsc_MenuModel();
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent caller) {
		System.out.println(caller.getActionCommand());
		switch(caller.getActionCommand())
				{
		
				case("Logout"):
				model.Logout(view);
				break;		
				case("Exit"):	
				model.ExitPressed(view);
				break;	
				case("Borrow"):
				model.SwitchPageToBorrow(view);	
				break;
				case("My Info"):
				model.SwitchPageToMyInfo(view);	
				break;
				case("My Borrowings"):
				model.SwitchPageToMyBorrowings(view);
				break;
				case("Shop"):
				model.SwitchPageToShop(view);
				break;
				}
				
	}
}

