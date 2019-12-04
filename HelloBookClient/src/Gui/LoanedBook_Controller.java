package Gui;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.ResourceBundle;

import Gui.model.DataModel;
import book.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoanedBook_Controller extends Base_Controller implements Initializable {

	public Button btn_Back,  btn_Comfirm, btn_Return, btn_LateinReturn;
	public Label lb_WhoBorrorwed, lb_Title, lb_Author, lb_Publisher, lb_BookCondition, lb_FullPrice, lb_SalePrice,
			lb_LendPrice, lb_Introduction;
	private Book book;
	@FXML
	public AnchorPane AnchorPane;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Base start
		this.book = DataModel.book_for_loaned;
		super.base();
		// Base end

		
		lb_WhoBorrorwed.setText(DataModel.who_borrow_book);
		lb_Title.setText(book.getTitle());
		lb_Author.setText(book.getAuther());
		lb_Publisher.setText(book.getPublisher());
		lb_BookCondition.setText(book.getBook_condition());
		lb_FullPrice.setText(book.getFull_price()+"");
		lb_SalePrice.setText(book.getSale_price()+"");
		lb_LendPrice.setText(book.getLend_price()+"");
		lb_Introduction.setText(book.getIntroduction());
		
		
	}

	public void backAction() { //�� ȭ������ 
		AnchorPane root=(AnchorPane) AnchorPane.getScene().getRoot();
		root.getChildren().remove(AnchorPane);
	}

	public void comfirmAction() {
		AnchorPane root=(AnchorPane) AnchorPane.getScene().getRoot();
		root.getChildren().remove(AnchorPane);
	}

	public void returnAction() {		
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "�� å�� �ݳ�ó�� �Ͻðڽ��ϱ�?", ButtonType.YES, ButtonType.NO);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			try {
				PrintWriter pw=new PrintWriter(new OutputStreamWriter(DataModel.socket.getOutputStream(),StandardCharsets.UTF_8));
				pw.println("ReturnBook:"+DataModel.ID+":"+this.book.getBook_num()+":"+this.book.getTitle());//ReturnBook:��û��:å��ȣ,å����
				pw.flush();
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public void lateinreturnAction() { 
		Alert alert = new Alert(Alert.AlertType.WARNING, "�뿩�ڰ� �뿩�Ұ� ���°� �˴ϴ�. \n�뿩�ڸ� �뿩�Ұ� ���·� ����ðڽ��ϱ�?",ButtonType.YES, ButtonType.NO);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			// ���� ����� lb_Rent�� false��
			try {
			PrintWriter pw=new PrintWriter(new OutputStreamWriter(DataModel.socket.getOutputStream(),StandardCharsets.UTF_8));
			pw.println("LateInReturn:"+lb_WhoBorrorwed.getText());//LateInReturn:�뿩�Ұ��� �ٲ� ID
			pw.flush();
			new Alert(Alert.AlertType.INFORMATION, "�뿩�Ұ��� ��������ϴ�.", ButtonType.OK).show();;
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
