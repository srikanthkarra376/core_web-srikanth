/**
 * 
 */
package fr.epita.iam.iamcore.tests;

import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.iam.datamodel.Identity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestHibernate {
	
	
	@Inject
	SessionFactory sFactory;

	
	private static final Logger LOGGER = LogManager.getLogger(TestHibernate.class);
	

	
	
	
	
	@Test
	public void testHQL() {
		
		//Given
		String hqlQuery =  "from Identity as identity where identity.displayName = :displayName";
		Session session = sFactory.openSession();
		Transaction tx = session.beginTransaction();
		String displayName = "thomas broussard";
		session.save(new Identity("123", displayName, "tbroussard@natsystem.fr"));
		tx.commit();
		
		//When
		Query query = session.createQuery(hqlQuery);
		query.setParameter("displayName", displayName);
		List<Identity> results = query.list();
		
		//Then
		Assert.assertTrue(!results.isEmpty());
		
		
	}

}
