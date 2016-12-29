package exam.dao;

import exam.domain.UserBase;
import exam.domain.UsernameException;

public interface UserDaoImp {
	public void addUser(UserBase newuser) throws UsernameException;
	public UserBase findUserByUsername(String username,String role);
}
