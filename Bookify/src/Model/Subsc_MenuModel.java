package Model;
import javax.swing.JFrame;

import View.*;

public class Subsc_MenuModel {

	public void SwitchPageToBorrow(Subsc_Menu obj) {
		new Subsc_Borrow(obj.subsc).setVisible(true);
	}

	public void SwitchPageToMyInfo(Subsc_Menu obj) {
		
		new Subsc_myInfo(obj.subsc).setVisible(true);
	}

	public void SwitchPageToMyBorrowings(Subsc_Menu obj) {
		new Subsc_MyBorrowings(obj.subsc).setVisible(true);
		
	}

	public void Logout(Subsc_Menu obj)
	{
		obj.dispose();
		new Login().frame.setVisible(true);;
	}
	
	public void ExitPressed(Subsc_Menu obj)
	{
		
		System.exit(JFrame.EXIT_ON_CLOSE);
	}
	public void SwitchPageToShop(Subsc_Menu obj)
	{
		new Subsc_shop().setVisible(true);
	}
	
	
}
