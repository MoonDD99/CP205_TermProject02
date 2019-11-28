package Gui;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import Gui.model.DataModel;
import book.Book;
import book.Book.HBoxCell;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class MyBookList_Controller extends Base_Controller implements Initializable {

	@FXML
	public Button btn_LoanedBook, btn_BorrowedBook, btn_RegisteredBook, btn_SoldBook, btn_Back;
	@FXML
	public ListView lv_MybooklistField;
	private Socket socket;

	private ObservableList<Object> ItemList_myBook;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DataModel.ItemList_myBook.clear();
		// Base start
		super.base();
		// Base end
		
		//lv print start
		ItemList_myBook = DataModel.ItemList_myBook;
		lv_MybooklistField.setItems(ItemList_myBook);
		//lv print end
	}

	public void showborrowedAction() { // ���� å MybooklistField�� ������ LoanedBook ��ü ������
		// Button �� ��ȭ start
		btn_BorrowedBook.setStyle("-fx-background-color: #6464CD");
		btn_LoanedBook.setStyle("-fx-background-color: #3065AC");
		btn_RegisteredBook.setStyle("-fx-background-color: #3065AC");
		btn_SoldBook.setStyle("-fx-background-color: #3065AC");
		// Button �� ��ȭ end
		DataModel.ItemList_myBook.clear();
	}

	public void showloanedAction() { // ������ å MybooklistField�� ������ registeredBook �� lend �� å
		// Button �� ��ȭ start
		btn_LoanedBook.setStyle("-fx-background-color: #6464CD");
		btn_BorrowedBook.setStyle("-fx-background-color: #3065AC");
		btn_RegisteredBook.setStyle("-fx-background-color: #3065AC");
		btn_SoldBook.setStyle("-fx-background-color: #3065AC");
		// Button �� ��ȭ end
		DataModel.ItemList_myBook.clear();
	}

	public void showregisteredAction() { // ����� å MybooklistField�� ������ registeredBook ��ü ������
		// Button �� ��ȭ start
		btn_RegisteredBook.setStyle("-fx-background-color: #6464CD");
		btn_BorrowedBook.setStyle("-fx-background-color: #3065AC");
		btn_LoanedBook.setStyle("-fx-background-color: #3065AC");
		btn_SoldBook.setStyle("-fx-background-color: #3065AC");
		// Button �� ��ȭ end

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

	}

	public void showsoldAction() { // �ȸ� å MybooklistField�� ������ registeredBook �� soldout�� å
		// Button �� ��ȭ start
		btn_SoldBook.setStyle("-fx-background-color: #6464CD");
		btn_BorrowedBook.setStyle("-fx-background-color: #3065AC");
		btn_RegisteredBook.setStyle("-fx-background-color: #3065AC");
		btn_LoanedBook.setStyle("-fx-background-color: #3065AC");
		// Button �� ��ȭ end

		DataModel.ItemList_myBook.clear();
	}

	public void backAction() { // �� ȭ������

	}

}
