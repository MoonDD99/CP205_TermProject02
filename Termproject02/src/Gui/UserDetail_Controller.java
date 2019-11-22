package Gui;

import java.net.URL;
import java.util.ResourceBundle;

import Gui.model.DataModel;
import authentication.User;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UserDetail_Controller extends Base_Controller implements Initializable {

	public TextField tf_Name, tf_Password, tf_Email, tf_Phone;
	public Label lb_Rent;
	public ChoiceBox cb_Email;
	public TextArea ta_Address;
	public Button btn_ChangeInfo, btn_Confirm;
	public User user;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Base start
		super.base();
		// Base end

		// MyInfo print start
		user = DataModel.user;
		tf_Name.setText(user.getName());
		tf_Password.setText(user.getPassword());
		if (user.isLend_OK()) {
			lb_Rent.setText("�뿩 ����");
		} else {
			lb_Rent.setText("�뿩 �Ұ� : ����) å �̹ݳ�");
		}
		tf_Email.setText(user.getEmail()); // �̸��� ó�� ����
		tf_Phone.setText(user.getPhone());
		ta_Address.setText(user.getAddress());
		// MyInfo print end
	}

	public void changeinfoAction() {
		// ���� field�� ���� �־������� �����ϱ�
	}

	public void confirmAction() {
		// ���� ȭ������

	}

}
