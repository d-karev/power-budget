package filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.userAuth;

public class authFilter implements javax.servlet.Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpServletResponse httpResp = (HttpServletResponse)response; 
		HttpSession httpSession = httpReq.getSession(false);		
		userAuth authBean = httpSession != null ? (userAuth)httpSession.getAttribute("userAuth") : null; 
		
		if (authBean != null && authBean.getAllowAccessToUserPages()) {
			chain.doFilter(request, response);
		} else {
			String contextPath = httpReq.getContextPath();
			httpResp.sendRedirect(contextPath + "/index.xhtml");
		}				
	}

}
