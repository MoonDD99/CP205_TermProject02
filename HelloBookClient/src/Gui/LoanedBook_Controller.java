package Gui;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Gui.model.DataModel;
import book.Book;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LoanedBook_Controller extends Base_Controller implements Initializable {

	public Button btn_Back, btn_Remove, btn_Comfirm, btn_Return, btn_LateinReturn;
	public Label lb_WhoBorrorwed, lb_Title, lb_Author, lb_Publisher, lb_BookCondition, lb_FullPrice, lb_SalePrice,
			lb_LendPrice, lb_Introduction;
	private Book book;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Base start
		this.book = DataModel.book_for_loaned;
		super.base();
		// Base end
		lb_Title.setText(book.getTitle());
		lb_Author.setText(book.getAuther());
		lb_Publisher.setText(book.getPublisher());
		lb_BookCondition.setText(book.getBook_condition());
		lb_FullPrice.setText(book.getFull_price()+"");
		lb_SalePrice.setText(book.getSale_price()+"");
		lb_LendPrice.setText(book.getLend_price()+"");
		lb_Introduction.setText(book.getIntroduction());
		
		
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

	public void removeAction() {
		Alert alert = new Alert(Alert.AlertType.WARNING, "�� å�� ���� �ݳ�ó���� ���� �ʾҽ��ϴ�.\n�� å�� ������ �����Ͻðڽ��ϱ�?", ButtonType.YES, ButtonType.CANCEL);
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.YES) {
			// ��ϵ� å�� ����
		}
	}

	public void comfirmAction() {
		backAction();
	}

	public void returnAction() {		
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "�� å�� �ݳ�ó�� �Ͻðڽ��ϱ�?", ButtonType.YES, ButtonType.CANCEL);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			// �ݳ�Ȯ��, lb_RentalStatus�� true��
			// ����Ʈ������ ����
		}
	}

	public void lateinreturnAction() { 
		Alert alert = new Alert(Alert.AlertType.WARNING, "�뿩�ڰ� �뿩�Ұ� ���°� �˴ϴ�. \n�뿩�ڸ� �뿩�Ұ� ���·� ����ðڽ��ϱ�?", ButtonType.APPLY, ButtonType.CANCEL);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.APPLY) {
			// ���� ����� lb_Rent�� false��
		}
	}
}
