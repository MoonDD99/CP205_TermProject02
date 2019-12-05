package Gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ServerManage_Controller implements Initializable {
	@FXML
	public TextField tf_notice, tf_shutdown; 
	@FXML//						��������							���� �ݱ�		ȭ�� �ݱ�
	public Button btn_Notice, btn_shutdown, btn_ServerOpen, btn_ServerClose, btn_Close, btn_Main;

	@FXML
	public void noticeAction() { //����
	}

	@FXML
	public void shutdownAction() { // ��������

	}

	@FXML
	public void serverOpenAction() {// ���� ����

	}

	@FXML
	public void serverCloseAction() { // ���� �ݱ�

	}

	@FXML
	public void closeAction() {// ȭ�� ����
		Stage stage = (Stage) btn_Close.getScene().getWindow();
	    stage.close();
	}

	@FXML
	public void mainAction() { //�ٽ� ���� ȭ�� �ε�
		try {
			Stage primaryStage = (Stage) btn_Main.getScene().getWindow();
			Parent search = FXMLLoader.load(getClass().getResource("/Gui/ServerManage_GUI.fxml"));
			Scene scene = new Scene(search);
			primaryStage.setTitle("HelloBooks Server");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
