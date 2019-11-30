package Gui;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import Gui.model.DataModel;
import book.Book.HBoxCell;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class MyBookList_Controller extends Base_Controller implements Initializable {

	@FXML
	public Button btn_LoanedBook, btn_BorrowedBook, btn_RegisteredBook, btn_SoldBook, btn_Back;
	@FXML
	public ListView<HBoxCell> lv_MybooklistField;
	private ObservableList<HBoxCell> ItemList_myBook;
	private Socket socket;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DataModel.ItemList_myBook.clear();
		// Base start
		super.base();
		// Base end
		ItemList_myBook = DataModel.ItemList_myBook;
		lv_MybooklistField.setItems(ItemList_myBook);
	}

	public void showborrowedAction() { // ���� å MybooklistField�� ������ LoanedBook ��ü ������
		DataModel.ItemList_myBook.clear();
	}

	public void showloanedAction() { // ������ å MybooklistField�� ������ registeredBook �� lend �� å
		DataModel.ItemList_myBook.clear();
	}

	public void showregisteredAction() { // ����� å MybooklistField�� ������ registeredBook ��ü ������

		// ����Ŭ�� ������ ���� ����..

		DataModel.ItemList_myBook.clear();
		socket = DataModel.socket;

		PrintWriter pw;
		try {
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			pw.println("PrintBookList:" + DataModel.ID + ":Registered");
			pw.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {

			Thread.sleep(1000);

		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Platform.runLater(() -> {
			for (HBoxCell item : ItemList_myBook) {

				item.title.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent evnet) {
						try {
							// item.num
							PrintWriter pw = new PrintWriter(
									new OutputStreamWriter(DataModel.socket.getOutputStream()));
							pw.println("PrintBookData:Registered:" + item.num.getText());
							pw.flush(); // å��ȣ�� ���� ������ �޶�� ��û

							Stage primaryStage = (Stage) btn_LogOut.getScene().getWindow();
							Parent search = FXMLLoader.load(getClass().getResource("/Gui/RegisteredBook_GUI.fxml"));
							Scene scene = new Scene(search);
							primaryStage.setTitle("HelloBooks");
							primaryStage.setScene(scene);
							primaryStage.show();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

	}

	public void showsoldAction() { // �ȸ� å MybooklistField�� ������ registeredBook �� soldout�� å
		DataModel.ItemList_myBook.clear();
	}

	public void backAction() {
		try {
			Stage primaryStage = (Stage) btn_Main.getScene().getWindow();
			Parent main = FXMLLoader.load(getClass().getResource("/Gui/Main_GUI.fxml"));
			Scene scene = new Scene(main);
			primaryStage.setTitle("HelloBooks/Main");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
