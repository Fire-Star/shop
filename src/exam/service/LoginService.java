package exam.service;

import java.util.Map;

import exam.domain.PasswordException;
import exam.domain.SystemException;
import exam.domain.UserBase;
import exam.domain.UserException;
import exam.domain.UsernameException;
import exam.utils.CommonUtils;

public class LoginService {
	private static UserService userService=new UserService();
	private UserBase userBase;
	
	public void login(Map userParram) throws UserException, PasswordException, UsernameException, SystemException{
		
		UserBase loginUserBase = (UserBase)CommonUtils.populateUserBase(new UserBase(), userParram);
		if(loginUserBase.getUsername()==null||loginUserBase.getUsername().equals("")){
			throw new UsernameException("�û�������Ϊ��!");
		}
		if(loginUserBase.getPassword()==null||loginUserBase.getPassword().equals("")){
			throw new PasswordException("���벻��Ϊ��!");
		}
		if(loginUserBase.getRole()==null||loginUserBase.getRole().equals("")){
			throw new SystemException("û�е�¼Ȩ��!");
		}
		
		UserBase user = userService.findUserByUsername(loginUserBase.getUsername(),loginUserBase.getRole());
		
		if(user==null){
			throw new UserException("���û�������!");
		}
		if(!user.getPassword().equals(loginUserBase.getPassword())){
			throw new PasswordException("�������!");
		}
		setUserBase(user);
	}

	public UserBase getUserBase() {
		return userBase;
	}

	public void setUserBase(UserBase userBase) {
		this.userBase = userBase;
	}
	
	
}
