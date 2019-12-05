package Gui.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import Gui.Base_Controller;
import alter.UserAlter;
import book.Book;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import user.User;

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
             	DataModel.ItemList_alter=FXCollections.observableArrayList();
             	
                while(true) {
                    String msg = br.readLine();
                    String[] tokens=msg.split(":");
                    if(tokens[0].equals("NewBook")) {           	
                    	if(!DataModel.is_exist_new_book) {//���ٰ� ����� ��ϵ� å�� �����ϴٸ� ����ֱ� ����
                    		Platform.runLater(() -> {DataModel.ItemList_newBook.clear();});
                    		DataModel.is_exist_new_book=true;
                    	}
                    	
                    	if(tokens[1].equals("��Ϲ�ȣ")) {//å�� �����ϴ� ��Ȳ
                    		String[] newBookData=new String[11];
                    		newBookData[0]=tokens[2];
                    		
                    		for(int i=1; i<11; i++) {newBookData[i]=tokens[i*2+2];}
                    		
                    		Platform.runLater(() -> { DataModel.addNewBook(new Book(newBookData));});
                    	}
                    	else {//å�� �������� �ʴ� ��Ȳ
                    		Platform.runLater(() -> { DataModel.NoNewBook(tokens[1]);});//��ϵ� å�� �����ϴٰ� ����
                    		DataModel.is_exist_new_book=false;
                    	}
                    	
                    	//NewBook end
                    	
                    }else  if(tokens[0].equals("RemoveNewBook")) {           	

                    		
                    	Platform.runLater(() -> { DataModel.removeNewBook(tokens[1]);});
           
                    	
                    	//NewBook end
                    	
                    } else if (tokens[0].equals("AddBookData")) {
 
                    	Platform.runLater(() -> { Alert alert = new Alert(AlertType.INFORMATION);
                    	alert.setTitle("Success");
                    	alert.setHeaderText("���ε� ����!");
                    	alert.setContentText("Ȯ���� �����ּ���");
                    	alert.show();});

                    	//AddBookData end
                    	
    				} else if (tokens[0].equals("PrintBookList")) {
                    	if(tokens[1].equals("��Ϲ�ȣ")) {
                    		String[] newBookData=new String[11];
                    		newBookData[0]=tokens[2];
                    		for(int i=1; i<11; i++) 		{newBookData[i]=tokens[i*2+2];}
                    		Platform.runLater(() -> { DataModel.addMyBookList(new Book(newBookData));});
                    	}
                    	else 
                    		Platform.runLater(() -> { DataModel.NoMyBookList(tokens[1]);});//��ϵ� å�� �����ϴٰ� ����
                    	
                    	//PrintBookList end
    				} else if (tokens[0].equals("PrintBookData")) {//PrintBookData:(Registered,Detail,Loaned):[��Ϲ�ȣ:~~~
    							
                    	if(tokens[1].equals("Detail")) {
                    		if(tokens[2].equals("å�� �������� �ʽ��ϴ�.")) 
                    			DataModel.book_for_detail=null;
                    		else {
                    			String mergeToken="";
                    			for(int i=3; i<tokens.length; i+=2) 		{mergeToken+=tokens[i]+":";}//tokens[0]��printBookData,tokens[1] �� [��Ϲ�ȣ, �������� ]��
                    			DataModel.book_for_detail=new Book(mergeToken);
                    			System.out.println(tokens.length);
                    			System.out.println(tokens[tokens.length-1]);
                    			 if(tokens.length==25) {//�������� �������� �������� ���ȴ��� ���� 25
                    				 DataModel.borrowed_form_who=tokens[24];
                    			}
                    		}                    		
                    	}
                    	else if(tokens[1].equals("Registered")) {
                    		if(tokens[2].equals("å�� �������� �ʽ��ϴ�.")) 
                    			DataModel.book_for_registered=null;
                    		else {
                    			String mergeToken="";
                    			for(int i=3; i<tokens.length; i+=2)		 {mergeToken+=tokens[i]+":";}
                    			DataModel.book_for_registered=new Book(mergeToken);
                    	
                    			 if(tokens.length==25) {//���������� �������� �������� ��������� ���� 25
                    				 DataModel.who_borrwed_book=tokens[24];
                    			}
                    		}
                    	}
                    	else if(tokens[1].equals("Loaned")) {
                    		if(tokens[2].equals("å�� �������� �ʽ��ϴ�.")) //å�� ������
                    			DataModel.book_for_loaned=null;
                    		else {
                    			String mergeToken="";
                    			for(int i=3; i<tokens.length; i+=2) {mergeToken+=tokens[i]+":";}//0��printBookData, 1�� [��Ϲ�ȣ, �������� ]��
                    			DataModel.book_for_loaned=new Book(mergeToken);
                    			DataModel.who_borrwed_book=tokens[24];
                    		}
                    	}

                    	//PrintBookData end
                    } 
                    else if (tokens[0].equals("SearchBookList")) {
                    	if(tokens[1].equals("��Ϲ�ȣ")) {
                    		String[] newBookData=new String[11];
                    		newBookData[0]=tokens[2];
                    		for(int i=1; i<11; i++) 		{newBookData[i]=tokens[i*2+2];}
                    		
                    		Platform.runLater(() -> { DataModel.addSearchBookList(new Book(newBookData));});
                    	}
                    	else 
                    		Platform.runLater(() -> { DataModel.NoSearchBookList(tokens[1]);});//��ϵ� å�� �����ϴٰ� ����
                    	
                    	//SearchBookList end
    				} 

                    else if (tokens[0].equals("Alter")) {
                    	String[] alterData=new String[6];
                    	alterData[0]=tokens[1];
                		for(int i=1; i<6; i++) 		{alterData[i]=tokens[i+1];}
                		
                    	UserAlter user_alter=new UserAlter(alterData);
                    	Platform.runLater(() -> { DataModel.addAlter(user_alter);});
                    	
                    	//Alter end
    				}
                    
                    
                    else if (tokens[0].equals("BorrowRequest")) {
                    	Platform.runLater(() -> { 	new Alert(Alert.AlertType.INFORMATION, tokens[1], ButtonType.CLOSE).show();});//�뿩��û�� ���½��ϴ� 	
                    	//BorrowRequest end
    				}
                    
                    else if (tokens[0].equals("ReturnBook")) {
                    	Platform.runLater(() -> { 	new Alert(Alert.AlertType.INFORMATION, tokens[1], ButtonType.CLOSE).show();});
                    	//ReturnBook end
    				}
                    else if (tokens[0].equals("PurchaseRequest")) {
                    	Platform.runLater(() -> { 	new Alert(Alert.AlertType.INFORMATION, tokens[1], ButtonType.CLOSE).show();}); //���ſ�û�� ���½��ϴ� 	             	
                    	//PurchaseRequest end
    				}else if (tokens[0].equals("ModifyUserData")) {
    					if(tokens[1].equals("ȸ�������� ����Ǿ����ϴ�.")) {
    						Platform.runLater(() -> { 	new Alert(Alert.AlertType.INFORMATION, tokens[1], ButtonType.CLOSE).show();}); 
    						DataModel.user=new User(tokens[2]);
    					}else {
    						Platform.runLater(() -> { 	new Alert(Alert.AlertType.INFORMATION, tokens[1], ButtonType.CLOSE).show();}); 
    					}
                    	//ModifyUserData end
    				}  else if (tokens[0].equals("RemoveBookData")) {
                    	Platform.runLater(() -> { 	new Alert(Alert.AlertType.INFORMATION, tokens[1], ButtonType.CLOSE).show();}); //���ſ�û�� ���½��ϴ� 	             	
                    	//RemoveBookData end
    				} 
    				else if (tokens[0].equals("LateInReturn")) {
                    	if(tokens[1].equals("Good")) {//�뿩�������� �ٲ��
                    		DataModel.user.setLend_OK(true);
                    		Platform.runLater(() -> { 	new Alert(Alert.AlertType.INFORMATION, "å�� �ݳ��Ǿ� �뿩���� ���°� �Ǿ����ϴ�.", ButtonType.CLOSE).show();}); //���ſ�û�� ���½��ϴ� 	         
                    	}else {
                    		DataModel.user.setLend_OK(false);
                    		Platform.runLater(() -> { 	new Alert(Alert.AlertType.INFORMATION, "å�� �ݳ����� �ʾ� �뿩�Ұ� ���°� �Ǿ����ϴ�", ButtonType.CLOSE).show();}); //���ſ�û�� ���½��ϴ� 	         
                    	}
                    	
                    	//lateInReturn e d
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
