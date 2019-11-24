package authentication;

import database.DB_USER;
import exception.FormException;
import exception.MyException;
import user.User;

public class LogInContext {


	public static boolean SignUp(String Name, String Phone, String ID, String Password, String Emain	,String Address) throws MyException {// ȸ������
	
		if(FormException.NameFormCheck(Name)&&FormException.phoneFormCheck(Phone)&&
				FormException.IDFormCheck(ID)&&FormException.passwordFormCheck(Password)) {//���Ŀ� �´ٸ�
			
			User getUser;
			getUser = DB_USER.getUser(ID);// �ߺ��˻����ִ� �޼ҵ��� ���ϰ��� ��й�ȣ�̴�, ���� �ߺ��Ǹ� null����
			if (getUser != null) { // �ߺ��ȴٸ�
				throw new MyException("Already Exist ID");
			}else {
			DB_USER.insertUser(ID, Password, Name, Phone, Emain, Address, 1, 0);
			// ȸ������ �Ϸ�
			return true;
			}
		}
		return false;
		
	}

	public static boolean SignOut(String ID, String Password) throws MyException {
		User getUser;
		getUser = DB_USER.getUser(ID);
			if (getUser == null) {// ���̵� �������� �ʴ´ٸ� null�� ��ȯ
				throw new MyException("ID does not exist");
			}
		// ���� ���̵�� �����ϴ� ��Ȳ
	
			if (Password.equals(getUser.getPassword())) {
				DB_USER.deleateUser(ID);
				return true;//���� ����
			}else {
			throw new MyException("Passwords do not match");
				}
			
			
	}

	public static boolean LogIn(String ID, String PW) throws MyException {
		User getUser;
		getUser = DB_USER.getUser(ID);

		if (getUser == null) {// �������� �ʴ� ���̵���
			throw new MyException("ID does not exist");
		}
		if (PW.equals(getUser.getPassword())) {
			//����
			if(!getUser.is_connected()) {//����Ǿ� ������ true, �ƴϸ� false
				return true;//�α��� ����
			}
			else {
				throw new MyException("You are already logged in");
			}
		} else {//��й�ȣ�� �ٸ��ٸ�
			throw new MyException("Passwords do not match");
		}

	}
	

	public static void LogOut(String ID) {
		DB_USER.userLogOut(ID);
	}

}
