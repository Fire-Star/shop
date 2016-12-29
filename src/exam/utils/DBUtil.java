package exam.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import exam.domain.UserBase;

	public class DBUtil {
		private static String user;
		private static String pwd;
		private static String driver;
		private static String url;
		
		static{
			Properties properties=new Properties();
			try {
				properties.load(new UserBase().getClass().getResourceAsStream("/daoConfig.txt"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			user=properties.getProperty("user");
			pwd=properties.getProperty("password");
			driver=properties.getProperty("driver");
			url=properties.getProperty("url");
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		public static Connection getConnection(){
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(url, user, pwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conn;
		}
		
		public static void closeAll(Connection con, PreparedStatement st, ResultSet rs){
			try {
				if(con != null){
					con.close();
				}
				if(st != null){
					st.close();
				}
				if(rs != null){
					rs.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

