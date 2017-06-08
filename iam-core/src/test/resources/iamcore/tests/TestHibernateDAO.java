/**
 * 
 */
package fr.epita.iam.iamcore.tests;

import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.Dao;
import fr.epita.iam.services.IdentityJDBCDAO;
import fr.epita.iam.services.WhereClauseBuilder;


/**
 * @author tbrou
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestHibernateDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(IdentityJDBCDAO.class);

	
	@Inject
	Dao<Identity> dao;
	
	@Inject
	SessionFactory sf;
	
	@Test
	public void testEndToEndCrud(){
		Identity identity = new Identity("123", "Thomas Broussard", "tbr@tbr.com");
		dao.write(identity);
		
		Assert.assertTrue(identity.getId()!= 0);
		identity.setDisplayName("Tom");
		dao.update(identity);
		
		Identity criteria = new Identity(null, "Tom", null);
		
		
		List<Identity> results = dao.search(criteria);
		Assert.assertTrue(results != null && !results.isEmpty());
		
		dao.delete(identity);
		
		results = dao.search(criteria);
		Assert.assertTrue(results.isEmpty());
		
		
		
	}
	
	@Test
	public void testWhereClauseBuilder() throws IllegalArgumentException, IllegalAccessException{
		WhereClauseBuilder wcb = new WhereClauseBuilder();
		
		Identity identity = new Identity("123", "Thomas Broussard", "tbr@tbr.com");
		dao.write(identity);
		
		Query query = wcb.getWhereClause(identity, sf.openSession());
		LOGGER.info(query);
		LOGGER.info(query.list());
		
		
	}

}
