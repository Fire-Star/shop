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
import exam.domain.UsernameException;
import exam.service.RegistService;
import exam.service.UserService;
import exam.service.VertifyCodeException;
import exam.utils.RandomNumber;

/**
 * Servlet implementation class Re
 */
//@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserService UserService=new UserService();
	private static RegistService registService=new RegistService();
	private HttpSession session;
       
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String verticode=req.getParameter("vertifycode");
		session=req.getSession();
		/*
		 * ��������շ���ָ�롣verticode��ȡ��������Ϊ�ͷ��Ĵ��뱻���ˡ�
		 * �������������ʵ�ֶ���ҵ��㣬Servlet���ṩ�κ�ʵ�֡�
		 */
		try {
			registService.chekVertifyCode(verticode, (String)req.getSession().getAttribute("verCode"));
		} catch (VertifyCodeException e) {
			session.setAttribute("username", req.getParameter("username"));
			session.setAttribute("password", req.getParameter("password"));
			session.setAttribute("vertifyError", e.getMessage());
			resp.sendRedirect("regist.jsp");
			return;
		}
		
		/*
		 * ����û�
		 */
		try {
			registService.registUser(req.getParameterMap());
		} catch (UsernameException e) {
			session.setAttribute("usernameError", e.getMessage());
			resp.sendRedirect("regist.jsp");
			return;
		} catch (PasswordException e) {
			session.setAttribute("passwordError", e.getMessage());
			resp.sendRedirect("regist.jsp");
			return;
		} catch (SystemException e) {
			session.setAttribute("systemError", e.getMessage());
			resp.sendRedirect("regist.jsp");
			return;
		}
		
		req.getSession().setAttribute("systemError", "ע��ɹ���");
		
		session.setAttribute("location", "index.jsp");
		session.setAttribute("tishiimgurl", "tishi0"+RandomNumber.getRandomNumber(0, 7)+".jpg");
		
		resp.sendRedirect("statepage/statepage.jsp");
		
		session.removeAttribute("username");
		session.removeAttribute("password");
		return;
	}
}
