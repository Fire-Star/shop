package exam.mysql.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

import exam.domain.CategoryItem;
import exam.utils.DBUtil;

public class CategoryDao {
	public LinkedList<CategoryItem> loadCategorys(){
		String sql="select * from category";
		
		Connection connection=DBUtil.getConnection();
		PreparedStatement preparedStatement=null;
		LinkedList<CategoryItem> categoryItems=null;
		ResultSet resultSet=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()){
				if(categoryItems==null){
					categoryItems=new LinkedList<CategoryItem>();
				}
				CategoryItem item=new CategoryItem();
				item.setCode(resultSet.getString(1));
				item.setName(resultSet.getString(2));
				item.setFathercode(resultSet.getString(3));
				item.setCategoryNext(new HashMap<String, LinkedList<CategoryItem>>());
				categoryItems.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(connection, preparedStatement, resultSet);
		}
		return categoryItems;
	}
	public CategoryItem getCategoryItemsByFatherCode(String fatherCode){
		String sql="select * from category where fathercode=?";
		
		CategoryItem item=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		Connection connection=DBUtil.getConnection();
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, fatherCode);
			resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()){
				item=new CategoryItem();
				item.setCode(resultSet.getString(1));
				item.setName(resultSet.getString(2));
				item.setFathercode(resultSet.getString(3));
				item.setCategoryNext(new HashMap<String, LinkedList<CategoryItem>>());
			}
		} catch (SQLException e) {
			DBUtil.closeAll(connection, preparedStatement, resultSet);
		}
		return item;
	}
}
