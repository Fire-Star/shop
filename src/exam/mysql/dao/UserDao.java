package exam.mysql.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exam.dao.UserDaoException;
import exam.dao.UserDaoImp;
import exam.domain.UserBase;
import exam.domain.UsernameException;
import exam.utils.DBUtil;

public class UserDao implements UserDaoImp{


	@Override
	public void addUser(UserBase newuser) throws UsernameException {
		UserBase userBase=findUserByUsername(newuser.getUsername(),newuser.getRole());
		if(userBase!=null){
			throw new UsernameException("该用户已经存在!");
		}
		
		String sql="insert into userbase values(?,?,?)";
		Connection connection=DBUtil.getConnection();
		PreparedStatement preparedStatement=null;
		
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, newuser.getUsername());
			preparedStatement.setString(2, newuser.getPassword());
			preparedStatement.setString(3, newuser.getRole());
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(connection, preparedStatement, null);
		}
	}

	@Override
	public UserBase findUserByUsername(String username,String role) {
		String sql = "select * from userbase where username = ? and role=?";
		UserBase user = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, username);
			st.setString(2, role);
			
			rs = st.executeQuery();
			if(rs.next()){
				user=new UserBase();
				user.setUsername(rs.getString("username"));	
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn,st,rs);
		}
		return user;
	}

}
