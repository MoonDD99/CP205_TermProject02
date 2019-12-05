package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import book.Book;

public class DB_UsersBook extends DBManager{

	public synchronized static void uploadBook(String ID, String title) throws SQLException {//���� 
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = getConn();

			String sql;
			sql = "INSERT INTO usersbook (ID,Book,status)VALUES (?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, ID);
			pstmt.setString(2, title);
			pstmt.setString(3, "Registered" );

			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} finally {	
			try {
				if (pstmt != null)	pstmt.close();
				if (conn != null)	conn.close();
			} catch (SQLException e) {}
		}
	}
	
	public synchronized static String searchRegisterByNum(int Book_Number) {//����ѻ�� ã��

		Connection conn = null;
		Statement state = null;
		ResultSet rs =null;
		try {
			conn = getConn();
			state = conn.createStatement();// conn���������� state�� ����.
			

			String sql;
			sql = "SELECT *FROM usersbook WHERE Num='" +Book_Number+ "'";
			
			rs = state.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID");
			}else {
				return null;
			}


		} catch (Exception e) {
			return null;
		} finally {	
			try {
				if (state != null)	state.close();
				if (conn != null)	conn.close();
				if(rs!=null)	rs.close();
			} catch (SQLException e) {}
		}
	}
	public synchronized static String searchRequesterByNum(int Book_Number) {//��û������� ã��

		Connection conn = null;
		Statement state = null;
		ResultSet rs =null;
		try {
			conn = getConn();
			state = conn.createStatement();// conn���������� state�� ����.
			

			String sql;
			sql = "SELECT *FROM usersbook WHERE Num='" +Book_Number+ "'";
			
			rs = state.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("Requester_ID");
			}else {
				return null;
			}


		} catch (Exception e) {
			return null;
		} finally {	
			try {
				if (state != null)	state.close();
				if (conn != null)	conn.close();
				if(rs!=null)	rs.close();
			} catch (SQLException e) {}
		}
	}
	
	public synchronized static String CheckRequest(int Book_Number) {//��û�� ������ �� ���ް�

		Connection conn = null;
		Statement state = null;
		ResultSet rs =null;


		try {

			conn = getConn();

			state = conn.createStatement();// conn���������� state�� ����.
			

			String sql;
			sql = "SELECT *FROM usersbook WHERE Num='" +Book_Number+ "'";
			
			rs = state.executeQuery(sql);

			if (rs.next()) {

				return rs.getString("Requester_ID");
			}else {
				return null;
			}


		} catch (Exception e) {
			return null;
		} finally {	
			try {
				if (state != null)	state.close();
				if (conn != null)	conn.close();
				if(rs!=null)	rs.close();
			} catch (SQLException e) {}
		}
	}
	
	public synchronized static List<Integer> getRegisteredBookNumber(String ID) throws SQLException {//���� 
		Connection conn = null;
		Statement state = null;
		ResultSet rs =null;

		try {
			conn = getConn();
			state = conn.createStatement();// conn���������� state�� ����.
			String sql;
			sql = "SELECT *FROM usersbook WHERE id='" +ID+ "' AND (STATUS= 'Registered' OR STATUS='Borrowed')";
			List<Integer> book_num_list=new ArrayList<>();
			rs = state.executeQuery(sql);
			if(rs==null) {
				return null;//���� X
			}
			while (rs.next()) {//���߿� �ٲ�
				book_num_list.add(Integer.parseInt(rs.getString("Num")));


			}
			return book_num_list;		
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}  finally {	
			try {
				if (state != null)	state.close();
				if (conn != null)	conn.close();
				if(rs!=null)	rs.close();
			} catch (SQLException e) {}
		}
	}
	public synchronized static List<Integer> getBorrowedBookNumber(String ID) throws SQLException {//���� 
		Connection conn = null;
		Statement state = null;
		ResultSet rs =null;

		try {
			conn = getConn();
			state = conn.createStatement();// conn���������� state�� ����.
			String sql;
			sql = "SELECT *FROM usersbook WHERE Requester_ID='" +ID+ "' AND STATUS= 'Borrowed'";
			List<Integer> book_num_list=new ArrayList<>();
			rs = state.executeQuery(sql);
			if(rs==null) {
				return null;//���� X
			}
			while (rs.next()) {//���߿� �ٲ�
				book_num_list.add(Integer.parseInt(rs.getString("Num")));


			}
			return book_num_list;		
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}  finally {	
			try {
				if (state != null)	state.close();
				if (conn != null)	conn.close();
				if(rs!=null)	rs.close();
			} catch (SQLException e) {}
		}
	}
	public synchronized static String getBorrowedBookUser(int book_number) throws SQLException {//���� 
		Connection conn = null;
		Statement state = null;
		ResultSet rs =null;

		try {
			conn = getConn();
			state = conn.createStatement();// conn���������� state�� ����.
			String sql;
			sql = "SELECT *FROM usersbook WHERE num='" +book_number+ "' and status='Borrowed'";

			rs = state.executeQuery(sql);
			if(rs==null) {
				return null;//���� X
			}
			if (rs.next()) {//���߿� �ٲ�
				return (rs.getString("Requester_ID"));
			}
			
			return null;		
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}  finally {	
			try {
				if (state != null)	state.close();
				if (conn != null)	conn.close();
				if(rs!=null)	rs.close();
			} catch (SQLException e) {}
		}
	}
	public synchronized static List<Integer> getLoanedBookNumber(String ID) throws SQLException {//ID�� ��������� ID(Requester_ID)
		Connection conn = null;
		Statement state = null;
		ResultSet rs =null;

		try {
			conn = getConn();
			state = conn.createStatement();// conn���������� state�� ����.
			String sql;
			sql = "SELECT *FROM usersbook WHERE id='" +ID+ "' AND STATUS= 'Borrowed'";
			List<Integer> book_num_list=new ArrayList<>();
			rs = state.executeQuery(sql);
			if(rs==null) {
				return null;//���� X
			}
			while (rs.next()) {//���߿� �ٲ�
				book_num_list.add(Integer.parseInt(rs.getString("Num")));

			}
			return book_num_list;		
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}  finally {	
			try {
				if (state != null)	state.close();
				if (conn != null)	conn.close();
				if(rs!=null)	rs.close();
			} catch (SQLException e) {}
		}
	}
	
	public synchronized static void BorrowRequest(int BookNum, String Requester_ID) throws SQLException {//������
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = getConn();

			

	        String sql = "update usersbook set Requester_ID=?, Request_Info=? where Num = ?";

	        try {
	        	pstmt = conn.prepareStatement(sql);

	        	pstmt.setString(1,Requester_ID); 
	        	pstmt.setString(2, "������"); 
	        	pstmt.setString(3, BookNum+"");
	        	pstmt.executeUpdate();
	        	
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }


			pstmt.close();
			conn.close();
		} finally {	
			try {
				if (pstmt != null)	pstmt.close();
				if (conn != null)	conn.close();
			} catch (SQLException e) {}
		}
	}
	
	public synchronized static void PurchaseRequest(int BookNum, String Requester_ID) throws SQLException {//������
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConn();
	        String sql = "update usersbook set Requester_ID=?, Request_Info=? where Num = ?";

	        try {
	        	pstmt = conn.prepareStatement(sql);

	        	pstmt.setString(1,Requester_ID); 
	        	pstmt.setString(2, "���"); 
	        	pstmt.setString(3, BookNum+"");
	        	pstmt.executeUpdate();
	        	
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }


			pstmt.close();
			conn.close();
		} finally {	
			try {
				if (pstmt != null)	pstmt.close();
				if (conn != null)	conn.close();
			} catch (SQLException e) {}
		}
	}
	
	public synchronized static void NoBorrowSell(int BookNum) throws SQLException {//������
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = getConn();

			   String sql = "update usersbook set Requester_ID=?, Request_Info=? where Num = ?";

		        try {
		        	pstmt = conn.prepareStatement(sql);

		        	//pstmt.setString(1,"NULL"); pstmt.setString(2, "NULL"); pstmt.setString(3, BookNum+"");
		        	pstmt.setNull(1,Types.VARBINARY);
		        	pstmt.setNull(2,Types.VARBINARY);
		        	pstmt.setString(3, BookNum+"");
		        	pstmt.executeUpdate();
		        	
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }


			
			pstmt.close();
			conn.close();
		} finally {	
			try {
				if (pstmt != null)	pstmt.close();
				if (conn != null)	conn.close();
			} catch (SQLException e) {}
		}
	}

	
	public synchronized static void ReturnBook(int BookNum) throws SQLException {//������
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = getConn();

			   String sql = "update usersbook set Requester_ID=?, Request_Info=?, status=? where Num = ?";

		        try {
		        	pstmt = conn.prepareStatement(sql);


		        	pstmt.setNull(1,Types.VARBINARY);
		        	pstmt.setNull(2,Types.VARBINARY);
		          	pstmt.setString(3,"Registered");
		        	pstmt.setString(4, BookNum+"");
		        	pstmt.executeUpdate();
		        	
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }


			
			pstmt.close();
			conn.close();
		} finally {	
			try {
				if (pstmt != null)	pstmt.close();
				if (conn != null)	conn.close();
			} catch (SQLException e) {}
		}
	}
	public synchronized static void deleateBook(int Book_Number) {//�����ϰ� 
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConn();

			String sql;
			sql = "DELETE FROM usersbook WHERE Num=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, Book_Number+"");
			pstmt.execute();

			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}  finally {	
			try {
				if (pstmt != null)	pstmt.close();
				if (conn != null)	conn.close();
			} catch (SQLException e) {}
		}
	}
	
	public synchronized static boolean checkRemovePossibility(int Book_Number) {//���� ������ �������� Ȯ��
		Connection conn = null;
		Statement state = null;
		ResultSet rs =null;

		try {
			conn = getConn();
			state = conn.createStatement();// conn���������� state�� ����.

			String sql;
			sql = "SELECT *FROM usersbook WHERE Num='" +Book_Number+ "'";
			
			rs = state.executeQuery(sql);

			if (rs.next()) {
				if( rs.getString("Requester_ID")!=null) {//null�� �ƴϸ�
		
					return false;
				}else if(!rs.getString("status").equals("Registered")) {//��ϵȰ� �ƴϸ�
					return false;
				}
				return true;
			}else {
				return false;
			}


		} catch (Exception e) {
			return false;
		} finally {	
			try {
				if (state != null)	state.close();
				if (conn != null)	conn.close();
				if(rs!=null)	rs.close();
			} catch (SQLException e) {}
		}
	}
	public synchronized static void BorrowBook(int BookNum) throws SQLException {//������
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = getConn();

			String sql = "update usersbook set status=? where Num = ?";

	        try {
	        	pstmt = conn.prepareStatement(sql);

	        	pstmt.setString(1,"Borrowed"); 
	        	pstmt.setString(2, BookNum+"");
	        	pstmt.executeUpdate();
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }


			
			pstmt.close();
			conn.close();
		} finally {	
			try {
				if (pstmt != null)	pstmt.close();
				if (conn != null)	conn.close();
			} catch (SQLException e) {}
		}
	}
}
