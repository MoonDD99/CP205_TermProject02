package Gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RegisteredBook_Controller extends Base_Controller implements Initializable {

	public Button btn_Back, btn_Left, btn_Right, btn_Remove, btn_Comfirm;
	public Label lb_RentalStatus, lb_Title, lb_Author, lb_Publisher, lb_BookCondition, lb_FullPrice, lb_SalePrice,
			lb_LendPrice, lb_Introduction;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Base start
		super.base();
		// Base end
	}

	public void backAction() {// ���� ȭ������
	}

	public void removeAction() {// å ����
	}

	public void comfirmAction() {//���� ������ ���·� ���� (���� å�� �����ų� �����Ѵٴ� alert�� �߸�, �װſ� ���缭 ��������,)
	}

	public void goLeftAction() {//�������� �̵��ϸ鼭 �ٸ� RegisteredBook�����ֱ�
	}

	public void goRightAction() {//���������� �̵��ϸ鼭 �ٸ� RegisteredBook�����ֱ�
	}
}
