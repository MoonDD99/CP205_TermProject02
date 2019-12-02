package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import book.Book;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;

public class LibraryServer {
	
	private final static int PORT=26432;
	public static void main(String[] args) {
		Map<String, String> client_id_ip=new HashMap<>();
		Map<String,PrintWriter> listUser= new HashMap<String,PrintWriter>();
		List<Book> new_book_list=new LinkedList<Book>();
		
		try {
			
			ServerSocket server=new ServerSocket();
			//String hostAddress = "0.0.0.0";
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			
			server.bind(new InetSocketAddress(hostAddress, PORT));
			System.out.println(server);
			System.out.println("Library Server Start!! "+"[Server IP:"+ server.getInetAddress()+"] [Server Port: "+PORT+"]");
			
			while(true) {
				Socket c_socket=server.accept();
				
				
			
				System.out.println(c_socket.getInetAddress().toString()+"���� ���� ��û");
				new LibraryServerThread(c_socket, client_id_ip,listUser,new_book_list).start();;
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendImage(Socket socket) {
		
		
		try {
			File[] files = new File("src/server/image").listFiles(); // path��ο��ִ� ���� ��� �о���Ӵϴ�.

			DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			dos.writeInt(files.length);//���ϰ��� ����

		try { 
			for(int i=0;i<files.length;i++){   
				
				
				  //���� �̸��� ����!

				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(files[i]));
				int bytes =0;
		
				
				dos.writeLong(files[i].length()); //����ũ�⺸����
				
				int lastDataSize=(int) (files[i].length()%4096);
				int totalReadCount=(int) (files[i].length()/4096);
				byte[] buf = new byte[4096]; //buf �����մϴ�.
				int theByte = 0;
				
				for(int k=0; k<totalReadCount; k++) {
					if ((theByte = bis.read(buf)) != -1) // BufferedInputStream����
						// Ŭ���̾�Ʈ�� ������ ���� write�մϴ�.
					{
						dos.write(buf,0,theByte);
					}

				}
				if(lastDataSize>0) {
					if ((theByte = bis.read(buf)) != -1) 
					{
						dos.write(buf,0,theByte);
					}

				}
				
				Thread.sleep(100);


				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}
 

	
	
}
