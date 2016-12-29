package exam.service;

import exam.dao.UserDaoImp;
import exam.domain.SystemException;
import exam.domain.UserBase;
import exam.domain.UsernameException;
import exam.mysql.dao.UserDao;

public class UserService {
	public static UserDaoImp userDao=new UserDao();
	
	public UserBase findUserByUsername(String username,String role) throws SystemException{
		UserBase userBase=null;
		if(role.equals("1")){
			userBase=userDao.findUserByUsername(username,role);
		}else {
			throw new SystemException("未开放此权限级别用户!");
		}
		return userBase;
	}
	public void addUser(UserBase newuser) throws SystemException, UsernameException{
		
		if(newuser.getRole().equals("1")){
			userDao.addUser(newuser);
		}else{
			throw new SystemException("未开放此权限级别用户!");
		}
	}
}
