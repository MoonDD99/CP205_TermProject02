package Gui;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Gui.model.DataModel;
import book.Book.HBoxCell;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Search_Controller extends Base_Controller implements Initializable {

	ObservableList<String> genre_list = FXCollections.observableArrayList("���þ���","�ι�/��ȸ", "����/����","����","����","�Ҽ�","��ȭ","��Ÿ");
	ObservableList<String> book_condition_list = FXCollections.observableArrayList("���þ���","��","��","��");
	ObservableList<String> rentalAvailable_list = FXCollections.observableArrayList("���þ���","����","�Ұ���");

	String genre;
	String book_condition;
	String rentalAvailable;
	
	@FXML
	public ChoiceBox cb_genre, cb_BookCondition, cb_RentalStatus, cb_About;
	@FXML
	public ListView lv_BookList;
	@FXML
	public Button btn_DetailSearch;
	@FXML
	public TextField tf_Title, tf_Author , tf_Publisher;
	
	private ObservableList<HBoxCell> ItemList_searchBook;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Base start
		super.base();
		// Base end
		
		cb_genre.setValue("���þ���");
		genre="���þ���";
		cb_genre.setItems(genre_list);
		
		cb_BookCondition.setValue("���þ���");
		book_condition="���þ���";
		cb_BookCondition.setItems(book_condition_list);
		
		cb_RentalStatus.setValue("���þ���");
		rentalAvailable="���þ���";
		cb_RentalStatus.setItems(rentalAvailable_list);
		
		
		cb_genre.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { //���� ���� !!!
			 
            // if the item of the list is changed 
            public void changed(ObservableValue ov, Number value, Number new_value) 
            { 
            	genre=genre_list.get(new_value.intValue()).toString();
            }
        }); 
		
		cb_BookCondition.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { //���� ���� !!!
			 
            // if the item of the list is changed 
            public void changed(ObservableValue ov, Number value, Number new_value) 
            { 
            	book_condition=book_condition_list.get(new_value.intValue()).toString();
            }
        }); 
		
		cb_RentalStatus.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { //���� ���� !!!
			 
            // if the item of the list is changed 
            public void changed(ObservableValue ov, Number value, Number new_value) 
            { 
            	rentalAvailable=rentalAvailable_list.get(new_value.intValue()).toString();
            }
        }); 
		
		if(DataModel.ItemList_searchBook!=null) {
			DataModel.ItemList_searchBook.clear();
		}
		
		
		ItemList_searchBook=DataModel.ItemList_searchBook;
		
		
		
		lv_BookList.setItems(ItemList_searchBook);
		
		
		
		
		
		
		
	}
	@FXML
	public void DetailSearchAction() {
		
		PrintWriter pw;
		try {
			pw = new PrintWriter(new OutputStreamWriter(DataModel.socket.getOutputStream(), StandardCharsets.UTF_8), true);
			ArrayList<String> ms=new ArrayList<>();//0-����, 1-�۰�, 2-���ǻ�, 3-�帣, 4-�뿩���ɼ�
				if(tf_Title.getText().contains("-")||tf_Author.getText().contains("-")||tf_Publisher.getText().contains("-")) {
					new Alert(Alert.AlertType.WARNING, "Ư������'-'�� ����Ͻ� �� �����ϴ�.", ButtonType.CLOSE).show();
				}
				else if(tf_Title.getText().contains(":")||tf_Author.getText().contains(":")||tf_Publisher.getText().contains(":")) {
					new Alert(Alert.AlertType.WARNING, "Ư������':'�� ����Ͻ� �� �����ϴ�.", ButtonType.CLOSE).show();
				}
				else {
					
				
					if(tf_Title.getText().length()!=0) ms.add("Title-"+tf_Title.getText());
					if(tf_Author.getText().length()!=0) ms.add("Auther-"+tf_Author.getText());
					if(tf_Publisher.getText().length()!=0) ms.add("Publisher-"+tf_Publisher.getText());
					if(!genre.equals("���þ���")) ms.add("Genre-"+genre);//���߿� �ٲ�� ��
					if(!book_condition.equals("���þ���")) ms.add("book_condition-"+book_condition);//���߿� �ٲ�� ��
					
					if(!rentalAvailable.equals("���þ���")) {
						if(rentalAvailable.equals("����")) {
							ms.add("Rental_Status-1");
						}else {
							ms.add("Rental_Status-0");//�Ұ���
						}
					}
			
			
			
				String request="SearchBook:";
				for(int i=0; i<ms.size(); i++) {
					if(i==0) {
						request+=ms.get(i);
					}else  {
						request+=":"+ms.get(i);
					}
				
				}
			
				if(DataModel.ItemList_searchBook!=null) {
					DataModel.ItemList_searchBook.clear();
				}
				pw.println(request);
				pw.flush();
			
				btn_DetailSearch.setDisable(true);
				Thread.sleep(500);
				btn_DetailSearch.setDisable(false);
			}
		}catch(IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
}
