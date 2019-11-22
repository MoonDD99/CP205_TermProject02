package exception;

import java.util.regex.Pattern;

public class FormException {
	
	public static boolean IDFormCheck(String id) throws MyException {//아이디는 오로지 영어와 숫자로만
		if(Pattern.matches("^[a-zA-Z0-9]*$", id)&&id.length()!=0) {
			return true;//형식에 맞다면
		}
		throw new MyException("ID does not match format");
	}
	
	public static boolean passwordFormCheck(String id) throws MyException{//비밀번호는 영어, 숫자 ,(!@#$%^&*())의 특수문자가 허용.
		if(Pattern.matches("^[a-zA-Z0-9!@#$%^&*()]*$", id)&&id.length()!=0)
			return true;
		
		throw new MyException("Password does not match format");
	}
	
	public static boolean NameFormCheck(String id) throws MyException{//이름은 한글과 영어만 가능.
		if(Pattern.matches("^[a-zA-Z가-힣]*$", id)&&id.length()!=0)
			return true;

		throw new MyException("Name does not match format");
	}
	
	public static boolean phoneFormCheck(String id) throws MyException {//핸드폰번호 형식은 000-000(0)-0000
		if(Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", id)&&id.length()!=0)
			return true;
		
		throw new MyException("Phone does not match format");
	}

}
