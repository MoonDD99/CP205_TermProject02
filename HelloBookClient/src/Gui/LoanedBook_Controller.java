package Gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LoanedBook_Controller extends Base_Controller implements Initializable{

	public Button btn_Back, btn_Remove, btn_Comfirm, btn_Return, btn_LateinReturn;
	public Label lb_WhoBorrorwed, lb_Title, lb_Author, lb_Publisher, lb_BookCondition, lb_FullPrice,
	lb_SalePrice, lb_LendPrice, lb_Introduction;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Base start
		super.base();
		// Base end
	}
	
	public void backAction() {//���� ȭ������
		
	}

	public void removeAction() {//��ϵ� å�� ����
		
	}
	
	public void comfirmAction() {//���� ȭ������
		
	}
	public void returnAction() { //�ݳ�Ȯ��, lb_RentalStatus�� true��
		
	}
	public void lateinreturnAction() { //���� �����  lb_Rent�� false��
		
	}
}
