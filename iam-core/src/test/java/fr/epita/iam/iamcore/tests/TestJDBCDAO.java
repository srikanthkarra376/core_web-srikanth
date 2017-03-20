/**
 * 
 */
package fr.epita.iam.iamcore.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.IdentityJDBCDAO;

/**
 * @author tbrou
 *
 */
public class TestJDBCDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(TestJDBCDAO.class);
	
	@BeforeClass
	public static void globalSetup() throws SQLException{
		LOGGER.info("beginning the setup");
		Connection connection = DriverManager.getConnection("jdbc:derby:memory:IAM;create=true", "TOM", "TOM");
		PreparedStatement pstmt = connection.prepareStatement("CREATE TABLE IDENTITIES " 
	    + " (IDENTITIES_UID INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT IDENTITIES_PK PRIMARY KEY, " 
	    + " IDENTITIES_DISPLAYNAME VARCHAR(255), "
	    + " IDENTITIES_EMAIL VARCHAR(255), "
	    + " IDENTITIES_BIRTHDATE DATE "
	    + " )");
		
		pstmt.execute();
		connection.commit();
		pstmt.close();
		connection.close();
		LOGGER.info("setup finished : ready to proceed with the testcase");
		
	}
	
	@Before
	public void setUp(){
		LOGGER.info("before test setup");
	}
	
	
	@Test
	public void basicTest() throws SQLException{
		IdentityJDBCDAO dao = new IdentityJDBCDAO();
		dao.writeIdentity(new Identity(null, "Thomas Broussard", "thomas.broussard@gmail.com"));
		
	}
	
	
	@After
	public void tearDown(){
		LOGGER.info("after test cleanup");
	}
	
	
	@AfterClass()
	public static void globalTearDown(){
		LOGGER.info("global cleanup");
	}

}
