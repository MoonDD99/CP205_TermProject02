package Gui;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Gui.model.DataModel;
import book.Book.HBoxCell;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main_Controller extends Base_Controller implements Initializable {
	private ArrayList<Image> ad_images=new ArrayList<>();
	private Image[] ad_arr;
	@FXML
	public Button btn_Left, btn_Right;
	@FXML
	public ImageView iv_ad;
	@FXML
	public Label lb_adExplain;
	@FXML
	public ListView lv_NewBooks, lv_BestSeller;
	private int ad_count;
	private ObservableList<HBoxCell> ItemList_newBook;



	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Base start

		super.base();

		ItemList_newBook=DataModel.ItemList_newBook;

	 
		for(HBoxCell item:ItemList_newBook) {

			
			item.title.setOnAction(new EventHandler<ActionEvent>() { 
			@Override 
			public void handle(ActionEvent evnet){ 
				try {
					//item.num
					PrintWriter pw=new PrintWriter(new OutputStreamWriter(DataModel.socket.getOutputStream())); 
					pw.println("PrintBookData:"+item.num.getText());
					pw.flush(); //å��ȣ�� ���� ������ �޶�� ��û
					
					Stage primaryStage = (Stage) btn_LogOut.getScene().getWindow();
					Parent search = FXMLLoader.load(getClass().getResource("/Gui/BookDetail_GUI.fxml"));
					Scene scene = new Scene(search);
					primaryStage.setTitle("HelloBooks");
					primaryStage.setScene(scene);
					primaryStage.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}});
		}

		lv_NewBooks.setItems(ItemList_newBook);//�� �Ʒ� ��ġ�� Listner�� �߰������ �� �۵���!!!

	
		lv_NewBooks.getItems().addListener (new ListChangeListener() {//���Ӱ� �߰��ɶ����� ����!!
		    @Override
		    public void onChanged(ListChangeListener.Change change) {
		    	if(ItemList_newBook.size()!=0) {

		    		HBoxCell hbc= (HBoxCell) lv_NewBooks.getItems().get(0);
		    		hbc.title.setOnAction(new EventHandler<ActionEvent>() { 
		    			@Override 
		    			public void handle(ActionEvent evnet){ 
		    				try {
		    					//item.num
		    					PrintWriter pw=new PrintWriter(new OutputStreamWriter(DataModel.socket.getOutputStream())); 
		    					pw.println("PrintBookData:"+hbc.num.getText());
		    					pw.flush(); //å��ȣ�� ���� ������ �޶�� ��û
							
		    					Stage primaryStage = (Stage) btn_LogOut.getScene().getWindow();
		    					Parent search = FXMLLoader.load(getClass().getResource("/Gui/BookDetail_GUI.fxml"));
		    					Scene scene = new Scene(search);
		    					primaryStage.setTitle("HelloBooks");
		    					primaryStage.setScene(scene);
		    					primaryStage.show();
		    				} catch (Exception e) {
		    					e.printStackTrace();
		    				}
		    			}}); 	
		    		}
		    	}
		    });
		

		
		

		
		File dirFile=new File("src\\image\\advertisement");
		File []fileList=dirFile.listFiles();
		
		if(fileList!=null) {
			for(File tempFile : fileList) {

				if(tempFile.isFile()) {
					Image image = new Image(tempFile.toURI().toString());
					ad_images.add(image);
				
				
				}
			}
			iv_ad.setPreserveRatio(false);
			/*
			 * this.ad=new Image[ad_images.size()]; int count=0; for(Image i:ad_images) {
			 * ad[count++]=i; } System.out.println("??");
			 */
			new chageImageThread().start();
			
			
			
		}
	
	}

	private class chageImageThread extends Thread{
		@Override
		public void run() {
			if(ad_images.size()!=0) {

				ad_count=0;
				while(DataModel.socket!=null) {
					if(ad_count>=ad_images.size()-1) {
						ad_count=-1;
					}
					iv_ad.setImage(ad_images.get(++ad_count));
					try {
						sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					
				}
			}
		}
	
	
	
	
	@FXML
	public void goLeftAction() {
		// �������� �̵��ϸ� �ٸ� adPicture �����ֱ�  
		
		if(ad_count<=0) {
			ad_count=ad_images.size();
		}
		iv_ad.setImage(ad_images.get(--ad_count));
	
	
	}

	@FXML
	public void goRightAction() {
		// ���������� �̵��ϸ� �ٸ� adPicture �����ֱ�
		if(ad_count>=ad_images.size()-1) {
			ad_count=-1;
		}
		iv_ad.setImage(ad_images.get(++ad_count));

	}


}
