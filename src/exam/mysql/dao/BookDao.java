package exam.mysql.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import exam.domain.Book;
import exam.domain.CategoryItem;
import exam.domain.SeeShoppingCartItem;
import exam.utils.DBUtil;

public class BookDao {
	public LinkedList<Book> getBooksByCategoryItem(CategoryItem categoryItem,int curpage,int pagecount){
		return getBooksByBookCode(categoryItem.getCode(),curpage,pagecount);
	}
	public LinkedList<Book> getBooksByBookCode(String bookCode,int curpage,int pagecount){
		String sql="select * from newbooks where fathercode=? limit ?,?";
		Connection connection=DBUtil.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		LinkedList<Book> resultBooks=new LinkedList<Book>();
		
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, bookCode);
			preparedStatement.setInt(2, (curpage-1)*pagecount);
			preparedStatement.setInt(3, pagecount);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				Book book=new Book();
				book.setId(resultSet.getString(1));
				book.setBookname(resultSet.getString(2));
				book.setAuthor(resultSet.getString(3));
				book.setPress(resultSet.getString(4));
				book.setPresstime(resultSet.getString(5));
				book.setPrice(resultSet.getString(6));
				book.setIsbn(resultSet.getString(7));
				book.setC3code(resultSet.getString(8));
				book.setBookcounts(resultSet.getString(9));
				book.setBooksell(resultSet.getString(10));
				book.setIntroduction(resultSet.getString(11));
				book.setRecommend(resultSet.getString(12));
				book.setCover(resultSet.getBytes(13));
				resultBooks.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(connection, preparedStatement, resultSet);
		}
		
		return resultBooks;
	}
	public boolean isExistBookByBookID(String bookid){
		String sql="select bookname from newbooks where id=?";
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		Connection connection=null;
		Boolean isExisit=false;
		
		connection=DBUtil.getConnection();
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, bookid);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				isExisit=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(connection, preparedStatement, resultSet);
		}
		return isExisit;
	}
	public Map getBooknameAndBookpriceByBookid(String bookid){
		String sql="select bookname ,price from newbooks where id=?";
		
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		Connection connection=null;
		Map<String, String> result=null;
		
		connection=DBUtil.getConnection();
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, bookid);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				result=new HashMap<String, String>();
				result.put("bookname", resultSet.getString(1));
				result.put("price", resultSet.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(connection, preparedStatement, resultSet);
		}
		return result;
	}
	public byte[] getCoverByBookid(String bookid){
		String sql="select cover from newbooks where id=?";
		
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		Connection connection=DBUtil.getConnection();
		byte[] cover=null;
		
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, bookid);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				cover=resultSet.getBytes(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(connection, preparedStatement, resultSet);
		}
		return cover;
	}
	public void setCoverByShoppingCartItem(LinkedList<SeeShoppingCartItem> seeShoppingCartItems){
		
		for(SeeShoppingCartItem i:seeShoppingCartItems){
			i.setCover(getCoverByBookid(i.getBookid()));
		}
	}
	
	public LinkedList<Book> getBookBySimilarName(String similarname,int curpage,int pagecount){
		String sql="select * from newbooks where bookname like ? limit ?,?";
		
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		Connection connection=DBUtil.getConnection();
		LinkedList<Book> books=null;
		
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, "%"+similarname+"%");
			preparedStatement.setInt(2, (curpage-1)*pagecount);
			preparedStatement.setInt(3, pagecount);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				if(books==null){
					books=new LinkedList<Book>();
				}
				Book book=new Book();
				book.setId(resultSet.getString(1));
				book.setBookname(resultSet.getString(2));
				book.setAuthor(resultSet.getString(3));
				book.setPress(resultSet.getString(4));
				book.setPresstime(resultSet.getString(5));
				book.setPrice(resultSet.getString(6));
				book.setIsbn(resultSet.getString(7));
				book.setC3code(resultSet.getString(8));
				book.setBookcounts(resultSet.getString(9));
				book.setBooksell(resultSet.getString(10));
				book.setIntroduction(resultSet.getString(11));
				book.setRecommend(resultSet.getString(12));
				book.setCover(resultSet.getBytes(13));
				books.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(connection, preparedStatement, resultSet);
		}
		return books;
	}
}
