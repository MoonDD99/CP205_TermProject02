package Gui.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import book.Book;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ClientThread  extends Thread{
        Socket socket = null;

        public ClientThread(Socket socket){//������
            this.socket = socket;
        }
        @Override
        public void run() {
            try {
            	BufferedReader br=null;
            	
            	//br=DataModel.br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            	br= new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            	DataModel.ItemList_newBook=FXCollections.observableArrayList();
             	DataModel.ItemList_myBook=FXCollections.observableArrayList();
             	DataModel.ItemList_searchBook=FXCollections.observableArrayList();
                while(true) {
                    String msg = br.readLine();

                    String[] tokens=msg.split(":");
                    if(tokens[0].equals("NewBook")) {           	
                    	if(!DataModel.exist_new_book) {//���ٰ� ����� ��ϵ� å�� �����ϴٸ� ����ֱ� ����
                    		Platform.runLater(() -> {DataModel.ItemList_newBook.clear();});
                    		//DataModel.ItemList_newBook.clear();
                    		DataModel.exist_new_book=true;
                    	}
                    	
                    	if(tokens[1].equals("[��Ϲ�ȣ")) {
                    		String[] newBookData=new String[11];
                    		newBookData[0]=tokens[2];
                    		for(int i=1; i<11; i++) {
                    			newBookData[i]=tokens[i*2+2];
                    		}
                    		Platform.runLater(() -> { DataModel.addNewBook(new Book(newBookData));});
                    	}
                    	else {
                    		
                    		Platform.runLater(() -> { DataModel.addNewBook(tokens[1]);});//��ϵ� å�� �����ϴٰ� ����
                    		DataModel.exist_new_book=false;
                    	}
                    	
                    }
                    else if (tokens[0].equals("AddBookData")) {
 
                    	Platform.runLater(() -> { Alert alert = new Alert(AlertType.INFORMATION);
                    	alert.setTitle("Success");
                    	alert.setHeaderText("���ε� ����!");
                    	alert.setContentText("Ȯ���� �����ּ���");
                    	alert.show();});

    				}
                    
                    
                    else if (tokens[0].equals("PrintBookList")) {
                    	if(tokens[1].equals("[��Ϲ�ȣ")) {
                    		String[] newBookData=new String[11];
                    		newBookData[0]=tokens[2];
                    		for(int i=1; i<11; i++) {
                    			newBookData[i]=tokens[i*2+2];
                    		}
                    		Platform.runLater(() -> { DataModel.addMyBookList(new Book(newBookData));});
                    	}
                    	else {
                    		Platform.runLater(() -> { DataModel.addMyBookList(tokens[1]);});//��ϵ� å�� �����ϴٰ� ����
                    	}
    				} 
                    
                    
                    else if (tokens[0].equals("PrintBookData")) {//PrintBookData:(Registered,Detail,Loaned):[��Ϲ�ȣ:~~~
                    	if(tokens[1].equals("Detail")) {
                    		if(tokens[2].equals("å�� �������� �ʽ��ϴ�.")) {//å�� ������
                    			DataModel.book_for_detail=null;
                    		}else {
               
                    			String mergeToken="";
                    			for(int i=3; i<tokens.length; i+=2) {//0��printBookData, 1�� [��Ϲ�ȣ, �������� ]��
                    				mergeToken+=tokens[i]+":";
                    			}
                    			DataModel.book_for_detail=new Book(mergeToken);
          
                    		}
                    	}else if(tokens[1].equals("Registered")) {
                    		if(tokens[2].equals("å�� �������� �ʽ��ϴ�.")) {//å�� ������
                    			DataModel.book_for_registered=null;
                    		}else {
               
                    			String mergeToken="";
                    			for(int i=3; i<tokens.length; i+=2) {//0��printBookData, 1�� [��Ϲ�ȣ, �������� ]��
                    				mergeToken+=tokens[i]+":";
                    			}
                    			DataModel.book_for_registered=new Book(mergeToken);
          
                    		}
                    	}
                    	
                    	
                    	
                    	
                    	}
                    
                    
                    else if (tokens[0].equals("SearchBookList")) {
                    	if(tokens[1].equals("[��Ϲ�ȣ")) {
                    		String[] newBookData=new String[11];
                    		newBookData[0]=tokens[2];
                    		for(int i=1; i<11; i++) {
                    			newBookData[i]=tokens[i*2+2];
                    		}
                    		Platform.runLater(() -> { DataModel.addSearchBookList(new Book(newBookData));});
                    	}
                    	else {
                    		Platform.runLater(() -> { DataModel.addSearchBookList(tokens[1]);});//��ϵ� å�� �����ϴٰ� ����
                    	}
    				} 
    				
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (Exception e) {
            	
            }
        }

}
