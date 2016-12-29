package exam.service;

import java.util.Map;

import exam.domain.PasswordException;
import exam.domain.SystemException;
import exam.domain.UserBase;
import exam.domain.UsernameException;
import exam.utils.CommonUtils;

public class RegistService {
	private UserService userService=new UserService();
	
	public void chekVertifyCode(String userVertifyCode,String correctVertifyCode) throws VertifyCodeException{
		VertifyCodeCheckService.checkVertifyCode(userVertifyCode,correctVertifyCode);
	}
	/*
	 * ������ʵ���û������
	 */
	public void registUser(Map parrams) throws UsernameException, PasswordException, SystemException{
		
		UserBase userBase=(UserBase)CommonUtils.populateUserBase(new UserBase(),parrams);
		
		if(userBase.getUsername()==null||userBase.getUsername().equals("")){
			throw new UsernameException("�û�������Ϊ�գ�");
		}
		if(userBase.getPassword()==null||userBase.getPassword().equals("")){
			throw new PasswordException("���벻��Ϊ��!");
		}
		if(userBase.getRole()==null||userBase.equals("")){
			throw new SystemException("û�е�¼Ȩ��!");
		}
		userService.addUser(userBase);
	}
}
