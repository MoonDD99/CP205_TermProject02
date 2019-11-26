package Gui;

import java.net.URL;
import java.util.ResourceBundle;

import Gui.model.DataModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import user.User;

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
		ObservableList<String> emailList = FXCollections.observableArrayList("naver.com", "gmail.com",
				"hanmail.net");
		cb_Email.setItems(emailList);

		// MyInfo print start
		user = DataModel.user;
		tf_Name.setText(user.getName());
		tf_Password.setText(user.getPassword());
		if (user.isLend_OK()) {
			lb_Rent.setText("�뿩 ����");
		} else {
			lb_Rent.setText("�뿩 �Ұ� : ����) å �̹ݳ�");
		}
		String[] email=user.getEmail().split("@");
		tf_Email.setText(email[0]); // �̸��� ó�� ����
		cb_Email.setValue(email[1]);
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
