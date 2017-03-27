/**
 * 
 */
package fr.epita.iam.iamcore.tests;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.IdentityJDBCDAO;

/**
 * @author tbrou
 *
 */



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestSpring {
	
	@Autowired
	IdentityJDBCDAO dao;	
	
	@Test
	public void testSpringContext() throws SQLException{
		dao.writeIdentity(new Identity(null, "Thomas", "thomas.broussard@gmail.com"));
	}

}
