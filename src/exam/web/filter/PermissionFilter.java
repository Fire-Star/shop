package exam.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class PermissionFilter
 */
@WebFilter("/mainpage/shoppingcartpage/*")
public class PermissionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PermissionFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session=((HttpServletRequest)request).getSession();
		if(session.getAttribute("user")==null){
			System.out.println(((HttpServletRequest)request).getContextPath()+"/index.jsp");
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/index.jsp");
			
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
