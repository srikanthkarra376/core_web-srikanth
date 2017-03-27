/**
 * 
 */
package fr.epita.iam.iamcore.tests;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.IdentityJDBCDAO;

/**
 * @author tbrou
 *
 */


public class TestSpring {
	
	@Autowired
	IdentityJDBCDAO dao;	
	
	@Test
	public void testSpringContext() throws SQLException{
		dao.writeIdentity(new Identity(null, "Thomas", "thomas.broussard@gmail.com"));
	}

}
