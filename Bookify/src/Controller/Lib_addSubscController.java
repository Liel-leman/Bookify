package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Lib_addSubscModel;
import Model.Subsc_MenuModel;
import View.Lib_addSubsc;
import View.Subsc_Menu;

public class Lib_addSubscController implements ActionListener{
	Lib_addSubscModel model;
	Lib_addSubsc view;

	public Lib_addSubscController(Lib_addSubsc view) {
		this.model = new 	Lib_addSubscModel();
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		model.addsubsc(view);
	}

	
	
}
