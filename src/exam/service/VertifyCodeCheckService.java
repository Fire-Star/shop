package exam.service;

public class VertifyCodeCheckService {
	public static void checkVertifyCode(String userVertifyCode,String vertifyCode) throws VertifyCodeException{
		if(userVertifyCode==null||userVertifyCode.equals("")){
			throw new VertifyCodeException("验证码不能为空!");
		}
		if(!vertifyCode.equalsIgnoreCase(userVertifyCode)){
			throw new VertifyCodeException("验证码输入错误!");
		}
	}
}
