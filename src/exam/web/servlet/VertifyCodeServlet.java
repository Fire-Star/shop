package exam.web.servlet;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.service.VertifyCodeService;

/**
 * Servlet implementation class VertifyCodeServlet
 */
//@WebServlet("/VertifyCodeServlet")
public class VertifyCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VertifyCodeService vertifyCodeService=new VertifyCodeService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*
		 * 生成验证码。每次调用getImage就会重新生成一个验证码。
		 */
		ImageIO.write(vertifyCodeService.getImage(), "JPEG", resp.getOutputStream());
		
		String vcode=vertifyCodeService.getVertifyCode();
		req.getSession().setAttribute("verCode", vcode);
		
	}
}
