package Gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class MyBookList_Controller extends Base_Controller implements Initializable {

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

	public void showborrowedAction() { // ���� å MybooklistField�� ������ LoanedBook ��ü ������

	}

	public void showloanedAction() { // ������ å MybooklistField�� ������ registeredBook �� lend �� å

	}

	public void showregisteredAction() { // ����� å MybooklistField�� ������ registeredBook ��ü ������

	}

	public void showsoldAction() { // �ȸ� å MybooklistField�� ������ registeredBook �� soldout�� å

	}

	public void backAction() { // �� ȭ������

	}

}
