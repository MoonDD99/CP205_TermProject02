package Gui;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import Gui.model.DataModel;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Base_Controller { //������ �ʴ� ȭ��

	public Button borrowedButton, wishlistButton, mybooklistButton, mainbutton, logoutButton, myInfoButton,
			searchButton, BooksalesButton;

	public TextField searchField;

	public Label userName, userId, lendOK;

	public ListView listField;
	
	public void base() { //�ʱ�ȭ 
		/* 1.listField�� ��ٱ��Ϻ��� �����ֱ�
		 * 2. userName, userId, lendOK �ʱ�ȭ
		*/
	}

	public void mainAction() throws Exception { // Main����
		try {
			Stage primaryStage = (Stage) mainbutton.getScene().getWindow();
			Parent main = FXMLLoader.load(getClass().getResource("/Gui/Main_GUI.fxml"));
			Scene scene = new Scene(main);
			primaryStage.setTitle("HelloBooks/Main");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logoutAction() throws Exception { // �α׾ƿ� �ؼ� Login����
		try {
			String m="LogOut:"+DataModel.ID;
			DataModel.ID=null;
			PrintWriter pw=new PrintWriter(new OutputStreamWriter(DataModel.socket.getOutputStream(), StandardCharsets.UTF_8),true);
			pw.println(m);
			Stage primaryStage = (Stage) logoutButton.getScene().getWindow();
			Parent search = FXMLLoader.load(getClass().getResource("/Gui/Login_GUI.fxml"));
			Scene scene = new Scene(search);
			primaryStage.setTitle("HelloBooks");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void serachAction() throws Exception { //  searchField���� �޾Ƽ� Search��
		try {
			Stage primaryStage = (Stage) searchButton.getScene().getWindow();
			Parent search = FXMLLoader.load(getClass().getResource("/Gui/Search_GUI.fxml"));
			Scene scene = new Scene(search);
			primaryStage.setTitle("HelloBooks/search");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salesAction() { // UploadBook��

	}

	public void mybooklistAction() { // MyBookList��

	}

	public void myInfoAction() throws Exception { // UserDetail��
		try {
			Stage primaryStage = (Stage) myInfoButton.getScene().getWindow();
			Parent search = FXMLLoader.load(getClass().getResource("/Gui/UserDetail_GUI.fxml"));
			Scene scene = new Scene(search);
			primaryStage.setTitle("HelloBooks/MyInfo");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void wishlistAction() { // listField�� ��ٱ��� ����Ʈ �����ֱ�

	}

	public void borrowButton() { // listField�� ���� å ����Ʈ �����ֱ�

	}

}
