/**
 * 
 */
package fr.epita.iam.services;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author tbrou
 *
 */
public class WhereClauseBuilder {
	
	private static final Logger LOGGER = LogManager.getLogger(WhereClauseBuilder.class);

	
	
	public Query getWhereClause(Object obj, Session session) throws IllegalArgumentException, IllegalAccessException{
		String queryString = "";
		Field[] fields = obj.getClass().getDeclaredFields();
		Map<String, Object> values = new LinkedHashMap<String, Object>();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			String name = field.getName();
			LOGGER.info(name);
			Object fieldValue = field.get(obj);
			LOGGER.info(fieldValue);
			String fieldWhereclause = name +" =  :" +name;
			values.put(name, fieldValue);
			queryString += " and " + fieldWhereclause;
		}
		if (!queryString.isEmpty()){
			queryString = "from " + obj.getClass().getSimpleName() + " where 1=1 " + queryString;
		}
		Query query = session.createQuery(queryString);
		String[] namedParameters = query.getNamedParameters();
		for (String parameter : namedParameters) {
			query.setParameter(parameter, values.get(parameter));
		}
		
		
		return query;
		
		
	}
	

}
