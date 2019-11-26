package exception;

import java.util.regex.Pattern;

public class FormException {
	
	public static boolean IDFormCheck(String id) throws MyException {//���̵�� ������ ����� ���ڷθ�
		if(Pattern.matches("^[a-zA-Z0-9]*$", id)&&id.length()!=0) {
			return true;//���Ŀ� �´ٸ�
		}
		throw new MyException("����� ���ڸ� ��� �����մϴ�.");
	}
	
	public static boolean passwordFormCheck(String pw) throws MyException{//��й�ȣ�� ����, ���� ,(!@#$%^&*())�� Ư�����ڰ� ���.
		if(Pattern.matches("^[a-zA-Z0-9!@#$%^&*()]*$", pw)&&pw.length()!=0)
			return true;
		
		throw new MyException("����, ����, Ư�����ڸ� ����ϼ���.");
	}
	
	public static boolean NameFormCheck(String name) throws MyException{//�̸��� �ѱ۰� ��� ����.
		if(Pattern.matches("^[a-zA-Z��-�R]*$", name)&&name.length()!=0)
			return true;

		throw new MyException("�ѱ۰� ��� ����ϼ���.");
	}
	
	public static boolean phoneFormCheck(String phone) throws MyException {//�ڵ�����ȣ ������ 010-000(0)-0000
		if(Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", phone)&&phone.length()!=0)
			return true;
		
		throw new MyException("��ȣ�� Ȯ�����ּ���.");
	}
	public static boolean emailFormCheck(String email) throws MyException {//email�� ����� ���ڸ�
		if(Pattern.matches("^[a-zA-Z0-9]*$", email)&&email.length()!=0)
			return true;
		
		throw new MyException("�̸��� �ּҸ� Ȯ�����ּ���.");
	}
}