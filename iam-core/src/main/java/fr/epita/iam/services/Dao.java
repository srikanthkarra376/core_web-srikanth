/**
 * 
 */
package fr.epita.iam.services;

import java.util.List;

/**
 * @author tbrou
 *
 */
public interface Dao<T> {
	
	public void write(T instance);
	public void delete(T instance);
	public void update(T instance);
	public List<T> search(T instance);

}
