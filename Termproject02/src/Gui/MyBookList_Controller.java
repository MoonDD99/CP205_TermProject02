package Gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class MyBookList_Controller extends Base_Controller implements Initializable{

	@FXML
	public Button btn_LoanedBook, btn_BorrowedBook, btn_RegisteredBook, btn_SoldBook, btn_Back;
	@FXML
	public ListView MybooklistField;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Base start
		super.base();
		// Base end
	}
	
	public void showloanedAction() { //������ å MybooklistField�� ������
		
	}
	
	public void showborrowedAction(){ //���� å  MybooklistField�� ������
		
	}
	
	public void showregisteredAction () { //����� å  MybooklistField�� ������
		
	}


	public void showsoldAction () { //�ȸ� å  MybooklistField�� ������
		
	}
	
	public void backAction() { // �� ȭ������ 
		
	}

}
