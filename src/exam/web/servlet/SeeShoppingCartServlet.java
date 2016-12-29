package exam.web.servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.domain.CartGoodBase;
import exam.domain.SeeShoppingCartItem;
import exam.service.ShoppingCartService;
import exam.utils.StringUtil;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class SeeShoppingCart
 */
//@WebServlet("/SeeShoppingCart")
public class SeeShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ShoppingCartService shoppingCartService=new ShoppingCartService();
	private static LinkedList<SeeShoppingCartItem> shoppingCartItems;
	private static JSONArray jsonArray=new JSONArray();;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		String order=req.getParameter("order");
		if(order!=null&&order.equals("cover")){
			String index=req.getParameter("index");
			resp.getOutputStream().write(shoppingCartItems.get(Integer.parseInt(index)).getCover());
		}else{
			String usrename=req.getParameter("username");
			shoppingCartItems=shoppingCartService.getBookCoverAndBookInfoByUsername(usrename);
			
			if(shoppingCartItems!=null){
				for(SeeShoppingCartItem i:shoppingCartItems){
					jsonArray.add(new CartGoodBase(i.getBookname(),i.getGoodprice(),i.getGoodAllPrice(),i.getGoodcount(),i.getSelect(),i.getBookid()));
				}
				resp.getWriter().print("("+jsonArray.toString()+")");
				jsonArray.clear();
			}
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
