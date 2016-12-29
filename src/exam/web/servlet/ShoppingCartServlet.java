package exam.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exam.domain.ShoppingCartCell;
import exam.domain.ShoppingCartException;
import exam.domain.ShoppingCellException;
import exam.domain.UserBase;
import exam.service.ShoppingBreakException;
import exam.service.ShoppingCartService;
import exam.utils.CommonUtils;
import exam.utils.RandomNumber;

/**
 * Servlet implementation class ShoppingCartPriceServlet
 */
//@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ShoppingCartService shoppingCartService = new ShoppingCartService();
    private HttpSession session;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		if(session.getAttribute("user")==null){
			response.sendRedirect("index.jsp");
			return;
		}
		
		ShoppingCartCell shoppingCartCell=(ShoppingCartCell)CommonUtils.populateUserBase(new ShoppingCartCell(), request.getParameterMap());
		try {
			shoppingCartService.addToShoppingCart(shoppingCartCell,request.getParameter("o"));
			if(request.getParameter("re")!=null){
				request.getRequestDispatcher("SeeShoppingCart").forward(request, response);
			}else {
				session.setAttribute("systemError", "添加购物车成功!!");
				session.setAttribute("location", "mainpage/shoppingcartpage/shopping.jsp");
				session.setAttribute("tishiimgurl", "tishi0"+RandomNumber.getRandomNumber(0, 7)+".jpg");
				response.sendRedirect("statepage/statepage.jsp");
			}
			
			return;
			
		} catch (ShoppingCartException e) {
			session.setAttribute("systemError", e.getMessage());
			session.setAttribute("location", "mainpage/index.jsp");
			session.setAttribute("tishiimgurl", "tishi0"+RandomNumber.getRandomNumber(1, 6)+".jpg");
			response.sendRedirect("statepage/statepage.jsp");
			return;
		} catch (ShoppingCellException e) {
			request.getSession().setAttribute("systemError", e.getMessage());
			session.setAttribute("location", "mainpage/index.jsp");
			session.setAttribute("tishiimgurl", "tishi0"+RandomNumber.getRandomNumber(1, 6)+".jpg");
			response.sendRedirect("statepage/statepage.jsp");
			return;
		} catch (ShoppingBreakException e) {
			request.getRequestDispatcher("SeeShoppingCart").forward(request, response);
			return;
		}
		
	}

}
