/**
 * 
 */
package fr.epita.iam.services;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fr.epita.iam.datamodel.Identity;

/**
 * @author tbrou
 *
 */
public class HibernateDAO {
	
	@Inject
	SessionFactory sf;
	
	
	
	public void writeIdentity(Identity identity){
		Session session = sf.openSession();
		session.save(identity);
		session.close();
	}
	
	public void updateIdentity(Identity identity){
		Session session = sf.openSession();
		session.update(identity);
		session.close();
	}
	 
	public void deleteIdentity(Identity identity){
		Session session = sf.openSession();
		session.delete(identity);
		session.close();
	}
	
	public List<Identity> search(Identity identity){
		Session session = sf.openSession();
		Query query = session.createQuery("from Identity as identity where identity.displayName like :displayName");
		query.setParameter("displayName", "%" + identity.getDisplayName() + "%");
		List<Identity> identityList = query.list();
		session.close();
		return identityList;
	}

}
