package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {


		  @Override
		  public void init(FilterConfig fc) throws ServletException {
		  }

		  @Override
		  public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
		    HttpServletRequest req = (HttpServletRequest) request;
		    HttpServletResponse res = (HttpServletResponse) response;  
		    if (req.getSession().getAttribute("userid") == null) {
		        res.sendRedirect("/index.jsp");
		    } else {	
		    	
		    	fc.doFilter(request, response);
		    	
		    	
		    }
		  }

		  @Override
		  public void destroy() {
		  }

}
