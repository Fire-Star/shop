package exam.service;

public class VertifyCodeCheckService {
	public static void checkVertifyCode(String userVertifyCode,String vertifyCode) throws VertifyCodeException{
		if(userVertifyCode==null||userVertifyCode.equals("")){
			throw new VertifyCodeException("��֤�벻��Ϊ��!");
		}
		if(!vertifyCode.equalsIgnoreCase(userVertifyCode)){
			throw new VertifyCodeException("��֤���������!");
		}
	}
}
