package exam.service;

import java.util.ArrayList;
import java.util.LinkedList;
import exam.domain.SeeShoppingCartItem;
import exam.domain.ShoppingCartCell;
import exam.domain.ShoppingCartException;
import exam.domain.ShoppingCellException;
import exam.mysql.dao.BookDao;
import exam.mysql.dao.ShoppingCartDao;

public class ShoppingCartService {
	private static ShoppingCartDao shoppingCartDao = new ShoppingCartDao();
	private static BookDao bookDao=new BookDao();
	
	public ArrayList<ShoppingCartCell> getAllShoppingCartCell(String username){
		return shoppingCartDao.getAllShoppingCartCell(username);
	}
	public void addToShoppingCart(ShoppingCartCell cell,String order) throws ShoppingCartException, ShoppingCellException, ShoppingBreakException{

		int count=1;
		if(order.equals("a")){
			count=shoppingCartDao.getShoppingCountByBookidAndUsername(cell.getBookid(),cell.getUsername())+1;
		}else if(order.equals("s")){
			count=shoppingCartDao.getShoppingCountByBookidAndUsername(cell.getBookid(),cell.getUsername())-1;
		}
		if(count==0){
			throw new ShoppingBreakException();
		}
		cell.setPrice(cell.getPrice().replace("￥", ""));
		cell.setCount(String.valueOf(count));
		int result=1;
		if(count==1&&order.equals("a")){
			result = shoppingCartDao.addShoppingCartCell(cell);
		}else {
			result = shoppingCartDao.updateShoppingCartCell(cell);
		}
		if(result == 0){
			throw new ShoppingCartException("添加购物车失败!");
		}
	}
	public void deleteShoppingCart(String username ,String bookid) throws ShoppingCartException{
		
		int result = shoppingCartDao.deleteShoppingCartCell(username,bookid);
		if(result == 0){
			throw new ShoppingCartException("删除购物车商品失败！");
		}
	}
	public void updateShoppingCart(String username,String bookid,String count) throws ShoppingCartException{
		
		ShoppingCartCell cell = new ShoppingCartCell();
		cell.setBookid(bookid);
		cell.setUsername(username);
		cell.setPrice(null);
		cell.setCount(count);
		int result = shoppingCartDao.updateShoppingCartCell(cell);
		if(result == 0){
			throw new ShoppingCartException("修改商品信息失败！");
		}
	}
	public static void main(String[] args) {
		
	}
	public LinkedList<SeeShoppingCartItem> getBookCoverAndBookInfoByUsername(String username){
		LinkedList<SeeShoppingCartItem> seeShoppingCartItems=shoppingCartDao.getshopbaseByUsername(username);
		if(seeShoppingCartItems==null)return null;
		bookDao.setCoverByShoppingCartItem(seeShoppingCartItems);
		return seeShoppingCartItems;
	}
	public void updateSelect(String username,String bookid,boolean flag){
		shoppingCartDao.updateSelect(username, bookid, flag);
	}
}
