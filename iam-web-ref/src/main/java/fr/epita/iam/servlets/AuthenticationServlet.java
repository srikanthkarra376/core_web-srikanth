/**
 * 
 */
package fr.epita.iam.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.Dao;

/**
 * @author tbrou
 *
 */

@WebServlet(name="AuthenticationServlet", urlPatterns={"/authenticate"})
public class AuthenticationServlet extends HttpServlet{

	
	@Autowired
	Dao<Identity> dao;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static final Logger LOGGER = LogManager.getLogger(AuthenticationServlet.class);
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		LOGGER.info("dao instance is : {}", dao);
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		LOGGER.info("tried to authenticate with this login {}", login);
	}
}
