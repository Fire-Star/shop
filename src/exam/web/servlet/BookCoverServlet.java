package exam.web.servlet;

import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.domain.Book;

/**
 * Servlet implementation class BookCoverServlet
 */
//@WebServlet("/BookCoverServlet")
public class BookCoverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	int index=Integer.parseInt(req.getParameter("index"));
    	Book book=((LinkedList<Book>)req.getSession().getAttribute("curBooks")).get(index);
    	resp.getOutputStream().write(book.getCover(), 0, book.getCover().length);
    }
}
