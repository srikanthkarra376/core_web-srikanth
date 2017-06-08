/**
 * 
 */
package fr.tbr.iam.web.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * @author srikanth
 *
 */
public class GenericSpringServlet extends HttpServlet {

	
	private static final long serialVersionUID = 2081780261460976356L;

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());

	}

}




