/**
 * 
 */
package fr.epita.iam.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epita.iam.datamodel.Identity;

/**
 * @author tbrou
 *
 */
public class IdentityJDBCDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(IdentityJDBCDAO.class);

	@Inject
	@Named("dataSourceBean")
	private DataSource ds;

	/**
	 * @throws SQLException
	 * 
	 */
	private IdentityJDBCDAO() throws SQLException {
	}


	public void writeIdentity(Identity identity) throws SQLException {
		LOGGER.debug("=> writeIdentity : tracing the input : {}", identity);
		
		String insertStatement = "insert into IDENTITIES (IDENTITIES_DISPLAYNAME, IDENTITIES_EMAIL, IDENTITIES_BIRTHDATE) "
				+ "values(?, ?, ?)";
		
		Connection connection = ds.getConnection();
		PreparedStatement pstmt_insert = connection.prepareStatement(insertStatement);
		pstmt_insert.setString(1, identity.getDisplayName());
		pstmt_insert.setString(2, identity.getEmail());
		Date now = new Date();
		pstmt_insert.setDate(3, new java.sql.Date(now.getTime()));

		
		pstmt_insert.execute();
		pstmt_insert.close();
		connection.close();
		LOGGER.debug("<= writeIdentity: leaving the method with no error" );
		

	}

	public List<Identity> readAll() throws SQLException {
		LOGGER.debug("=> readAll");
		
		
		List<Identity> identities = new ArrayList<Identity>();
		Connection connection = ds.getConnection();
		
		PreparedStatement pstmt_select = connection.prepareStatement("select * from IDENTITIES");
		ResultSet rs = pstmt_select.executeQuery();
		while (rs.next()) {
			String displayName = rs.getString("IDENTITIES_DISPLAYNAME");
			String uid = String.valueOf(rs.getString("IDENTITIES_UID"));
			String email = rs.getString("IDENTITIES_EMAIL");
//			Date birthDate = rs.getDate("IDENTITIES_BIRTHDATE");
			Identity identity = new Identity(uid, displayName, email);
			identities.add(identity);
		}
		rs.close();
		pstmt_select.close();
		connection.close();
		return LOGGER.traceExit("<= readAll : {}", identities);
		
		

	}

}
