/**
 * 
 */
package fr.epita.iam.iamcore.tests;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.HibernateDAO;


/**
 * @author tbrou
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestHibernateDAO {
	
	@Inject
	HibernateDAO dao;
	
	@Test
	public void testEndToEndCrud(){
		Identity identity = new Identity("123", "Thomas Broussard", "tbr@tbr.com");
		dao.writeIdentity(identity);
		
		Assert.assertTrue(identity.getId()!= 0);
		identity.setDisplayName("Tom");
		dao.updateIdentity(identity);
		
		Identity criteria = new Identity(null, "Tom", null);
		
		
		List<Identity> results = dao.search(criteria);
		Assert.assertTrue(results != null && !results.isEmpty());
		
		dao.deleteIdentity(identity);
		
		results = dao.search(criteria);
		Assert.assertTrue(results.isEmpty());
		
		
		
	}

}
