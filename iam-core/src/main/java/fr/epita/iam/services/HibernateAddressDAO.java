/**
 * 
 */
package fr.epita.iam.services;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.epita.iam.datamodel.Address;

/**
 * @author tbrou
 *
 */
@Repository
public class HibernateAddressDAO implements Dao<Address> {

	/* (non-Javadoc)
	 * @see fr.epita.iam.services.Dao#write(java.lang.Object)
	 */
	public void write(Address instance) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see fr.epita.iam.services.Dao#delete(java.lang.Object)
	 */
	public void delete(Address instance) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see fr.epita.iam.services.Dao#update(java.lang.Object)
	 */
	public void update(Address instance) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see fr.epita.iam.services.Dao#search(java.lang.Object)
	 */
	public List<Address> search(Address instance) {
		// TODO Auto-generated method stub
		return null;
	}

}
