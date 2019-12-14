package exception;

import java.util.regex.Pattern;

public class FormException {
	
	public static boolean IDFormCheck(String id) throws MyException {//���̵�� ������ ����� ���ڷθ�
		if(Pattern.matches("^[a-zA-Z0-9]{1,20}$", id)&&id.length()!=0) {
			return true;//���Ŀ� �´ٸ�
		}
		throw new MyException("����� ���ڸ� ����Ͽ� 20���� ���Ϸ� �ۼ��ϼ���.");
	}
	
	public static boolean passwordFormCheck(String pw) throws MyException{//��й�ȣ�� ����, ���� ,(!@#$%^&*())�� Ư�����ڰ� ���.
		if(Pattern.matches("^[a-zA-Z0-9!@#$%^&*()]{1,20}$", pw)&&pw.length()!=0)
			return true;
		
		throw new MyException("����, ����, Ư�����ڸ� ����Ͽ� 20���� ���Ϸ� �ۼ��ϼ���.");
	}
	
	public static boolean NameFormCheck(String name) throws MyException{//�̸��� �ѱ۰� ��� ����.
		if(Pattern.matches("^[a-zA-Z��-�R]{1,20}$", name)&&name.length()!=0)
			return true;

		throw new MyException("�ѱ۰� ��� ����Ͽ� 20���� ���Ϸ� �ۼ��ϼ���.");
	}
	
	public static boolean phoneFormCheck(String phone) throws MyException {//�ڵ�����ȣ ������ 010-000(0)-0000
		if(Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", phone)&&phone.length()!=0)
			return true;
		
		throw new MyException("��ȣ�� Ȯ�����ּ���.");
	}
	public static boolean emailFormCheck(String email) throws MyException {//email�� ����� ���ڸ�
		if(Pattern.matches("^[a-zA-Z0-9]{1,20}$", email)&&email.length()!=0)
			return true;
		
		throw new MyException("�̸��� �ּҸ� Ȯ�����ּ���.");
	}
	public static boolean AddressFormCheck(String name) throws MyException{//�ּ��� �ѱ۰� ����,���� ����.
		if(Pattern.matches("^[a-zA-Z��-�R0-9\\s]{1,100}$", name)&&name.length()!=0)
			return true;

		throw new MyException("�ѱ۰� ����,���ڸ� ����Ͽ� 100���� ���Ϸ� �ۼ��ϼ���.");
	}
	
	public static boolean BookFormCheck(String name) throws MyException{//������ �ѱ۰� ��� ����.
		if(Pattern.matches("^[a-zA-Z��-�R0-9!@#$%^&*()\\s]{1,30}$", name)&&name.length()!=0) 
			return true;

		throw new MyException("�ѱ۰� ����, ���ڿ� Ư�����ڸ� ����Ͽ� 30���� ���Ϸ� �ۼ��ϼ���.");
	}
	public static boolean BookIntroduceFormCheck(String name) throws MyException{//�Ұ� �ѱ۰� ��� ����.
		if(Pattern.matches("^[a-zA-Z��-�R0-9!@#$%^&*()\\s]{1,100}$", name)&&name.length()!=0) 
			return true;

		throw new MyException("�ѱ۰� ����, ���ڿ� Ư�����ڸ� ����Ͽ� 100���� ���Ϸ� �ۼ��ϼ���.");
	}
}
