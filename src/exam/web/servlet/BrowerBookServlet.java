package exam.web.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.If;

import exam.domain.Book;
import exam.domain.BookBase;
import exam.service.BookService;
import net.sf.json.JSONArray;
/**
 * Servlet implementation class BrowerBookServlet
 */
//@WebServlet("/BrowerBookServlet")
public class BrowerBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static BookService bookService=new BookService();
	private HttpSession session;
	private int curpage=1;
	private int pagecount=12;
	private JSONArray jsonArray=new JSONArray();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session=req.getSession();
		
		if(req.getParameter("tonext")!=null){
			curpage++;
		}else if(req.getParameter("topre")!=null){
			curpage--; 
		}
		LinkedList<Book> books=null;
		resp.setContentType("text/html;charset=utf-8");
		String categoryCode=null;
		if(req.getParameter("code")!=null){
			curpage=1;
			session.setAttribute("curpage", curpage);
			session.setAttribute("pagecount", pagecount);
			
			categoryCode=req.getParameter("code");
			session.setAttribute("code", categoryCode);
			
			String firstpath=req.getParameter("firstpath");
			String secondpath=req.getParameter("secondpath");
			session.setAttribute("firstpath", firstpath);
			session.setAttribute("secondpath", secondpath);
		}else if(req.getParameter("bnk")!=null){
			if(req.getParameter("toserach")!=null){
				curpage=1;
			}
			String booknameKey=req.getParameter("bnk");
			books=bookService.getBookBySimilarName(booknameKey,curpage,pagecount);
		}else{
			categoryCode=(String)session.getAttribute("code");
		}
		
		if(req.getParameter("bnk")==null)
			books=bookService.getBooksByCategoryItem(categoryCode,curpage,pagecount);
		
		session.setAttribute("curBooks", books);
		
		for(Book i:books){
			jsonArray.add(new BookBase(i.getBookname(), i.getId(), i.getPrice(), i.getBooksell()));
		}
		
		resp.getWriter().write("("+jsonArray.toString()+")");
		jsonArray.clear();
	}
}
