/**
 * 
 */
package fr.epita.iam.iamcore.tests;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.IdentityJDBCDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestHibernate {
	
	
	@Inject
	SessionFactory sFactory;
	
	@Inject
	IdentityJDBCDAO jdbcDao;
	
	private static final Logger LOGGER = LogManager.getLogger(TestHibernate.class);
	

	
	
	@Test
	public void testHibernate() throws SQLException{
		
		List<Identity> identitiesListFromJdbc = jdbcDao.readAll();

		LOGGER.info("before : {} ", identitiesListFromJdbc);
		int originalSize = identitiesListFromJdbc.size();
		
		Session session = sFactory.openSession();
		Identity identity = new Identity("654564", "Thomas Broussard", "tbr@tbr.com");
		
		session.saveOrUpdate(identity);
		
		identitiesListFromJdbc = jdbcDao.readAll();
		LOGGER.info("after : {}", identitiesListFromJdbc);
		
		Assert.assertEquals(jdbcDao.readAll().size(), originalSize + 1);
		
	}

}
