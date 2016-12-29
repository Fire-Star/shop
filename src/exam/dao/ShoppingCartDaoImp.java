package exam.dao;

import java.util.ArrayList;

import exam.domain.ShoppingCartCell;

public interface ShoppingCartDaoImp {
	public ArrayList<ShoppingCartCell> getAllShoppingCartCell(String username);
	public int addShoppingCartCell(ShoppingCartCell cell);
	public int updateShoppingCartCell(ShoppingCartCell cell);
	public int deleteShoppingCartCell(String username,String bookid);
	
}
