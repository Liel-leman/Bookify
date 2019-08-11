package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Model.Subsc_ShopModel;
import View.Subsc_shop;

public class Subsc_ShopController extends MouseAdapter implements ActionListener{
	Subsc_ShopModel model;
	Subsc_shop view;

	public Subsc_ShopController(Subsc_shop view) {
		this.model = new Subsc_ShopModel();
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent caller) {
		
		
		switch(caller.getActionCommand())
				{
				case("Buy"):
					System.out.println("return");
					model.buy(view);
					break;
				}
	}

	public void mouseClicked(MouseEvent e) {  
        model.selectedOnTable(view);
    } 
	
}