package fr.tbr.iam.web.servlets;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.tbr.iam.log.IAMLogger;
import fr.tbr.iam.log.impl.IAMLogManager;
import fr.tbr.iamcore.datamodel.Identity;
import fr.tbr.iamcore.exception.DAOSearchException;
import fr.tbr.iamcore.service.dao.DAODeleteException;
import fr.tbr.iamcore.service.dao.IdentityDAOInterface;

/**
 * Servlet implementation class UpdateAndDelete
 */
public class UpdateAndDelete extends GenericSpringServlet {
	private static final long serialVersionUID = 1L;

	IAMLogger logger = IAMLogManager.getIAMLogger(UpdateAndDelete.class);

	@Inject
	IdentityDAOInterface dao;

	/**
	 * Default constructor.
	 */
	public UpdateAndDelete() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		Collection<Identity> idList = null;
		try {
			idList = dao.search(new Identity(request.getParameter("selection"), null, null));
		} catch (DAOSearchException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if ("modify".equals(request.getParameter("type")) && idList != null) {
			for (Identity identity : idList) {

				request.getSession().setAttribute("identity", identity);
				request.getRequestDispatcher("create.jsp").forward(request, response);

			}

		} else if ("delete".equals(request.getParameter("type")) && idList != null) {
			
			if (request.getParameter("selection") != null) {
				
				for (Identity identity : idList) {
					try {
					dao.delete(identity);

				} catch (DAODeleteException e) {
					logger.info(e.getMessage());
					e.printStackTrace();
				}
				}

			}
		} else {
			response.sendRedirect("search.jsp?msg=");
		}
	}

}
