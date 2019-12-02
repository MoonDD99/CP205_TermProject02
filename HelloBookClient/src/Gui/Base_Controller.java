package Gui;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import Gui.model.DataModel;
import alter.UserAlter;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Base_Controller { // ������ �ʴ� ȭ�� = Base

	/*
	 * lb = label btn = button tf = textfield ts = textArea lv = listview
	 * 
	 */
	public Button btn_Main, btn_Search, btn_BookSales, btn_LogOut, btn_MyInfo, btn_MyBookList, btn_ProfileWishList,
	btn_ProfileNewAlert;

	public TextField tf_Search;

	public Label lb_ProfileName, lb_ProfileID, lb_ProfileLend;

	public ListView lv_ProfileList;
	
	private ObservableList<UserAlter> ItemList_alter;
	
	@SuppressWarnings("unchecked")
	public void base() {
		
		// Profile start
		lb_ProfileName.setText(DataModel.user.getName());
		lb_ProfileID.setText(DataModel.user.getID());
		if (DataModel.user.isLend_OK()) {
			lb_ProfileLend.setText("�뿩 ����");
		} else {
			lb_ProfileLend.setText("�뿩 �Ұ�");
		}
		// Profile end

		// ProfileList �ʱ�ȭ start
		// ProfileList �ʱ�ȭ end
		
		this.ItemList_alter=DataModel.ItemList_alter;
		lv_ProfileList.setItems(ItemList_alter);

		lv_ProfileList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<UserAlter>() {

		    @Override
		    public void changed(ObservableValue<? extends UserAlter> observable, UserAlter oldValue, UserAlter newValue) {
		        // Your action here
		    	if(newValue!=null) {
		    		if(newValue.getRequest_Status().equals("������")) {
		    			Alert alert = new Alert(Alert.AlertType.WARNING, lv_ProfileList.getSelectionModel().getSelectedItem().toString(), ButtonType.YES, ButtonType.NO);
		    			Optional<ButtonType> result = alert.showAndWait();
		    			if (result.get() == ButtonType.YES) {
		    				// ��ϵ� å�� ����
		    				try {
		    					PrintWriter pw=new PrintWriter(new OutputStreamWriter(DataModel.socket.getOutputStream(),StandardCharsets.UTF_8));//BorrowAnswer:(����,�ź�):��û��:å��ȣ:å����:��û�޴���

		    					pw.println("BorrowAnswer:����:"+newValue.getRequested_ID()+":"+newValue.getBook_Number()+":"+newValue.getBook_Title()+":"+newValue.getRequester_ID());
		    					pw.flush();
		    					int index=lv_ProfileList.getSelectionModel().getSelectedIndex();
		    					Platform.runLater(() -> {ItemList_alter.remove(index);});
					
		    				} catch (IOException e) {
		    					// TODO Auto-generated catch block
		    					e.printStackTrace();
		    				}
					
		    			}else {
		    			
		    				try {
								PrintWriter pw= new PrintWriter(new OutputStreamWriter(DataModel.socket.getOutputStream(),StandardCharsets.UTF_8));
								pw.println("BorrowAnswer:����:"+newValue.getRequested_ID()+":"+newValue.getBook_Number()+":"+newValue.getBook_Title()+":"+newValue.getRequester_ID());
		    					pw.flush();
								int index=lv_ProfileList.getSelectionModel().getSelectedIndex();
								Platform.runLater(() -> {ItemList_alter.remove(index);});
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}//BorrowAnswer:(����,�ź�):��û��:å��ȣ:å����:��û�޴���

	    				
		    			}
		    		}else if(newValue.getRequest_Status().equals("�����ִ�")||newValue.getRequest_Status().equals("�Ⱥ����ִ�")){
		    			Alert alert = new Alert(Alert.AlertType.WARNING, lv_ProfileList.getSelectionModel().getSelectedItem().toString(), ButtonType.YES);
		    			alert.show();
		    			
						try {
							PrintWriter pw = new PrintWriter(new OutputStreamWriter(DataModel.socket.getOutputStream(),StandardCharsets.UTF_8));
							pw.println("AlterOK:"+newValue.getRequested_ID()+":"+newValue.getBook_Number());//AlterOK:��û��:å��ȣ
	    					pw.flush();
	    					System.out.println("AlterOK:"+newValue.getRequested_ID()+":"+newValue.getBook_Number());
							int index=lv_ProfileList.getSelectionModel().getSelectedIndex();
							Platform.runLater(() -> {ItemList_alter.remove(index);});
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
		    		}
		    	}
		    }
		});
	}

	public void mainAction() throws Exception {
		// To Main_GUI
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

	public void logoutAction() throws Exception {

		//Logout start
		LogOut();
		//Logout end

		//To Login_GUI
		try {
			Stage primaryStage = (Stage) btn_LogOut.getScene().getWindow();
			Parent search = FXMLLoader.load(getClass().getResource("/Gui/Login_GUI.fxml"));
			Scene scene = new Scene(search);
			primaryStage.setTitle("HelloBooks");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void CloseButtonActione() {
		LogOut();
		System.exit(0);//�̰� �� ���ָ�, �α��� ��ư ������ ����ȭ�� �߱� ���� X��ư�� ������, �α׾ƿ��� �ʰ� ���ÿ� Main ȭ���� �� ������ �߻��Ѵ�.!
	}
	public static void LogOut() {
		if(DataModel.socket!=null) {
			String m = "LogOut:" + DataModel.ID;
			DataModel.ID = null;
			PrintWriter pw;
			try {
				pw = new PrintWriter(
						new OutputStreamWriter(DataModel.socket.getOutputStream(), StandardCharsets.UTF_8), true);
				pw.println(m);
				pw.flush();
				DataModel.socket=null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void searchAction() throws Exception { 
		//tf_Search ���� �ޱ� start
		//tf_Search ���� �ޱ� end
		
		//To Search_Gui
		try {
			Stage primaryStage = (Stage) btn_Search.getScene().getWindow();
			Parent search = FXMLLoader.load(getClass().getResource("/Gui/Search_GUI.fxml"));
			Scene scene = new Scene(search);
			primaryStage.setTitle("HelloBooks/search");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salesAction() { 
		// To UploadBook_GUI
		try {
			Stage primaryStage = (Stage) btn_Search.getScene().getWindow();
			Parent search = FXMLLoader.load(getClass().getResource("/Gui/UploadBook_GUI.fxml"));
			Scene scene = new Scene(search);
			primaryStage.setTitle("HelloBooks/UploadBook");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mybooklistAction() { 
		// To MyBookList_GUI
		try {
			Stage primaryStage = (Stage) btn_MyInfo.getScene().getWindow();
			Parent search = FXMLLoader.load(getClass().getResource("/Gui/MyBookList_GUI.fxml"));
			Scene scene = new Scene(search);
			primaryStage.setTitle("HelloBooks/MyBookList");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	public void myInfoAction() throws Exception { 
		// To UserDetail_GUI
		try {
			Stage primaryStage = (Stage) btn_MyInfo.getScene().getWindow();
			Parent search = FXMLLoader.load(getClass().getResource("/Gui/UserDetail_GUI.fxml"));
			Scene scene = new Scene(search);
			primaryStage.setTitle("HelloBooks/MyInfo");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void wishlistAction() { 
		// lv_ProfileList�� ��ٱ��� ����Ʈ �����ֱ�
	}

	public void alertAction() { 
		// lv_ProfileList�� ���ο� �˶� �����ֱ�
		//Alert�� �־�� �ϴ� �� : �Ǹ��Ѵٰ� �ϴ� ���� ID, btn_���� �����ϱ� ��ư, btn_��å���� �ٷΰ���
	}

}
