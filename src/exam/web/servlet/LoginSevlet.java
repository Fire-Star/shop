package exam.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exam.domain.PasswordException;
import exam.domain.SystemException;
import exam.domain.UserException;
import exam.domain.UsernameException;
import exam.service.LoginService;
import exam.utils.CommonUtils;

/**
 * Servlet implementation class LoginSevlet
 */
//@WebServlet("/LoginSevlet")
public class LoginSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static LoginService loginService=new LoginService();
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 调用LoginService直接登录
		 */
		HttpSession session=request.getSession();
		try {
			loginService.login(request.getParameterMap());
		} catch (UserException e) {
			session.setAttribute("systemError", e.getMessage());
			response.sendRedirect("index.jsp");
			return;
		} catch (PasswordException e) {
			session.setAttribute("passwordError", e.getMessage());
			response.sendRedirect("index.jsp");
			return;
		} catch (UsernameException e) {
			session.setAttribute("usernameError", e.getMessage());
			response.sendRedirect("index.jsp");
			return;
		} catch (SystemException e) {
			session.setAttribute("systemError", e.getMessage());
			response.sendRedirect("index.jsp");
			return;
		}
		
		session.setAttribute("user", loginService.getUserBase());
		response.sendRedirect("mainpage/index.jsp");
		return;
	}
	
}
