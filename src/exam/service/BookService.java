package exam.service;

import java.util.LinkedList;

import exam.domain.Book;
import exam.mysql.dao.BookDao;

public class BookService {
	private static CategoryService categoryService=new CategoryService();
	private static BookDao bookDao=new BookDao();
	public LinkedList<Book> getBooksByCategoryItem(String categoryCode,int curpage,int pagecount){
		return bookDao.getBooksByCategoryItem(categoryService.getCategoryItemsByFatherCode(categoryCode),curpage,pagecount);
	}
	public LinkedList<Book> getBookBySimilarName(String similarname,int curpage,int pagecount){
		return bookDao.getBookBySimilarName(similarname,curpage,pagecount);
	}
}
