package Gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import Gui.model.ClientThread;
import Gui.model.DataModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import user.User;

public class Login_Controller implements Initializable {
	@FXML
	public Label lb_error;
	@FXML
	public TextField tf_ID;
	@FXML
	public PasswordField pf_Password;
	@FXML
	public Button btn_Login, btn_SignUp;

	public Socket socket;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		socket = new Socket();
		try {
			socket.connect(new InetSocketAddress(DataModel.SERVER_IP, DataModel.PORT));
			DataModel.socket = socket;

		} catch (IOException e) {
			//if (e.getMessage().equals("Connection refused: connect")) {
			//	lb_error.setText("������ ������� ����");
				lb_error.setText(e.getMessage());
				btn_Login.setDisable(true);
				btn_SignUp.setDisable(true);
			//}
		}
	}

	@FXML
	public void loginAction() {
		// Login start
		// ID�� Password�� ���� �ϳ��� �Է����� ���� ��
		if (tf_ID.getText().length() == 0 || pf_Password.getText().length() == 0) {
			new Alert(Alert.AlertType.WARNING, "��ĭ�� ��� ä���ּ���", ButtonType.CLOSE).show();
		} else {

			// ID�� Password�� �Ѵ� �Է����� ��
			String message = null;
			try {
				String m = "LogIn:" + tf_ID.getText() + ":" + pf_Password.getText();
				BufferedReader br = new BufferedReader(
						new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
				PrintWriter pw = new PrintWriter(
						new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
				pw.println(m);
				pw.flush();
				message = br.readLine();

			} catch (IOException e1) {
				e1.printStackTrace();
			}
			String[] tokens = message.split(":");
			if (tokens[0].equals("LogIn")) {
				if (!tokens[1].equals("����")) {
					// ������ �ƴ� �� token[1]�� �����޼���
					new Alert(Alert.AlertType.WARNING, tokens[1], ButtonType.CLOSE).show();
				} else {
					// �α��ο� �����ϸ� Main����
					try {
						DataModel.user = new User(tokens[2]);
						DataModel.ID = tf_ID.getText();
						new ClientThread(DataModel.socket).start();
						
						Thread.sleep(500);
						
						Stage primaryStage = (Stage) btn_Login.getScene().getWindow();
						Parent login = FXMLLoader.load(getClass().getResource("/Gui/Main_GUI.fxml"));
						Scene scene = new Scene(login);
						primaryStage.setTitle("HelloBooks/Main");
						primaryStage.setScene(scene);
						primaryStage.show();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		// Login end
	}

	@FXML
	public void signUpAction() throws Exception {
		// To SignUp_GUI
		try {
			Stage primaryStage = (Stage) btn_SignUp.getScene().getWindow();
			Parent login = FXMLLoader.load(getClass().getResource("/Gui/SignUp_GUI.fxml"));
			Scene scene = new Scene(login);
			primaryStage.setTitle("HelloBooks/signup");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
