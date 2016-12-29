package exam.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.domain.ShoppingCartException;
import exam.service.ShoppingCartService;

/**
 * Servlet implementation class DeleteGoodItem
 */
//@WebServlet("/DeleteGoodItem")
public class DeleteGoodItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ShoppingCartService shoppingCartService=new ShoppingCartService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		String bookid=req.getParameter("bookid");
		
		try {
			shoppingCartService.deleteShoppingCart(username, bookid);
		} catch (ShoppingCartException e) {
			e.printStackTrace();
		}
	}
}
