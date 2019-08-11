package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Model.Lib_MenuModel;
import View.Lib_Menu;

public class Lib_MenuController extends MouseAdapter implements ActionListener,KeyListener{
	Lib_MenuModel model;
	Lib_Menu view;

	public Lib_MenuController(Lib_Menu view) {
		this.model = new Lib_MenuModel();
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent caller) {
		switch(caller.getActionCommand())
				{
				case("Add Subscriber"):
					model.OpenAddSubscWindow(view);
					break;
				case("Logout"):
					model.Logout(view);
					break;		
				case("Exit"):	
					model.ExitPressed(view);
					break;	
				case("Save"):
					model.Save_panel1(view);
					break;			
				case("Update"):
					model.Update_panel1(view);
					break;			
				case("Delete"):
					model.Delete_panel1(view);
				//pane 2
					break;	
				case(""):
					model.pane2_addbutton(view);
					break;	
				case("hidenAddButton2"):		
					model.pane2_addbutton(view);
					break;
				case("hidenRemoveButton2"):
					model.pane2_removebutton(view);
					break;
				case("hidenRefreshButton2"):
					model.pane2_refreshbutton(view);
					break;
				case("Combobox pane 2"):
					model.pane2_getbookIDbycombobox(view);
					break;	
						
				
				}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		model.mouseklicked_pane1(view);
		
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		model.keyreleased_panel1(view);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}