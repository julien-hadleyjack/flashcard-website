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

/** Authentifizierungsfilter, der es ermöglicht nicht eingeloggten Nutzern den Zugriff auf Seiten 
 * zu verbieten und sie wieder zum Loginscreen zu schicken
 * 
 * Eingebunden in der web.xml
 */
public class AuthenticationFilter implements Filter {


		  @Override
		  public void init(FilterConfig fc) throws ServletException {
		  }

		  @Override
		  public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
		    HttpServletRequest req = (HttpServletRequest) request;
		    HttpServletResponse res = (HttpServletResponse) response;  
		    if (req.getSession().getAttribute("userid") == null) {
		    	String path = ((HttpServletRequest) request).getRequestURI();
		    	if(path.startsWith("/help.html") || path.startsWith("/impressum.html")) {
			    	fc.doFilter(request, response);

		    	}
		    	else {
		        res.sendRedirect("/index.jsp");
		    	}
		    } else {	
		    	
		    	fc.doFilter(request, response);
		    	
		    	
		    }
		  }

		  @Override
		  public void destroy() {
		  }

}
