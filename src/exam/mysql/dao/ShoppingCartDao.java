package exam.mysql.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import exam.dao.ShoppingCartDaoImp;
import exam.domain.SeeShoppingCartItem;
import exam.domain.ShoppingCartCell;
import exam.domain.UserBase;
import exam.utils.DBUtil;

public class ShoppingCartDao implements ShoppingCartDaoImp{

	@Override
	public ArrayList<ShoppingCartCell> getAllShoppingCartCell(String username) {
		String sql = "select * from shopping_cart where username = ?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<ShoppingCartCell> cells = new ArrayList<>();
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, username);
			rs = st.executeQuery();
			while(rs.next()){
				ShoppingCartCell cell = new ShoppingCartCell();
				cell.setBookname(rs.getString("bookname"));
				cell.setBookid(rs.getString("bookid"));
				cell.setCount(rs.getString("count"));
				cell.setPrice(rs.getString("price"));
				cell.setUsername(rs.getString("username"));
				cells.add(cell);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn,st,rs);
		}
		return cells;
		
	}

	@Override
	public int addShoppingCartCell(ShoppingCartCell cell) {
		String sql = "insert into shopping_cart values(?,?,?,?,?,?)";
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, cell.getBookname());
			st.setString(2, cell.getBookid());
			st.setString(3, cell.getCount());
			st.setString(4, cell.getPrice());
			st.setString(5, cell.getUsername());
			st.setBoolean(6, false);
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateShoppingCartCell(ShoppingCartCell cell) {
		String sql = "update shopping_cart set count=? where bookid = ? and username =?";
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, cell.getCount());
			st.setString(2, cell.getBookid());
			st.setString(3, cell.getUsername());
			result = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn,st,null);
		}
		
		return result;
	}

	@Override
	public int deleteShoppingCartCell(String username,String bookid) {
		String sql = "delete from shopping_cart where bookid = ? and username = ?";
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(sql);
			st.setString(1,bookid);
			st.setString(2,username);
			result = st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn,st,null);
		}
		
		return result;
	}
	public boolean isExcistShoppingCell(String bookid,String username){
		String sql="select bookid from shopping_cart where bookid=? and username=?";
		
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		Connection connection=null;
		Boolean isExisit=false;
		
		connection=DBUtil.getConnection();
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, bookid);
			preparedStatement.setString(2, username);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				isExisit=true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(connection, preparedStatement, resultSet);
		}
		return isExisit;
	}
	public int getShoppingCountByBookidAndUsername(String bookid,String username){
		String sql="select count from shopping_cart where bookid=? and username=?";
		
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		Connection connection=null;
		int shoppingcount=0;
		
		connection=DBUtil.getConnection();
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, bookid);
			preparedStatement.setString(2, username);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				shoppingcount=resultSet.getInt("count");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shoppingcount;
	}
	public LinkedList<SeeShoppingCartItem> getshopbaseByUsername(String username){
		String sql="select bookid,count,price,bookname,ischecked from shopping_cart where username=?";
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		Connection connection=DBUtil.getConnection();
		LinkedList<SeeShoppingCartItem> results=null;
		
		try {
			preparedStatement=connection.prepareStatement(sql);
			
			preparedStatement.setString(1, username);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				if(results==null){
					results=new LinkedList<SeeShoppingCartItem>();
				}
				SeeShoppingCartItem seeShoppingCartItem=new SeeShoppingCartItem();
				seeShoppingCartItem.setBookid(resultSet.getString(1));
				seeShoppingCartItem.setGoodcount(resultSet.getString(2));
				seeShoppingCartItem.setGoodprice(resultSet.getString(3));
				seeShoppingCartItem.setGoodAllPrice(String.valueOf(resultSet.getInt(2)*resultSet.getInt(3)));
				seeShoppingCartItem.setBookname(resultSet.getString(4));
				seeShoppingCartItem.setSelect(resultSet.getString(5));
				results.add(seeShoppingCartItem);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(connection, preparedStatement, resultSet);
		}
		
		return results;
	}
	public void updateSelect(String username,String bookid,boolean select){
		String value=select? "1":"0";
		String sql = "update shopping_cart set ischecked=? where bookid = ? and username =?";
		
		PreparedStatement preparedStatement=null;
		Connection connection=DBUtil.getConnection();
		
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, value);
			preparedStatement.setString(2, bookid);
			preparedStatement.setString(3, username);
			int result=preparedStatement.executeUpdate();
			System.out.println("result="+result);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(connection, preparedStatement, null);
		}
	}
}
