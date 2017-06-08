/**
 * 
 */
package fr.epita.iam.iamcore.tests;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author tbrou
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestDatasource {
	
	@Inject
	DataSource ds;
	
	@Test
	public void testConnection() throws SQLException{
		Connection connection = ds.getConnection();
		connection.close();
	}

}
