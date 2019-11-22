package authentication;

import database.DB_USER;
import exception.FormException;
import exception.MyException;

public class LogInContext {


	public static boolean SignUp(String Name, String Phone, String ID, String Password, String Emain	,String Address) throws MyException {// 회원가입
	
		if(FormException.NameFormCheck(Name)&&FormException.phoneFormCheck(Phone)&&
				FormException.IDFormCheck(ID)&&FormException.passwordFormCheck(Password)) {//형식에 맞다면
			
			User getUser;
			getUser = DB_USER.getUser(ID);// 중복검사해주는 메소드의 리턴값은 비밀번호이다, 만약 중복되면 null값임
			if (getUser != null) { // 중복된다면
				throw new MyException("Already Exist ID");
			}else {
			DB_USER.insertUser(ID, Password, Name, Phone, Emain, Address, 1, 0);
			// 회원가입 완료
			return true;
			}
		}
		return false;
		
	}

	public static boolean SignOut(String ID, String Password) throws MyException {
		User getUser;
		getUser = DB_USER.getUser(ID);
			if (getUser == null) {// 아이디가 존재하지 않는다면 null을 반환
				throw new MyException("ID does not exist");
			}
		// 지울 아이디는 존재하는 상황
	
			if (Password.equals(getUser.getPassword())) {
				DB_USER.deleateUser(ID);
				return true;//제거 성공
			}else {
			throw new MyException("Passwords do not match");
				}
			
			
	}

	public static boolean LogIn(String ID, String PW) throws MyException {
		User getUser;
		getUser = DB_USER.getUser(ID);

		if (getUser == null) {// 존재하지 않는 아이디라면
			throw new MyException("ID does not exist");
		}
		if (PW.equals(getUser.getPassword())) {
			//성공
			if(!getUser.is_connected()) {//연결되어 있으면 true, 아니면 false
				return true;//로그인 성공
			}
			else {
				throw new MyException("You are already logged in");
			}
		} else {//비밀번호가 다르다면
			throw new MyException("Passwords do not match");
		}

	}
	

	public static void LogOut(String ID) {
		DB_USER.userLogOut(ID);
	}

}
