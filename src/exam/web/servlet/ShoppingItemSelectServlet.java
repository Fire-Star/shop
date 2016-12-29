package exam.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.service.ShoppingCartService;

/**
 * Servlet implementation class ShoppingItemSelect
 */
//@WebServlet("/ShoppingItemSelect")
public class ShoppingItemSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ShoppingCartService shoppingcartservice=new ShoppingCartService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		String bookid=req.getParameter("bookid");
		boolean select=req.getParameter("select").equals("true")? true:false;
		shoppingcartservice.updateSelect(username, bookid, select);
	}
}
