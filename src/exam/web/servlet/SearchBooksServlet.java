package exam.web.servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.domain.Book;
import exam.domain.BookBase;
import exam.service.BookService;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class SearchBooks
 */
//@WebServlet("/SearchBooks")
public class SearchBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static BookService bookService=new BookService();
	private static JSONArray jsonArray=new JSONArray();
	private int curpage=1;
	private int pagecount=12;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String booknameKey=req.getParameter("bnk");
		resp.setContentType("text/html;charset=utf-8");
		
		LinkedList<Book> books=bookService.getBookBySimilarName(booknameKey,curpage,pagecount);
		req.getSession().setAttribute("curBooks", books);
		if(books!=null){
			for(Book i:books){
				jsonArray.add(new BookBase(i.getBookname(), i.getId(), i.getPrice(), i.getBooksell()));
			}
		}
		resp.getWriter().write(jsonArray.toString());
		jsonArray.clear();
	}
}
