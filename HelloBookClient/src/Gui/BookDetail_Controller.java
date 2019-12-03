package Gui;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import Gui.model.DataModel;
import book.Book;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

public class BookDetail_Controller extends Base_Controller implements Initializable {

	@FXML
	public Button btn_Back, btn_Borrow, btn_BuyNow;
	@FXML
	public Label lb_MainBookName, lb_MainBookAuthor, lb_RentalStatus, lb_Title, lb_Author, lb_Publisher, lb_BookCondition, lb_FullPrice, lb_SalePrice, lb_LendPrice, lb_Introduction; 
	
	private Book book;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.book=DataModel.book_for_detail;
	
		// Base start
		super.base();
		// Base end
		if(book==null) {
			lb_MainBookName.setText("å�� �������� �ʽ��ϴ�.");
		}else {
			lb_MainBookName.setText(book.getTitle());
			lb_MainBookAuthor.setText(book.getAuther());
			lb_RentalStatus.setText(book.getRental_status()+"");
			lb_Title.setText(book.getTitle());
			lb_Author.setText(book.getAuther());
			lb_Publisher.setText(book.getPublisher());
			lb_BookCondition.setText(book.getBook_condition());
			lb_FullPrice.setText(book.getFull_price()+"");
			lb_SalePrice.setText(book.getSale_price()+"");
			lb_LendPrice.setText(book.getLend_price()+"");
			lb_Introduction.setText(book.getIntroduction());
		}	
	}

	@FXML
	public void backAction() { //�� ȭ������ 

	}

	@FXML
	public void borrowedNowAction() { //�� å�� �ٷ� ������ å���ο��� �˸��� -> å ������ New Alert�� �߱�
		PrintWriter pw;
		try {
			pw = new PrintWriter(new OutputStreamWriter(DataModel.socket.getOutputStream()));
			pw.println("BorrowRequest:"+DataModel.ID+":"+book.getBook_num()+":"+book.getTitle()+":");////BorrowRequest:��û��:å��ȣ:å����
			pw.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}

	@FXML
	public void buyNowAction() { //�� å�� �ٷ� �����ϱ� -> å ������ New Alert�� �߱�

	}
}
